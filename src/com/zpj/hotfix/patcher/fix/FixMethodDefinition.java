package com.zpj.hotfix.patcher.fix;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.utils.FixMethodBuilder;
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
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction22c;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction35c;
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
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.dexlib2.util.SyntheticAccessorResolver;
import org.jf.dexlib2.util.TypeUtils;
import org.jf.util.ExceptionWithContext;
import org.jf.util.IndentingWriter;
import org.jf.util.SparseIntArray;

import java.io.IOException;
import java.util.*;

public class FixMethodDefinition extends MethodDefinition {

    public final FixClassDefinition classDef;
    public final Method method;
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

    public FixMethodDefinition(FixClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        super(classDef, method, methodImpl);
        this.classDef = classDef;
        this.method = method;
        this.methodImpl = methodImpl;

        this.bugType = this.classDef.classDef.getType();
        this.fixType = bugType.substring(0, bugType.length() - 1) + "_Fix;";

        try {
            this.instructions = ImmutableList.copyOf(methodImpl.getInstructions());
            this.methodParameters = ImmutableList.copyOf(method.getParameters());
            this.effectiveInstructions = Lists.newArrayList(this.instructions);
            this.packedSwitchMap = new SparseIntArray(0);
            this.sparseSwitchMap = new SparseIntArray(0);
            this.instructionOffsetMap = new InstructionOffsetMap(this.instructions);
            int endOffset = this.instructionOffsetMap.getInstructionCodeOffset(this.instructions.size() - 1) + ((Instruction) this.instructions.get(this.instructions.size() - 1)).getCodeUnits();

            for (int i = 0; i < this.instructions.size(); ++i) {
                Instruction instruction = (Instruction) this.instructions.get(i);
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
                methodString = ReferenceUtil.getMethodDescriptor(this.classDef.classDef, method, false);
            } catch (Exception var12) {
                throw ExceptionWithContext.withContext(var15, "Error while processing method", new Object[0]);
            }

            throw ExceptionWithContext.withContext(var15, "Error while processing method %s", new Object[]{methodString});
        }
    }

    public static void writeEmptyMethodTo(IndentingWriter writer, Method method, baksmaliOptions options) throws IOException {
        writer.write(".method ");
        writeAccessFlags(writer, method.getAccessFlags());
        writer.write(method.getName());
        writer.write("(");
        ImmutableList<MethodParameter> methodParameters = ImmutableList.copyOf(method.getParameters());
        UnmodifiableIterator var4 = methodParameters.iterator();

        while (var4.hasNext()) {
            MethodParameter parameter = (MethodParameter) var4.next();
            writer.write(parameter.getType());
        }

        writer.write(")");
        writer.write(method.getReturnType());
        writer.write(10);
        writer.indent(4);
        writeParameters(writer, method, methodParameters, options);
        String containingClass = null;
        if (options.useImplicitReferences) {
            containingClass = method.getDefiningClass();
        }

        AnnotationFormatter.writeTo(writer, method.getAnnotations(), containingClass);
        writer.deindent(4);
        writer.write(".end method\n");
    }

    public void writeTo(IndentingWriter writer) throws IOException {
        int parameterRegisterCount = 0;
        if (!AccessFlags.STATIC.isSet(this.method.getAccessFlags())) {
            ++parameterRegisterCount;
        }

        writer.write(".method ");
        writeAccessFlags(writer, this.method.getAccessFlags());
        writer.write(this.method.getName());
        writer.write("(");

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

        boolean addSelfItem = false;

        for (MethodItem methodItem : getMethodItems()) {
            if (methodItem instanceof InstructionMethodItem) {
                Instruction instruction = ((InstructionMethodItem<?>) methodItem).getInstruction();
                Opcode opcode = instruction.getOpcode();
                if (opcode == Opcode.INVOKE_DIRECT || opcode == Opcode.INVOKE_STATIC) {
                    continue;
                }
                if (instruction instanceof ThreeRegisterInstruction) {
                    int a = ((ThreeRegisterInstruction) instruction).getRegisterA();
                    int b = ((ThreeRegisterInstruction) instruction).getRegisterB();
                    int c = ((ThreeRegisterInstruction) instruction).getRegisterC();


//                    if (!this.classDef.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {
//                        writer.write(112);
//                        writer.printSignedIntAsDec(register - (this.registerCount - this.parameterRegisterCount));
//                    } else {
//                        writer.write(118);
//                        writer.printSignedIntAsDec(register);
//                    }

                    addSelfItem = registerFormatter.shouldAddSelfItem(a, b, c);

                } else if (instruction instanceof TwoRegisterInstruction) {
                    int a = ((TwoRegisterInstruction) instruction).getRegisterA();
                    int b = ((TwoRegisterInstruction) instruction).getRegisterB();
                    addSelfItem = registerFormatter.shouldAddSelfItem(a, b);
                } else if (instruction instanceof OneRegisterInstruction) {
                    int a = ((OneRegisterInstruction) instruction).getRegisterA();
                    addSelfItem = registerFormatter.shouldAddSelfItem(a);
                } else if (instruction instanceof FiveRegisterInstruction) {
                    FiveRegisterInstruction fiveInstruction = (FiveRegisterInstruction) instruction;
                    int regCount = fiveInstruction.getRegisterCount();

                    switch (regCount) {
                        case 1:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterC());
                            break;
                        case 2:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterC(),
                                    fiveInstruction.getRegisterD());
                            break;
                        case 3:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterC(),
                                    fiveInstruction.getRegisterD(), fiveInstruction.getRegisterE());
                            break;
                        case 4:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterC(),
                                    fiveInstruction.getRegisterD(), fiveInstruction.getRegisterE(),
                                    fiveInstruction.getRegisterF());
                            break;
                        case 5:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterC(),
                                    fiveInstruction.getRegisterD(), fiveInstruction.getRegisterE(),
                                    fiveInstruction.getRegisterF(), fiveInstruction.getRegisterG());
                            break;
                    }
                } else if (instruction instanceof OneFixedFourParameterRegisterInstruction) {
                    OneFixedFourParameterRegisterInstruction fiveInstruction = (OneFixedFourParameterRegisterInstruction) instruction;
                    int regCount = fiveInstruction.getRegisterCount();

                    switch (regCount) {
                        case 1:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterFixedC());
                            break;
                        case 2:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterFixedC(),
                                    fiveInstruction.getRegisterParameterD());
                            break;
                        case 3:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterFixedC(),
                                    fiveInstruction.getRegisterParameterD(), fiveInstruction.getRegisterParameterE());
                            break;
                        case 4:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterFixedC(),
                                    fiveInstruction.getRegisterParameterD(), fiveInstruction.getRegisterParameterE(),
                                    fiveInstruction.getRegisterParameterF());
                            break;
                        case 5:
                            addSelfItem = registerFormatter.shouldAddSelfItem(fiveInstruction.getRegisterFixedC(),
                                    fiveInstruction.getRegisterParameterD(), fiveInstruction.getRegisterParameterE(),
                                    fiveInstruction.getRegisterParameterF(), fiveInstruction.getRegisterParameterG());
                            break;
                    }

                } else if (instruction instanceof RegisterRangeInstruction){
                    RegisterRangeInstruction rangeInstruction = (RegisterRangeInstruction) instruction;
                    if (rangeInstruction.getRegisterCount() != 0) {
                        int startRegister = rangeInstruction.getStartRegister();
                        int[] registers = new int[rangeInstruction.getRegisterCount()];
                        for (int i = 0; i < registers.length; i++) {
                            registers[i] = startRegister + i;
                        }
                        addSelfItem = registerFormatter.shouldAddSelfItem(registers);
                    }
                }


                if (addSelfItem) {
                    System.out.println(method.getName() + " opname=" + opcode.name + " addSelfItem=" + addSelfItem);
                    break;
                }


            }


        }


        if (this.classDef.options.useLocalsDirective) {
            writer.write(".locals ");
            if (addSelfItem) {
                writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() - parameterRegisterCount + 1);
            } else {
                writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() - parameterRegisterCount);
            }
        } else {
            writer.write(".registers ");
            if (addSelfItem) {
                writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() + 1);
            } else {
                writer.printSignedIntAsDec(this.methodImpl.getRegisterCount());
            }
        }

        writer.write(10);
        writeParameters(writer, this.method, this.methodParameters, this.classDef.options);
//        if (this.registerFormatter == null) {
//            this.registerFormatter = new RegisterFormatter(this.classDef.options, this.methodImpl.getRegisterCount(), parameterRegisterCount);
//        }

        String containingClass = null;
        if (this.classDef.options.useImplicitReferences) {
            containingClass = this.method.getDefiningClass();
        }

        AnnotationFormatter.writeTo(writer, this.method.getAnnotations(), containingClass);
        writer.write(10);
        List<MethodItem> methodItems = this.getMethodItems();


//        System.out.println("FieldReference bugType=" + bugType);
//        System.out.println("FieldReference fixType=" + fixType);
        System.out.println("methodName=" + method.getName() + " addSelfItem=" + addSelfItem);

        registerFormatter.setAddSelfItem(addSelfItem);

        writer.write("iget-object v0, p0, " + fixType + "->mBugObj:" + bugType);
        writer.write(10);
        writer.write(10);
        writer.write(".local v0, \"_thisBugObj\":" + bugType);
        writer.write(10);
        writer.write(10);

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
                    String definingClass = ((DexBackedMethodReference) reference).getDefiningClass();
                    if (bugType.equals(definingClass)) {
                        // TODO add new method in fix class

                        String name = ((DexBackedMethodReference) reference).getName();
                        String returnType = ((DexBackedMethodReference) reference).getReturnType();
                        List<String> parameterTypes = ((DexBackedMethodReference) reference).getParameterTypes();

                        boolean isStatic = (opcode == Opcode.INVOKE_STATIC);


                        System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
                        System.out.println("DexBackedMethodReference getName=" + name);
                        System.out.println("DexBackedMethodReference getReturnType=" + returnType);
                        System.out.println("DexBackedMethodReference getParameterTypes=" + parameterTypes);

                        // TODO 替换方法
                        String key = returnType + "@" + name + "@" + parameterTypes;
                        if (this.classDef.shouldInjectMethod(key)) {
                            String getMethod = FixMethodBuilder.buildAccessMethod(name, parameterTypes, returnType, bugType, fixType, isStatic);
                            System.out.println("buildAccessMethod:\n\n" + getMethod + "\n\n");
                            this.classDef.putNewMethod(key, getMethod);
                        }

                        return true;
                    }
                }
            } else if (instruction instanceof DexBackedInstruction22c) {
                int registerA = ((DexBackedInstruction22c) instruction).getRegisterA();
                int registerB = ((DexBackedInstruction22c) instruction).getRegisterB();
                System.out.println("instruction getRegisterA=" + registerA + " getRegisterB=" + registerB);
                Reference reference = ((DexBackedInstruction22c) instruction).getReference();
                if (reference instanceof FieldReference) {
                    String definingClass = ((FieldReference) reference).getDefiningClass();
                    String name = ((FieldReference) reference).getName();
                    String type = ((FieldReference) reference).getType();

                    System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
                    System.out.println("DexBackedMethodReference getName=" + name);
                    System.out.println("DexBackedMethodReference type=" + type);
                    if (opcode == Opcode.IGET_OBJECT && bugType.equals(definingClass)) {
                        // TODO 替换方法,
                        /**
                         *     iget-object v0, p0, bug类->字段名称:字段类型
                         *              ||
                         *              ||
                         *              vv
                         *     invoke-direct {p0}, fix类->获取字段方法名()字段类型
                         *     move-result-object v0
                         */
                        String getMethodName = "_get_" + name;
                        String key = type + "@" + getMethodName;

                        if (this.classDef.shouldInjectMethod(key)) {
                            String getMethod = FixMethodBuilder.buildGetMethod(getMethodName, name, type, bugType, fixType);
                            System.out.println("buildGetMethod:\n\n" + getMethod + "\n\n");
                            this.classDef.putNewMethod(key, getMethod);
                        }


                        writer.write("invoke-direct {");
                        // TODO 寄存器
                        registerFormatter.writeTo(Opcode.INVOKE_DIRECT, writer, registerB);
                        writer.write("}, ");
                        writer.write(fixType);
                        writer.write("->");
                        writer.write(getMethodName);
                        writer.write("()");
                        writer.write(type);


                        writer.write("\n\n");
                        writer.write("move-result-object ");
                        registerFormatter.writeTo(Opcode.INVOKE_DIRECT, writer, registerA);
                        writer.write("\n\n");

                        return false;


                    } else if (opcode == Opcode.IPUT_OBJECT && bugType.equals(definingClass)) {

                        /**
                         *     iput-object v0, p0, bug类->字段名:字段类型
                         *              ||
                         *              ||
                         *              vv
                         *     invoke-direct {p0, v0}, fix类->设置字段方法(字段类型)V
                         */
                        String setMethodName = "_set_" + name;

                        String key = setMethodName + "@" + type;
                        if (this.classDef.shouldInjectMethod(key)) {
                            String getMethod = FixMethodBuilder.buildSetMethod(setMethodName, name, bugType, fixType);
                            System.out.println("buildSetMethod:\n\n" + getMethod + "\n\n");
                            this.classDef.putNewMethod(key, getMethod);
                        }


                        writer.write("invoke-direct {");
                        // TODO 寄存器

                        registerFormatter.writeTo(Opcode.INVOKE_DIRECT, writer, registerB);
                        registerFormatter.writeTo(Opcode.INVOKE_DIRECT, writer, registerA);

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

        Instruction instruction = (Instruction) this.instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            if (instruction.getOpcode() == Opcode.NOP) {
                ++targetIndex;
                if (targetIndex < this.instructions.size()) {
                    instruction = (Instruction) this.instructions.get(targetIndex);
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

        Instruction instruction = (Instruction) this.instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            if (instruction.getOpcode() == Opcode.NOP) {
                ++targetIndex;
                if (targetIndex < this.instructions.size()) {
                    instruction = (Instruction) this.instructions.get(targetIndex);
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

    private static void writeAccessFlags(IndentingWriter writer, int accessFlags) throws IOException {
        AccessFlags[] var2 = AccessFlags.getAccessFlagsForMethod(accessFlags);
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            AccessFlags accessFlag = var2[var4];
            writer.write(accessFlag.toString());
            writer.write(32);
        }

    }

    private static void writeParameters(IndentingWriter writer, Method method, List<? extends MethodParameter> parameters, baksmaliOptions options) throws IOException {
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

    private List<MethodItem> getMethodItems() {
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
            Instruction instruction = (Instruction) this.effectiveInstructions.get(i);
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
            AnalyzedInstruction instruction = (AnalyzedInstruction) instructions.get(i);
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
            int codeSize = lastInstructionAddress + ((Instruction) this.instructions.get(this.instructions.size() - 1)).getCodeUnits();
            Iterator var5 = tryBlocks.iterator();

            while (var5.hasNext()) {
                TryBlock<? extends ExceptionHandler> tryBlock = (TryBlock) var5.next();
                int startAddress = tryBlock.getStartCodeAddress();
                int endAddress = startAddress + tryBlock.getCodeUnitCount();
                if (startAddress >= codeSize) {
                    throw new RuntimeException(String.format("Try start offset %d is past the end of the code block.", startAddress));
                }

                if (endAddress > codeSize) {
                    throw new RuntimeException(String.format("Try end offset %d is past the end of the code block.", endAddress));
                }

                int lastCoveredIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(endAddress - 1, false);
                int lastCoveredAddress = this.instructionOffsetMap.getInstructionCodeOffset(lastCoveredIndex);
                Iterator var11 = tryBlock.getExceptionHandlers().iterator();

                while (var11.hasNext()) {
                    ExceptionHandler handler = (ExceptionHandler) var11.next();
                    int handlerAddress = handler.getHandlerCodeAddress();
                    if (handlerAddress >= codeSize) {
                        throw new ExceptionWithContext("Exception handler offset %d is past the end of the code block.", new Object[]{handlerAddress});
                    }

                    CatchMethodItem catchMethodItem = new CatchMethodItem(this.classDef.options, this.labelCache, lastCoveredAddress, handler.getExceptionType(), startAddress, endAddress, handlerAddress);
                    methodItems.add(catchMethodItem);
                }
            }

        }
    }

    private void addDebugInfo(List<MethodItem> methodItems) {
        Iterator var2 = this.methodImpl.getDebugItems().iterator();

        while (var2.hasNext()) {
            DebugItem debugItem = (DebugItem) var2.next();
            methodItems.add(DebugMethodItem.build(this.registerFormatter, debugItem));
        }

    }

    private void setLabelSequentialNumbers() {
        HashMap<String, Integer> nextLabelSequenceByType = new HashMap();
        ArrayList<LabelMethodItem> sortedLabels = new ArrayList(this.labelCache.getLabels());
        Collections.sort(sortedLabels);
        Iterator var3 = sortedLabels.iterator();

        while (var3.hasNext()) {
            LabelMethodItem labelMethodItem = (LabelMethodItem) var3.next();
            Integer labelSequence = (Integer) nextLabelSequenceByType.get(labelMethodItem.getLabelPrefix());
            if (labelSequence == null) {
                labelSequence = 0;
            }

            labelMethodItem.setLabelSequence(labelSequence);
            nextLabelSequenceByType.put(labelMethodItem.getLabelPrefix(), labelSequence + 1);
        }

    }

    public static class InvalidSwitchPayload extends ExceptionWithContext {
        private final int payloadOffset;

        public InvalidSwitchPayload(int payloadOffset) {
            super("No switch payload at offset: %d", new Object[]{payloadOffset});
            this.payloadOffset = payloadOffset;
        }
    }

    public static class LabelCache {
        protected HashMap<LabelMethodItem, LabelMethodItem> labels = new HashMap();

        public LabelCache() {
        }

        public LabelMethodItem internLabel(LabelMethodItem labelMethodItem) {
            LabelMethodItem internedLabelMethodItem = (LabelMethodItem) this.labels.get(labelMethodItem);
            if (internedLabelMethodItem != null) {
                return internedLabelMethodItem;
            } else {
                this.labels.put(labelMethodItem, labelMethodItem);
                return labelMethodItem;
            }
        }

        public Collection<LabelMethodItem> getLabels() {
            return this.labels.values();
        }
    }

    public FixRegisterFormatter getRegisterFormatter() {
        return registerFormatter;
    }

}
