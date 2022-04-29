package com.zpj.hotfix.patcher.fix;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import com.zpj.hotfix.patcher.utils.FixMethodBuilder;
import com.zpj.hotfix.patcher.utils.MethodUtils;
import com.zpj.hotfix.patcher.utils.TypeHelper;
import org.jf.baksmali.Adaptors.*;
import org.jf.baksmali.Adaptors.Debug.DebugMethodItem;
import org.jf.baksmali.Adaptors.Format.InstructionMethodItem;
import org.jf.baksmali.Adaptors.Format.InstructionMethodItemFactory;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.analysis.AnalysisException;
import org.jf.dexlib2.analysis.AnalyzedInstruction;
import org.jf.dexlib2.analysis.MethodAnalyzer;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction21c;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction22c;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction35c;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction3rc;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;
import org.jf.dexlib2.iface.*;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.*;
import org.jf.dexlib2.iface.instruction.formats.Instruction31t;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.iface.reference.Reference;
import org.jf.dexlib2.immutable.instruction.ImmutableInstruction31t;
import org.jf.dexlib2.util.InstructionOffsetMap;
import org.jf.dexlib2.util.SyntheticAccessorResolver;
import org.jf.dexlib2.util.TypeUtils;
import org.jf.util.ExceptionWithContext;
import org.jf.util.IndentingWriter;
import org.jf.util.SparseIntArray;

import java.io.IOException;
import java.util.*;

public class FixMethodDefinition extends MethodDefinition {

    public final FixClassDefinition classDef;
    public final DexBackedMethod method;
    public final MethodImplementation methodImpl;
    public final ImmutableList<Instruction> instructions;
    public final List<Instruction> effectiveInstructions;
    public final ImmutableList<MethodParameter> methodParameters;
    public FixRegisterFormatter registerFormatter;
    private final MethodDefinition.LabelCache labelCache = new MethodDefinition.LabelCache();
    private final SparseIntArray packedSwitchMap;
    private final SparseIntArray sparseSwitchMap;
    private final InstructionOffsetMap instructionOffsetMap;

    private final String bugType;
    private final String fixType;

    private final boolean isStatic;

    public FixMethodDefinition(FixClassDefinition classDef, DexBackedMethod method, MethodImplementation methodImpl) {
        super(classDef, method, methodImpl);
        this.classDef = classDef;
        this.method = method;
        this.methodImpl = methodImpl;

        this.isStatic = AccessFlags.STATIC.isSet(this.method.getAccessFlags());

        this.bugType = this.classDef.classDef.getType();
        this.fixType = bugType.substring(0, bugType.length() - 1) + "_Fix;";

        try {
            this.instructions = ImmutableList.copyOf(methodImpl.getInstructions());
            this.methodParameters = ImmutableList.copyOf(method.getParameters());
            this.effectiveInstructions = Lists.newArrayList(this.instructions);
            this.packedSwitchMap = new SparseIntArray(0);
            this.sparseSwitchMap = new SparseIntArray(0);
            this.instructionOffsetMap = new InstructionOffsetMap(this.instructions);
            int endOffset = this.instructionOffsetMap.getInstructionCodeOffset(this.instructions.size() - 1) + this.instructions.get(this.instructions.size() - 1).getCodeUnits();

            for (int i = 0; i < this.instructions.size(); ++i) {
                Instruction instruction = this.instructions.get(i);
                Opcode opcode = instruction.getOpcode();
                boolean valid;
                int codeOffset;
                int targetOffset;
                Instruction payloadInstruction;
                if (opcode == Opcode.PACKED_SWITCH) {
                    valid = true;
                    codeOffset = this.instructionOffsetMap.getInstructionCodeOffset(i);
                    targetOffset = codeOffset + ((OffsetInstruction) instruction).getCodeOffset();

                    try {
                        targetOffset = this.findPayloadOffset(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                    } catch (MethodDefinition.InvalidSwitchPayload var13) {
                        valid = false;
                    }

                    if (valid) {
                        if (this.packedSwitchMap.get(targetOffset, -1) != -1) {
                            payloadInstruction = this.findSwitchPayload(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            this.effectiveInstructions.set(i, new ImmutableInstruction31t(opcode, ((Instruction31t) instruction).getRegisterA(), endOffset - codeOffset));
                            this.effectiveInstructions.add(payloadInstruction);
                            endOffset += payloadInstruction.getCodeUnits();
                        }

                        this.packedSwitchMap.append(targetOffset, codeOffset);
                    }
                } else if (opcode == Opcode.SPARSE_SWITCH) {
                    valid = true;
                    codeOffset = this.instructionOffsetMap.getInstructionCodeOffset(i);
                    targetOffset = codeOffset + ((OffsetInstruction) instruction).getCodeOffset();

                    try {
                        targetOffset = this.findPayloadOffset(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                    } catch (MethodDefinition.InvalidSwitchPayload var14) {
                        valid = false;
                    }

                    if (valid) {
                        if (this.sparseSwitchMap.get(targetOffset, -1) != -1) {
                            payloadInstruction = this.findSwitchPayload(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            this.effectiveInstructions.set(i, new ImmutableInstruction31t(opcode, ((Instruction31t) instruction).getRegisterA(), endOffset - codeOffset));
                            this.effectiveInstructions.add(payloadInstruction);
                            endOffset += payloadInstruction.getCodeUnits();
                        }

                        this.sparseSwitchMap.append(targetOffset, codeOffset);
                    }
                }
            }

        } catch (Exception var15) {
            String methodString;
            try {
                methodString = MethodUtils.getMethodDescriptor(method);
            } catch (Exception var12) {
                throw ExceptionWithContext.withContext(var15, "Error while processing method");
            }

            throw ExceptionWithContext.withContext(var15, "Error while processing method %s", methodString);
        }
    }

    public void writeTo(IndentingWriter writer) throws IOException {
        int parameterRegisterCount = 0;


        writer.write(".method ");
        writeAccessFlags(writer, this.method.getAccessFlags());

        if (!isStatic) {
            writer.write(AccessFlags.STATIC.toString());
            writer.write(32);
        }

        writer.write(this.method.getName());
        writer.write("(");


        if (!isStatic) {
            writer.write(bugType);

            ++parameterRegisterCount;
        }

        for (MethodParameter parameter : this.methodParameters) {
            String type = parameter.getType();
            writer.write(type);
            ++parameterRegisterCount;
            if (TypeUtils.isWideType(type)) {
                ++parameterRegisterCount;
            }
        }

        writer.write(")");
        writer.write(this.method.getReturnType());
        writer.write(10);
        writer.indent(4);


        if (this.registerFormatter == null) {
            this.registerFormatter = new FixRegisterFormatter(this.classDef.options, this.methodImpl.getRegisterCount(), parameterRegisterCount);
        }

        if (this.classDef.options.useLocalsDirective) {
            writer.write(".locals ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() - parameterRegisterCount);
        } else {
            writer.write(".registers ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount());
        }

        writer.write(10);
        if (!isStatic) {
            writer.write(".param p0");
            if (this.classDef.options.outputDebugInfo) {
                writer.write(", ");
                ReferenceFormatter.writeStringReference(writer, "_this$p0");
            }

            writer.write("    # ");
            writer.write(this.bugType);
            writer.write("\n");
        }
        writeParameters(writer, this.method, this.methodParameters, this.classDef.options);

        String containingClass = null;
        if (this.classDef.options.useImplicitReferences) {
            containingClass = this.method.getDefiningClass();
        }

        AnnotationFormatter.writeTo(writer, this.method.getAnnotations(), containingClass);
        writer.write(10);
        List<MethodItem> methodItems = this.getMethodItems();


        for (MethodItem methodItem : methodItems) {
            System.out.println("-----------------------------------methodItem=" + methodItem);
            if (canWrite(writer, methodItem) && methodItem.writeTo(writer)) {
                writer.write(10);
            }
            System.out.println("-----------------------------------methodItem write end!");
        }

        writer.deindent(4);
        writer.write(".end method\n");
    }

    private boolean canWrite(IndentingWriter writer, MethodItem methodItem) throws IOException {
        if (methodItem instanceof InstructionMethodItem) {
            Instruction instruction = ((InstructionMethodItem<?>) methodItem).getInstruction();
            Opcode opcode = instruction.getOpcode();
            if (instruction instanceof DexBackedInstruction35c) { // opcode == Opcode.INVOKE_DIRECT
                // 函数调用 : invoke-direct
                Reference reference = ((DexBackedInstruction35c) instruction).getReference();
//            System.out.println("instruction reference=" + reference);
                if (reference instanceof DexBackedMethodReference) {
                    String name = ((DexBackedMethodReference) reference).getName();
                    String returnType = ((DexBackedMethodReference) reference).getReturnType();
                    List<String> parameterTypes = ((DexBackedMethodReference) reference).getParameterTypes();
                    String definingClass = ((DexBackedMethodReference) reference).getDefiningClass();
                    if (opcode == Opcode.INVOKE_SUPER) {
                        /*
                            invoke-super {p0}, Lcom/zpj/hotfix/patch_dev/super_method/Base;->test()V
                                ||
                                ||
                                VV
                            invoke-direct {p0}, Lcom/zpj/hotfix/patch_dev/super_method/Test;->_super_test()V
                         */

//                        String fixClass;
//                        String bugClass;
//                        String superMethodName = "_super_" + name;
//
////                        // 当父类该方法也修复的情况下，此时注入新的访问方法将导致死循环，所以不能新增方法。
//                        DiffClassInfo classInfo = Patcher.getClassInfo(definingClass);
//                        if (classInfo != null && classInfo.isFixMethod(name, parameterTypes)) {
////                            writer.write("invoke-super ");
//
//                            fixClass = classInfo.getFixType();
//                            bugClass = classInfo.getType();
//
//                            String key = returnType + "@" + superMethodName + "@" + parameterTypes;
//                            if (this.classDef.shouldInjectMethod(key)) {
//                                List<String> parameters = new ArrayList<>();
//                                parameters.add(bugClass);
//                                parameters.addAll(parameterTypes);
//                                String getMethod = FixMethodBuilder.buildAccessMethod(superMethodName, name,
//                                        parameters, returnType, bugClass, true);
//                                System.out.println("buildAccessMethod:\n\n" + getMethod + "\n\n");
//                                this.classDef.putNewMethod(key, getMethod);
//                            }
//                        } else {
//                            fixClass = fixType;
//                            bugClass = bugType;
//
//                            String key = returnType + "@" + name + "@" + parameterTypes;
//                            if (this.classDef.shouldInjectMethod(key)) {
//                                String superMethod = FixMethodBuilder.buildAccessSuperMethod(superMethodName, name,
//                                        parameterTypes, returnType, bugClass);
//                                System.out.println("buildAccessSuperMethod:\n\n" + superMethod + "\n\n");
//                                this.classDef.putNewMethod(key, superMethod);
//                            }
//                        }

                        String superMethodName = "_super_" + name;
                        String key = returnType + "@" + name + "@" + parameterTypes;
                        if (this.classDef.shouldInjectMethod(key)) {
                            String superMethod = FixMethodBuilder.buildAccessSuperMethod(superMethodName, name,
                                    parameterTypes, returnType, bugType);
                            System.out.println("buildAccessSuperMethod:\n\n" + superMethod + "\n\n");
                            this.classDef.putNewMethod(key, superMethod);
                        }

                        writer.write("invoke-static ");
                        ((InstructionMethodItem<?>) methodItem).writeInvokeRegisters(writer);
                        String parameters = String.join("", parameterTypes);
                        writer.write(", " + fixType + "->" + superMethodName + "(" + bugType + parameters + ")" + returnType);
                        return false;
                    } else if (bugType.equals(definingClass)) {
                        boolean isStatic = (opcode == Opcode.INVOKE_STATIC);

                        System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
                        System.out.println("DexBackedMethodReference getName=" + name);
                        System.out.println("DexBackedMethodReference getReturnType=" + returnType);
                        System.out.println("DexBackedMethodReference getParameterTypes=" + parameterTypes);

                        if (!this.classDef.classDef.getClassInfo().isFixMethod(name, parameterTypes)) {
                            String key = returnType + "@" + name + "@" + parameterTypes;
                            if (this.classDef.shouldInjectMethod(key)) {
                                String getMethod = FixMethodBuilder.buildAccessMethod(name, name,
                                        parameterTypes, returnType, bugType, isStatic);
                                System.out.println("buildAccessMethod:\n\n" + getMethod + "\n\n");
                                this.classDef.putNewMethod(key, getMethod);
                            }
                        }

                        String parameters;
                        if (isStatic) {
                            parameters = String.join("", parameterTypes);
                        } else {
                            parameters = bugType + String.join("", parameterTypes);
                        }
                        writer.write("invoke-static ");
                        ((InstructionMethodItem<?>) methodItem).writeInvokeRegisters(writer);
                        writer.write(", " + fixType + "->" + name + "(" + parameters + ")" + returnType);
                        return false;
                    } else {
                        // 新增方法的调用
                        String clazz = ((DexBackedMethodReference) reference).getDefiningClass();
                        DiffClassInfo info = Patcher.getClassInfo(clazz);
                        if (info != null) {
                            boolean isAddedMethod = info.isAddedMethod(name, parameterTypes);
                            if (isAddedMethod) {
//                                int register = ((DexBackedInstruction35c) instruction).getRegisterC();
//                                ((DexBackedMethodReference) reference).replaceDefiningClass(info.getFixType());
//                                String methodName = "get_" + info.getFixClassName();
//                                returnType = info.getFixType();

//                                writer.write("invoke-static {" + registerStr + "}, " + this.classDef.classDef.getFixType()
//                                        + "->" + methodName + "(" + clazz + ")" + returnType + "\n\n");

                                String parameters = String.join("", parameterTypes);
                                writer.write("invoke-static ");
                                ((InstructionMethodItem<?>) methodItem).writeInvokeRegisters(writer);
                                writer.write(", " + info.getFixType()
                                        + "->" + name + "(" + clazz + parameters + ")" + returnType + "\n\n");

//                                writer.write("move-result-object ");
//
//                                registerFormatter.writeTo(writer, register);
//                                writer.write("\n\n");
//
//                                String key = returnType + "@" + methodName + "@" + clazz;
//                                if (this.classDef.shouldInjectMethod(key)) {
//                                    String getMethod = FixMethodBuilder.buildAccessAddedMethod(methodName, clazz, returnType);
//                                    System.out.println("buildAccessAddedMethod:\n\n" + getMethod + "\n\n");
//                                    this.classDef.putNewMethod(key, getMethod);
//                                }
                                return false;
                            }
                        }
                    }
                }
            } else if (instruction instanceof DexBackedInstruction3rc) {
                // 函数调用 : invoke-direct/range
                Reference reference = ((DexBackedInstruction3rc) instruction).getReference();
                if (reference instanceof DexBackedMethodReference) {
                    String name = ((DexBackedMethodReference) reference).getName();
                    String returnType = ((DexBackedMethodReference) reference).getReturnType();
                    List<String> parameterTypes = ((DexBackedMethodReference) reference).getParameterTypes();
                    String definingClass = ((DexBackedMethodReference) reference).getDefiningClass();
                    if (opcode == Opcode.INVOKE_SUPER_RANGE) {
                        /*
                            invoke-super/range {p0 .. p5}, Lcom/zpj/hotfix/patch_dev/super_method/Base;->test(....)V
                                ||
                                ||
                                VV
                            invoke-direct/range {p0 .. p5}, Lcom/zpj/hotfix/patch_dev/super_method/Test;->_super_test(....)V
                         */

//                        String fixClass;
//                        String bugClass;
//                        DiffClassInfo classInfo = Patcher.getClassInfo(definingClass);
//                        // 修改或新增的super方法直接调用即可
//                        String superMethodName = "_super_" + name;
//                        if (classInfo != null && classInfo.isFixMethod(name, parameterTypes)) {
//
////                            writer.write("invoke-super/range ");
//
//                            fixClass = classInfo.getFixType();
//                            bugClass = classInfo.getType();
//
//                            String key = returnType + "@" + superMethodName + "@" + parameterTypes;
//                            if (this.classDef.shouldInjectMethod(key)) {
//                                List<String> parameters = new ArrayList<>();
//                                parameters.add(bugClass);
//                                parameters.addAll(parameterTypes);
//                                String getMethod = FixMethodBuilder.buildAccessMethod(superMethodName, name,
//                                        parameters, returnType, bugClass, true);
//                                System.out.println("buildAccessMethod:\n\n" + getMethod + "\n\n");
//                                this.classDef.putNewMethod(key, getMethod);
//                            }
//                        } else {
//                            fixClass = fixType;
//                            bugClass = bugType;
//
//                            String key = returnType + "@" + name + "@" + parameterTypes;
//                            if (this.classDef.shouldInjectMethod(key)) {
//                                String superMethod = FixMethodBuilder.buildAccessSuperMethod(superMethodName, name,
//                                        parameterTypes, returnType, bugClass);
//                                System.out.println("buildAccessSuperMethod:\n\n" + superMethod + "\n\n");
//                                this.classDef.putNewMethod(key, superMethod);
//                            }
//                        }


                        String superMethodName = "_super_" + name;
                        String key = returnType + "@" + name + "@" + parameterTypes;
                        if (this.classDef.shouldInjectMethod(key)) {
                            String superMethod = FixMethodBuilder.buildAccessSuperMethod(superMethodName, name,
                                    parameterTypes, returnType, bugType);
                            System.out.println("buildAccessSuperMethod:\n\n" + superMethod + "\n\n");
                            this.classDef.putNewMethod(key, superMethod);
                        }

                        writer.write("invoke-static/range ");

                        String parameters = String.join("", parameterTypes);
                        ((InstructionMethodItem<?>) methodItem).writeInvokeRangeRegisters(writer);
                        writer.write(", " + fixType + "->" + superMethodName + "(" + bugType + parameters + ")" + returnType);
                        return false;
                    } else if (bugType.equals(definingClass)) {
                        boolean isStatic = (opcode == Opcode.INVOKE_STATIC_RANGE);

                        System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
                        System.out.println("DexBackedMethodReference getName=" + name);
                        System.out.println("DexBackedMethodReference getReturnType=" + returnType);
                        System.out.println("DexBackedMethodReference getParameterTypes=" + parameterTypes);

                        if (!this.classDef.classDef.getClassInfo().isFixMethod(name, parameterTypes)) {
                            String key = returnType + "@" + name + "@" + parameterTypes;
                            if (this.classDef.shouldInjectMethod(key)) {
                                String getMethod = FixMethodBuilder.buildAccessMethod(name, name, parameterTypes, returnType, bugType, isStatic);
                                System.out.println("buildAccessMethod:\n\n" + getMethod + "\n\n");
                                this.classDef.putNewMethod(key, getMethod);
                            }
                        }

                        String parameters;
                        if (isStatic) {
                            parameters = String.join("", parameterTypes);
                        } else {
                            parameters = bugType + String.join("", parameterTypes);
                        }
                        writer.write("invoke-static/range ");
                        ((InstructionMethodItem<?>) methodItem).writeInvokeRangeRegisters(writer);
                        writer.write(", " + fixType + "->" + name + "(" + parameters + ")" + returnType);
                        return false;
                    } else {
                        // TODO 新增方法的调用
                        String clazz = ((DexBackedMethodReference) reference).getDefiningClass();
                        DiffClassInfo info = Patcher.getClassInfo(clazz);
                        if (info != null) {
                            boolean isAddedMethod = info.isAddedMethod(name, parameterTypes);
                            if (isAddedMethod) {
//                                int register = ((DexBackedInstruction3rc) instruction).getStartRegister();
//                                ((DexBackedMethodReference) reference).replaceDefiningClass(info.getFixType());
//                                String methodName = "get_" + info.getFixClassName();
//                                returnType = info.getFixType();
//
////                                writer.write("invoke-static {" + registerStr + "}, " + this.classDef.classDef.getFixType()
////                                        + "->" + methodName + "(" + clazz + ")" + returnType + "\n\n");
//
//                                writer.write("invoke-static {");
//                                registerFormatter.writeTo(writer, register);
//                                writer.write("}, " + this.classDef.classDef.getFixType()
//                                        + "->" + methodName + "(" + clazz + ")" + returnType + "\n\n");
//
//                                writer.write("move-result-object ");
//
//                                registerFormatter.writeTo(writer, register);
//
//                                writer.write("\n\n");
//
//                                String key = returnType + "@" + methodName + "@" + clazz;
//                                if (this.classDef.shouldInjectMethod(key)) {
//                                    String getMethod = FixMethodBuilder.buildAccessAddedMethod(methodName, clazz, returnType);
//                                    System.out.println("buildAccessAddedMethod:\n\n" + getMethod + "\n\n");
//                                    this.classDef.putNewMethod(key, getMethod);
//                                }


                                String parameters = String.join("", parameterTypes);
                                writer.write("invoke-static/range ");
                                ((InstructionMethodItem<?>) methodItem).writeInvokeRangeRegisters(writer);
                                writer.write(", " + info.getFixType()
                                        + "->" + name + "(" + clazz + parameters + ")" + returnType + "\n\n");
                            }
                        }
                    }
                }
            } else if (instruction instanceof DexBackedInstruction22c) {
                return accessField(writer, (DexBackedInstruction22c) instruction);
            } else if (instruction instanceof DexBackedInstruction21c) {
                return accessStaticField(writer, (DexBackedInstruction21c) instruction);
            }
        }
        return true;
    }

    private boolean accessField(IndentingWriter writer, DexBackedInstruction22c instruction) throws IOException {
        Opcode opcode = instruction.getOpcode();
        int registerA = instruction.getRegisterA();
        int registerB = instruction.getRegisterB();
        System.out.println("instruction getRegisterA=" + registerA + " getRegisterB=" + registerB);
        Reference reference = instruction.getReference();
        if (reference instanceof FieldReference) {
            String definingClass = ((FieldReference) reference).getDefiningClass();
            String name = ((FieldReference) reference).getName();
            String type = ((FieldReference) reference).getType();

            System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
            System.out.println("DexBackedMethodReference getName=" + name);
            System.out.println("DexBackedMethodReference type=" + type);
            if (opcode.name.startsWith("iget") && bugType.equals(definingClass)) { // opcode == Opcode.IGET_OBJECT &&
                // TODO 替换方法,
                /**
                 *     iget v0, p0, Lcom/zpj/hotfix/demo/patch_dev/field/Test;->b:I
                 *          ||
                 *          ||
                 *          VV
                 *     invoke-static {p0}, Lcom/zpj/hotfix/demo/patch_dev/field/Test;->getA(Lcom/zpj/hotfix/demo/patch_dev/field/Test;)I
                 *     move-result v0
                 */
                String getMethodName = "_get_" + name;
                String key = type + "@" + getMethodName;

                if (this.classDef.shouldInjectMethod(key)) {
                    String getMethod = FixMethodBuilder.buildGetFieldMethod(getMethodName, name, type, bugType);
                    System.out.println("buildGetMethod:\n\n" + getMethod + "\n\n");
                    this.classDef.putNewMethod(key, getMethod);
                }


//                writer.write("invoke-direct {");
                writer.write("invoke-static {p0}, ");
                // TODO 寄存器
//                registerFormatter.writeFirstInvokeTo(writer, registerB);
//                writer.write("}, ");
                writer.write(fixType);
                writer.write("->");
                writer.write(getMethodName);
                writer.write("(" + bugType + ")");
                writer.write(type);


                writer.write("\n\n");
                if (TypeHelper.isPrimitive(type)) {
                    writer.write("move-result ");
                } else {
                    writer.write("move-result-object ");
                }
                registerFormatter.writeTo(Opcode.INVOKE_STATIC, writer, registerA);
                writer.write("\n\n");

                return false;


            } else if (opcode.name.startsWith("iput") && bugType.equals(definingClass)) { // opcode == Opcode.IPUT_OBJECT

                /**
                 *     iput v1, p0, bug类->字段名:字段类型
                 *     ||
                 *     ||
                 *     VV
                 *     invoke-static {p0, v1}, fix类->设置字段方法(bug类、字段类型)V
                 */
                String setMethodName = "_set_" + name;

                String key = setMethodName + "@" + type;
                if (this.classDef.shouldInjectMethod(key)) {
                    String getMethod = FixMethodBuilder.buildSetFieldMethod(setMethodName, name, type, bugType);
                    System.out.println("buildSetMethod:\n\n" + getMethod + "\n\n");
                    this.classDef.putNewMethod(key, getMethod);
                }


                writer.write("invoke-static {p0, ");
                // TODO 寄存器

//                registerFormatter.writeFirstInvokeTo(writer, registerB);
                registerFormatter.writeTo(Opcode.INVOKE_STATIC, writer, registerA);

                writer.write("}, ");
                writer.write(fixType);
                writer.write("->");
                writer.write(setMethodName);
                writer.write("(");
                writer.write(bugType);
                writer.write(type);
                writer.write(")V");
                writer.write("\n\n");

                return false;
            }
        }
        return true;
    }

    private boolean accessStaticField(IndentingWriter writer, DexBackedInstruction21c instruction) throws IOException {
        Opcode opcode = instruction.getOpcode();
        int registerA = instruction.getRegisterA();
        Reference reference = instruction.getReference();
        if (reference instanceof FieldReference) {
            String definingClass = ((FieldReference) reference).getDefiningClass();
            String name = ((FieldReference) reference).getName();
            String type = ((FieldReference) reference).getType();

            if (opcode.name.startsWith("sget") && bugType.equals(definingClass)) { // opcode == Opcode.IGET_OBJECT &&
                // TODO 替换方法,
                /**
                 *     sget v0, bug类->字段名称:字段类型
                 *              ||
                 *              ||
                 *              vv
                 *     invoke-static {}, 当前类->获取字段方法()字段类型
                 *     move-result v0
                 */
                // TODO 不同的类但是可能有相同name的字段，此时方法名应该区分开
                String getMethodName = "_get_" + name;
                String key = type + "@" + getMethodName;

                if (this.classDef.shouldInjectMethod(key)) {
                    String getMethod = FixMethodBuilder.buildGetStaticFieldMethod(getMethodName, name, type, bugType);
                    System.out.println("buildGetStaticFieldMethod:\n\n" + getMethod + "\n\n");
                    this.classDef.putNewMethod(key, getMethod);
                }

                writer.write("invoke-static {}, " + fixType + "->" + getMethodName + "()" + type);

                writer.write("\n\n");
                if (TypeHelper.isPrimitive(type)) {
                    writer.write("move-result ");
                } else {
                    writer.write("move-result-object ");
                }
                registerFormatter.writeTo(writer, registerA);
                writer.write("\n\n");

                return false;


            } else if (opcode.name.startsWith("sput") && bugType.equals(definingClass)) { // opcode == Opcode.IPUT_OBJECT

                /**
                 *     sput v0, bug类->字段名:字段类型
                 *              ||
                 *              ||
                 *              vv
                 *     invoke-static {v0}, fix类->设置字段方法(字段类型)V
                 */
                String setMethodName = "_set_" + name;

                String key = setMethodName + "@" + type;
                if (this.classDef.shouldInjectMethod(key)) {
                    String getMethod = FixMethodBuilder.buildSetStaticFieldMethod(setMethodName, name, type, bugType);
                    System.out.println("buildSetStaticFieldMethod:\n\n" + getMethod + "\n\n");
                    this.classDef.putNewMethod(key, getMethod);
                }


                writer.write("invoke-static {");
                registerFormatter.writeFirstInvokeTo(writer, registerA);

                writer.write("}, ");
                writer.write(fixType);
                writer.write("->");
                writer.write(setMethodName);
                writer.write("(");
                writer.write(type);
                writer.write(")V");
                writer.write("\n\n");

                return false;
            }
        }

        return true;
    }

    public Instruction findSwitchPayload(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InstructionOffsetMap.InvalidInstructionOffset var5) {
            throw new MethodDefinition.InvalidSwitchPayload(targetOffset);
        }

        Instruction instruction = this.instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            if (instruction.getOpcode() == Opcode.NOP) {
                ++targetIndex;
                if (targetIndex < this.instructions.size()) {
                    instruction = this.instructions.get(targetIndex);
                    if (instruction.getOpcode() == type) {
                        return instruction;
                    }
                }
            }

            throw new MethodDefinition.InvalidSwitchPayload(targetOffset);
        } else {
            return instruction;
        }
    }

    public int findPayloadOffset(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InstructionOffsetMap.InvalidInstructionOffset var5) {
            throw new MethodDefinition.InvalidSwitchPayload(targetOffset);
        }

        Instruction instruction = this.instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            if (instruction.getOpcode() == Opcode.NOP) {
                ++targetIndex;
                if (targetIndex < this.instructions.size()) {
                    instruction = this.instructions.get(targetIndex);
                    if (instruction.getOpcode() == type) {
                        return this.instructionOffsetMap.getInstructionCodeOffset(targetIndex);
                    }
                }
            }

            throw new MethodDefinition.InvalidSwitchPayload(targetOffset);
        } else {
            return targetOffset;
        }
    }

    protected static void writeAccessFlags(IndentingWriter writer, int accessFlags) throws IOException {
        AccessFlags[] var2 = AccessFlags.getAccessFlagsForMethod(accessFlags);
        int var3 = var2.length;

        for (AccessFlags accessFlag : var2) {
            writer.write(accessFlag.toString());
            writer.write(32);
        }

    }

    protected static void writeParameters(IndentingWriter writer, Method method, List<? extends MethodParameter> parameters, baksmaliOptions options) throws IOException {
        boolean isStatic = AccessFlags.STATIC.isSet(method.getAccessFlags());
        int registerNumber = isStatic ? 0 : 1;

        for (MethodParameter parameter : parameters) {
            String parameterType = parameter.getType();
            String parameterName = parameter.getName();
            Collection<? extends Annotation> annotations = parameter.getAnnotations();
            if (options.outputDebugInfo && parameterName != null || annotations.size() != 0) {
                writer.write(".param p");
                writer.printSignedIntAsDec(registerNumber);
                if (parameterName != null && options.outputDebugInfo) {
                    writer.write(", ");
                    ReferenceFormatter.writeStringReference(writer, parameterName);
                }

                writer.write("    # ");
                writer.write(parameterType);
                writer.write("\n");
                if (annotations.size() > 0) {
                    writer.indent(4);
                    String containingClass = null;
                    if (options.useImplicitReferences) {
                        containingClass = method.getDefiningClass();
                    }

                    AnnotationFormatter.writeTo(writer, annotations, containingClass);
                    writer.deindent(4);
                    writer.write(".end param\n");
                }
            }

            ++registerNumber;
            if (TypeUtils.isWideType(parameterType)) {
                ++registerNumber;
            }
        }

    }

    public MethodDefinition.LabelCache getLabelCache() {
        return this.labelCache;
    }

    public int getPackedSwitchBaseAddress(int packedSwitchPayloadCodeOffset) {
        return this.packedSwitchMap.get(packedSwitchPayloadCodeOffset, -1);
    }

    public int getSparseSwitchBaseAddress(int sparseSwitchPayloadCodeOffset) {
        return this.sparseSwitchMap.get(sparseSwitchPayloadCodeOffset, -1);
    }

    protected List<MethodItem> getMethodItems() {
        ArrayList<MethodItem> methodItems = new ArrayList<>();
        if (this.classDef.options.registerInfo == 0 && !this.classDef.options.normalizeVirtualMethods && (!this.classDef.options.deodex || !this.needsAnalyzed())) {
            this.addInstructionMethodItems(methodItems);
        } else {
            this.addAnalyzedInstructionMethodItems(methodItems);
        }

        this.addTries(methodItems);
        if (this.classDef.options.outputDebugInfo) {
            this.addDebugInfo(methodItems);
        }

        if (this.classDef.options.useSequentialLabels) {
            this.setLabelSequentialNumbers();
        }

        methodItems.addAll(this.labelCache.getLabels());

        Collections.sort(methodItems);
        return methodItems;
    }

    private boolean needsAnalyzed() {
        Iterator var1 = this.methodImpl.getInstructions().iterator();

        Instruction instruction;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            instruction = (Instruction) var1.next();
        } while (!instruction.getOpcode().odexOnly());

        return true;
    }

    private void addInstructionMethodItems(List<MethodItem> methodItems) {
        int currentCodeAddress = 0;

        for (int i = 0; i < this.effectiveInstructions.size(); ++i) {
            Instruction instruction = this.effectiveInstructions.get(i);
            MethodItem methodItem = InstructionMethodItemFactory.makeInstructionFormatMethodItem(this, currentCodeAddress, instruction);
            methodItems.add(methodItem);
            if (i != this.effectiveInstructions.size() - 1) {
                methodItems.add(new BlankMethodItem(currentCodeAddress));
            }

            if (this.classDef.options.addCodeOffsets) {
                methodItems.add(new MethodItem(currentCodeAddress) {
                    public double getSortOrder() {
                        return -1000.0D;
                    }

                    public boolean writeTo(IndentingWriter writer) throws IOException {
                        writer.write("#@");
                        writer.printUnsignedLongAsHex((long) this.codeAddress & 4294967295L);
                        return true;
                    }
                });
            }

            if (!this.classDef.options.noAccessorComments && instruction instanceof ReferenceInstruction) {
                Opcode opcode = instruction.getOpcode();
                if (opcode.referenceType == 3) {
                    MethodReference methodReference = null;

                    try {
                        methodReference = (MethodReference) ((ReferenceInstruction) instruction).getReference();
                    } catch (DexBackedDexFile.InvalidItemIndex var9) {
                    }

                    if (methodReference != null && SyntheticAccessorResolver.looksLikeSyntheticAccessor(methodReference.getName())) {
                        SyntheticAccessorResolver.AccessedMember accessedMember = this.classDef.options.syntheticAccessorResolver.getAccessedMember(methodReference);
                        if (accessedMember != null) {
                            methodItems.add(new SyntheticAccessCommentMethodItem(accessedMember, currentCodeAddress));
                        }
                    }
                }
            }

            currentCodeAddress += instruction.getCodeUnits();
        }

    }

    private void addAnalyzedInstructionMethodItems(List<MethodItem> methodItems) {
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(this.classDef.options.classPath, this.method, this.classDef.options.inlineResolver, this.classDef.options.normalizeVirtualMethods);
        AnalysisException analysisException = methodAnalyzer.getAnalysisException();
        if (analysisException != null) {
            methodItems.add(new CommentMethodItem(String.format("AnalysisException: %s", analysisException.getMessage()), analysisException.codeAddress, -2.147483648E9D));
            analysisException.printStackTrace(System.err);
        }

        List<AnalyzedInstruction> instructions = methodAnalyzer.getAnalyzedInstructions();
        int currentCodeAddress = 0;

        for (int i = 0; i < instructions.size(); ++i) {
            AnalyzedInstruction instruction = instructions.get(i);
            MethodItem methodItem = InstructionMethodItemFactory.makeInstructionFormatMethodItem(this, currentCodeAddress, instruction.getInstruction());
            methodItems.add(methodItem);
            if (instruction.getInstruction().getOpcode().format == Format.UnresolvedOdexInstruction) {
                methodItems.add(new CommentedOutMethodItem(InstructionMethodItemFactory.makeInstructionFormatMethodItem(this, currentCodeAddress, instruction.getOriginalInstruction())));
            }

            if (i != instructions.size() - 1) {
                methodItems.add(new BlankMethodItem(currentCodeAddress));
            }

            if (this.classDef.options.addCodeOffsets) {
                methodItems.add(new MethodItem(currentCodeAddress) {
                    public double getSortOrder() {
                        return -1000.0D;
                    }

                    public boolean writeTo(IndentingWriter writer) throws IOException {
                        writer.write("#@");
                        writer.printUnsignedLongAsHex((long) this.codeAddress & 4294967295L);
                        return true;
                    }
                });
            }

            if (this.classDef.options.registerInfo != 0 && !instruction.getInstruction().getOpcode().format.isPayloadFormat) {
                methodItems.add(new PreInstructionRegisterInfoMethodItem(this.classDef.options.registerInfo, methodAnalyzer, this.registerFormatter, instruction, currentCodeAddress));
                methodItems.add(new PostInstructionRegisterInfoMethodItem(this.registerFormatter, instruction, currentCodeAddress));
            }

            currentCodeAddress += instruction.getInstruction().getCodeUnits();
        }

    }

    private void addTries(List<MethodItem> methodItems) {
        List<? extends TryBlock<? extends ExceptionHandler>> tryBlocks = this.methodImpl.getTryBlocks();
        if (tryBlocks.size() != 0) {
            int lastInstructionAddress = this.instructionOffsetMap.getInstructionCodeOffset(this.instructions.size() - 1);
            int codeSize = lastInstructionAddress + this.instructions.get(this.instructions.size() - 1).getCodeUnits();

            for (TryBlock<? extends ExceptionHandler> block : tryBlocks) {
                int startAddress = block.getStartCodeAddress();
                int endAddress = startAddress + block.getCodeUnitCount();
                if (startAddress >= codeSize) {
                    throw new RuntimeException(String.format("Try start offset %d is past the end of the code block.", startAddress));
                }

                if (endAddress > codeSize) {
                    throw new RuntimeException(String.format("Try end offset %d is past the end of the code block.", endAddress));
                }

                int lastCoveredIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(endAddress - 1, false);
                int lastCoveredAddress = this.instructionOffsetMap.getInstructionCodeOffset(lastCoveredIndex);

                for (ExceptionHandler handler : block.getExceptionHandlers()) {
                    int handlerAddress = handler.getHandlerCodeAddress();
                    if (handlerAddress >= codeSize) {
                        throw new ExceptionWithContext("Exception handler offset %d is past the end of the code block.", handlerAddress);
                    }

                    CatchMethodItem catchMethodItem = new CatchMethodItem(this.classDef.options, this.labelCache, lastCoveredAddress, handler.getExceptionType(), startAddress, endAddress, handlerAddress);
                    methodItems.add(catchMethodItem);
                }
            }

        }
    }

    private void addDebugInfo(List<MethodItem> methodItems) {

        for (DebugItem debugItem : this.methodImpl.getDebugItems()) {
            methodItems.add(DebugMethodItem.build(this.registerFormatter, debugItem));
        }

    }

    private void setLabelSequentialNumbers() {
        HashMap<String, Integer> nextLabelSequenceByType = new HashMap();
        ArrayList<LabelMethodItem> sortedLabels = new ArrayList(this.labelCache.getLabels());
        Collections.sort(sortedLabels);

        for (LabelMethodItem labelMethodItem : sortedLabels) {
            Integer labelSequence = nextLabelSequenceByType.get(labelMethodItem.getLabelPrefix());
            if (labelSequence == null) {
                labelSequence = 0;
            }

            labelMethodItem.setLabelSequence(labelSequence);
            nextLabelSequenceByType.put(labelMethodItem.getLabelPrefix(), labelSequence + 1);
        }

    }

    public FixRegisterFormatter getRegisterFormatter() {
        return registerFormatter;
    }

}
