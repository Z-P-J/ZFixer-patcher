//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.baksmali.Adaptors.Format;

import com.zpj.hotfix.patcher.fix.FixClassDef;
import com.zpj.hotfix.patcher.fix.FixRegisterFormatter;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.baksmali.Adaptors.MethodDefinition.InvalidSwitchPayload;
import org.jf.baksmali.Adaptors.MethodItem;
import org.jf.baksmali.Adaptors.RegisterFormatter;
import org.jf.baksmali.Renderers.LongRenderer;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.ReferenceType;
import org.jf.dexlib2.ReferenceType.InvalidReferenceTypeException;
import org.jf.dexlib2.VerificationError;
import org.jf.dexlib2.dexbacked.DexBackedDexFile.InvalidItemIndex;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction22c;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.instruction.*;
import org.jf.dexlib2.iface.instruction.formats.Instruction20bc;
import org.jf.dexlib2.iface.instruction.formats.Instruction31t;
import org.jf.dexlib2.iface.instruction.formats.UnknownInstruction;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.reference.Reference;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.util.ExceptionWithContext;
import org.jf.util.IndentingWriter;
import org.jf.util.NumberUtils;

import java.io.IOException;
import java.util.Map;

public class InstructionMethodItem<T extends Instruction> extends MethodItem {
    protected final MethodDefinition methodDef;
    protected final T instruction;

    private final String bugType;
    private final String fixType;

    public InstructionMethodItem(MethodDefinition methodDef, int codeAddress, T instruction) {
        super(codeAddress);
        this.methodDef = methodDef;
        this.instruction = instruction;

        this.bugType = this.methodDef.classDef.classDef.getType();
        this.fixType = bugType.substring(0, bugType.length() - 1) + "_Fix;";
    }

    public T getInstruction() {
        return instruction;
    }

    public double getSortOrder() {
        return 100.0D;
    }

    private boolean isAllowedOdex(Opcode opcode) {
        baksmaliOptions options = this.methodDef.classDef.options;
        if (options.allowOdex) {
            return true;
        } else if (this.methodDef.classDef.options.apiLevel >= 14) {
            return false;
        } else {
            return opcode.isVolatileFieldAccessor() || opcode == Opcode.THROW_VERIFICATION_ERROR;
        }
    }

    public boolean writeTo(IndentingWriter writer) throws IOException {

        Opcode opcode = this.instruction.getOpcode();

        System.out.println("instruction=" + this.instruction);
        System.out.println("instruction name=" + opcode.name);
        System.out.println("instruction format=" + opcode.format);


//        if (this.instruction instanceof DexBackedInstruction35c) { // opcode == Opcode.INVOKE_DIRECT
//            // 函数调用 : invoke-direct
//            DexBackedInstruction35c instruction = (DexBackedInstruction35c) this.instruction;
//            Reference reference = instruction.getReference();
////            System.out.println("instruction reference=" + reference);
//            if (reference instanceof DexBackedMethodReference) {
//                System.out.println("FieldReference bugType=" + bugType);
//                System.out.println("FieldReference fixType=" + fixType);
//
//                if (bugType.equals(((DexBackedMethodReference) reference).getDefiningClass())) {
//                    // TODO add new method in fix class
//
//
//
//                    String definingClass = ((DexBackedMethodReference) reference).getDefiningClass();
//                    String name = ((DexBackedMethodReference) reference).getName();
//                    String returnType = ((DexBackedMethodReference) reference).getReturnType();
//                    List<String> parameterTypes = ((DexBackedMethodReference) reference).getParameterTypes();
//
//
//                    System.out.println("DexBackedMethodReference getDefiningClass=" + definingClass);
//                    System.out.println("DexBackedMethodReference getName=" + name);
//                    System.out.println("DexBackedMethodReference getReturnType=" + returnType);
//                    System.out.println("DexBackedMethodReference getParameterTypes=" + parameterTypes);
//
//                    if (bugType.equals(definingClass)) {
//                        // TODO替换方法
//                        String getMethod = FixMethodBuilder.buildGetMethod(name, parameterTypes, returnType, bugType, fixType);
//                        System.out.println("getMethod:\n\n" + getMethod + "\n\n");
//                        Patcher.addNewMethod(getMethod);
//                    }
//
//
//                }
//            }
//
//
//        }

        if (this.instruction instanceof DexBackedInstruction22c) {
            // TODO
            DexBackedInstruction22c instruction = (DexBackedInstruction22c)this.instruction;

            System.out.println("instruction getRegisterA=" + instruction.getRegisterA() + " getRegisterB=" + instruction.getRegisterB());

            Reference reference = instruction.getReference();
            if (reference instanceof FieldReference) {
                System.out.println("FieldReference getDefiningClass=" + ((FieldReference) reference).getDefiningClass());
                System.out.println("FieldReference getName=" + ((FieldReference) reference).getName());
                System.out.println("FieldReference getType=" + ((FieldReference) reference).getType());
            }

        }

        String verificationErrorName = null;
        String referenceString = null;
        boolean commentOutInstruction = false;
        if (this.instruction instanceof Instruction20bc) {
            int verificationError = ((Instruction20bc)this.instruction).getVerificationError();
            verificationErrorName = VerificationError.getVerificationErrorName(verificationError);
            if (verificationErrorName == null) {
                writer.write("#was invalid verification error type: ");
                writer.printSignedIntAsDec(verificationError);
                writer.write("\n");
                verificationErrorName = "generic-error";
            }
        }

        if (this.instruction instanceof ReferenceInstruction) {
            ReferenceInstruction referenceInstruction = (ReferenceInstruction)this.instruction;

            try {
                Reference reference = referenceInstruction.getReference();
                String classContext = null;
                if (this.methodDef.classDef.options.useImplicitReferences) {
                    classContext = this.methodDef.method.getDefiningClass();
                }


                ClassDef classDef = this.methodDef.classDef.classDef;
                if (classDef instanceof FixClassDef) {
                    referenceString = ReferenceUtil.getReferenceString((FixClassDef) classDef, reference, classContext);
                } else {
                    referenceString = ReferenceUtil.getReferenceString(opcode, reference, classContext);
                }
                System.out.println("referenceString=" + referenceString);
//                if (this.methodDef.method.getName().equals("<clinit>")) {
//                    String clazz = this.methodDef.method.getDefiningClass();
//                    if (Patcher.getModifiedClasses(clazz) != null) {
//                        referenceString = referenceString.replace(clazz, TypeGenUtil.newType(clazz));
//                    }
//
//                }

                assert referenceString != null;
            } catch (InvalidItemIndex var10) {
                writer.write("#");
                writer.write(var10.getMessage());
                writer.write("\n");
                commentOutInstruction = true;
                referenceString = String.format("%s@%d", ReferenceType.toString(referenceInstruction.getReferenceType()), var10.getInvalidIndex());
            } catch (InvalidReferenceTypeException var11) {
                writer.write("#invalid reference type: ");
                writer.printSignedIntAsDec(var11.getReferenceType());
                commentOutInstruction = true;
                referenceString = "invalid_reference";
            }
        }



        boolean isResourceId;
        if (this.instruction instanceof Instruction31t) {
            isResourceId = true;
            int baseAddress;
            switch(this.instruction.getOpcode()) {
                case PACKED_SWITCH:
                    baseAddress = this.methodDef.getPackedSwitchBaseAddress(this.codeAddress + ((Instruction31t)this.instruction).getCodeOffset());
                    if (baseAddress == -1) {
                        isResourceId = false;
                    }
                    break;
                case SPARSE_SWITCH:
                    baseAddress = this.methodDef.getSparseSwitchBaseAddress(this.codeAddress + ((Instruction31t)this.instruction).getCodeOffset());
                    if (baseAddress == -1) {
                        isResourceId = false;
                    }
                    break;
                case FILL_ARRAY_DATA:
                    try {
                        this.methodDef.findPayloadOffset(this.codeAddress + ((Instruction31t)this.instruction).getCodeOffset(), Opcode.ARRAY_PAYLOAD);
                    } catch (InvalidSwitchPayload var9) {
                        isResourceId = false;
                    }
                    break;
                default:
                    throw new ExceptionWithContext("Invalid 31t opcode: %s", new Object[]{this.instruction.getOpcode()});
            }

            if (!isResourceId) {
                writer.write("#invalid payload reference\n");
                commentOutInstruction = true;
            }
        }

        if (opcode.odexOnly() && !this.isAllowedOdex(opcode)) {
            writer.write("#disallowed odex opcode\n");
            commentOutInstruction = true;
        }

        if (commentOutInstruction) {
            writer.write("#");
        }

        switch(this.instruction.getOpcode().format) {
            case Format10t:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeTargetLabel(writer);
                break;
            case Format10x:
                if (this.instruction instanceof UnknownInstruction) {
                    writer.write("#unknown opcode: 0x");
                    writer.printUnsignedLongAsHex((long)((UnknownInstruction)this.instruction).getOriginalOpcode());
                    writer.write(10);
                }

                this.writeOpcode(writer);
                break;
            case Format11n:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeLiteral(writer);
                break;
            case Format11x:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                break;
            case Format12x:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                break;
            case Format20bc:
                this.writeOpcode(writer);
                writer.write(32);
                writer.write(verificationErrorName);
                writer.write(", ");
                writer.write(referenceString);
                break;
            case Format20t:
            case Format30t:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeTargetLabel(writer);
                break;
            case Format21c:
            case Format31c:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                writer.write(referenceString);
                break;
            case Format21ih:
            case Format21lh:
            case Format21s:
            case Format31i:
            case Format51l:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeLiteral(writer);
                if (this.instruction.getOpcode().setsWideRegister()) {
                    this.writeCommentIfLikelyDouble(writer);
                } else {
                    isResourceId = this.writeCommentIfResourceId(writer);
                    if (!isResourceId) {
                        this.writeCommentIfLikelyFloat(writer);
                    }
                }
                break;
            case Format21t:
            case Format31t:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeTargetLabel(writer);
                break;
            case Format22b:
            case Format22s:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                writer.write(", ");
                this.writeLiteral(writer);
                break;
            case Format22c:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                writer.write(", ");
                writer.write(referenceString);
                break;
            case Format22cs:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                writer.write(", ");
                this.writeFieldOffset(writer);
                break;
            case Format22t:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                writer.write(", ");
                this.writeTargetLabel(writer);
                break;
            case Format22x:
            case Format32x:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                break;
            case Format23x:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeFirstRegister(writer);
                writer.write(", ");
                this.writeSecondRegister(writer);
                writer.write(", ");
                this.writeThirdRegister(writer);
                break;
            case Format25x:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvoke25xRegisters(writer);
                break;
            case Format35c:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRegisters(writer);
                writer.write(", ");
                writer.write(referenceString);
                break;
            case Format35mi:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRegisters(writer);
                writer.write(", ");
                this.writeInlineIndex(writer);
                break;
            case Format35ms:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRegisters(writer);
                writer.write(", ");
                this.writeVtableIndex(writer);
                break;
            case Format3rc:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRangeRegisters(writer);
                writer.write(", ");
                writer.write(referenceString);
                break;
            case Format3rmi:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRangeRegisters(writer);
                writer.write(", ");
                this.writeInlineIndex(writer);
                break;
            case Format3rms:
                this.writeOpcode(writer);
                writer.write(32);
                this.writeInvokeRangeRegisters(writer);
                writer.write(", ");
                this.writeVtableIndex(writer);
                break;
            default:
                assert false;

                return false;
        }

        if (commentOutInstruction) {
            writer.write("\nnop");
        }

        return true;
    }

    protected void writeOpcode(IndentingWriter writer) throws IOException {
        writer.write(this.instruction.getOpcode().name);
    }

    protected void writeTargetLabel(IndentingWriter writer) throws IOException {
        throw new RuntimeException();
    }

    protected void writeRegister(IndentingWriter writer, int registerNumber) throws IOException {
        RegisterFormatter formatter = this.methodDef.getRegisterFormatter();
        if (formatter instanceof FixRegisterFormatter) {
            System.out.println("writeRegister methodName=" + this.methodDef.method.getName() + " name=" + this.instruction.getOpcode().name + " registerNumber=" + registerNumber);
            ((FixRegisterFormatter) formatter).writeTo(this.instruction.getOpcode(), writer, registerNumber);
        } else {
            formatter.writeTo(writer, registerNumber);
        }
    }

    protected void writeFirstInvokeRegister(IndentingWriter writer, int registerNumber) throws IOException {
        RegisterFormatter formatter = this.methodDef.getRegisterFormatter();
        if (formatter instanceof FixRegisterFormatter) {
            ((FixRegisterFormatter) formatter).writeFirstInvokeTo(writer, registerNumber);
        } else {
            formatter.writeTo(writer, registerNumber);
        }
    }

    protected void writeFirstRegister(IndentingWriter writer) throws IOException {
        this.writeRegister(writer, ((OneRegisterInstruction)this.instruction).getRegisterA());
    }

    protected void writeSecondRegister(IndentingWriter writer) throws IOException {
        this.writeRegister(writer, ((TwoRegisterInstruction)this.instruction).getRegisterB());
    }

    protected void writeThirdRegister(IndentingWriter writer) throws IOException {
        this.writeRegister(writer, ((ThreeRegisterInstruction)this.instruction).getRegisterC());
    }

    public void writeInvokeRegisters(IndentingWriter writer) throws IOException {
        FiveRegisterInstruction instruction = (FiveRegisterInstruction)this.instruction;
        int regCount = instruction.getRegisterCount();
        writer.write(123);
        switch(regCount) {
            case 1:
                this.writeFirstInvokeRegister(writer, instruction.getRegisterC());
                break;
            case 2:
                this.writeFirstInvokeRegister(writer, instruction.getRegisterC());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterD());
                break;
            case 3:
                this.writeFirstInvokeRegister(writer, instruction.getRegisterC());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterE());
                break;
            case 4:
                this.writeFirstInvokeRegister(writer, instruction.getRegisterC());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterE());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterF());
                break;
            case 5:
                this.writeFirstInvokeRegister(writer, instruction.getRegisterC());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterE());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterF());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterG());
        }

        writer.write(125);
    }

    protected void writeInvoke25xRegisters(IndentingWriter writer) throws IOException {
        // invoke-lambda
        OneFixedFourParameterRegisterInstruction instruction = (OneFixedFourParameterRegisterInstruction)this.instruction;
        int parameterRegCount = instruction.getParameterRegisterCount();
        this.writeRegister(writer, instruction.getRegisterFixedC());
        writer.write(", {");
        switch(parameterRegCount) {
            case 1:
                this.writeRegister(writer, instruction.getRegisterParameterD());
                break;
            case 2:
                this.writeRegister(writer, instruction.getRegisterParameterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterE());
                break;
            case 3:
                this.writeRegister(writer, instruction.getRegisterParameterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterE());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterF());
                break;
            case 4:
                this.writeRegister(writer, instruction.getRegisterParameterD());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterE());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterF());
                writer.write(", ");
                this.writeRegister(writer, instruction.getRegisterParameterG());
        }

        writer.write(125);
    }

    public void writeInvokeRangeRegisters(IndentingWriter writer) throws IOException {
        RegisterRangeInstruction instruction = (RegisterRangeInstruction)this.instruction;
        int regCount = instruction.getRegisterCount();
        if (regCount == 0) {
            writer.write("{}");
        } else {
            int startRegister = instruction.getStartRegister();
            this.methodDef.getRegisterFormatter().writeRegisterRange(writer, startRegister, startRegister + regCount - 1);
        }

    }

    protected void writeLiteral(IndentingWriter writer) throws IOException {
        LongRenderer.writeSignedIntOrLongTo(writer, ((WideLiteralInstruction)this.instruction).getWideLiteral());
    }

    protected void writeCommentIfLikelyFloat(IndentingWriter writer) throws IOException {
        this.writeCommentIfLikelyFloat(writer, ((NarrowLiteralInstruction)this.instruction).getNarrowLiteral());
    }

    protected void writeCommentIfLikelyFloat(IndentingWriter writer, int val) throws IOException {
        if (NumberUtils.isLikelyFloat(val)) {
            writer.write("    # ");
            float fval = Float.intBitsToFloat(val);
            if (fval == 1.0F / 0.0) {
                writer.write("Float.POSITIVE_INFINITY");
            } else if (fval == -1.0F / 0.0) {
                writer.write("Float.NEGATIVE_INFINITY");
            } else if (fval == 0.0F / 0.0) {
                writer.write("Float.NaN");
            } else if (fval == 3.4028235E38F) {
                writer.write("Float.MAX_VALUE");
            } else if (fval == 3.1415927F) {
                writer.write("(float)Math.PI");
            } else if (fval == 2.7182817F) {
                writer.write("(float)Math.E");
            } else {
                writer.write(Float.toString(fval));
                writer.write(102);
            }
        }

    }

    protected void writeCommentIfLikelyDouble(IndentingWriter writer) throws IOException {
        this.writeCommentIfLikelyDouble(writer, ((WideLiteralInstruction)this.instruction).getWideLiteral());
    }

    protected void writeCommentIfLikelyDouble(IndentingWriter writer, long val) throws IOException {
        if (NumberUtils.isLikelyDouble(val)) {
            writer.write("    # ");
            double dval = Double.longBitsToDouble(val);
            if (dval == 1.0D / 0.0) {
                writer.write("Double.POSITIVE_INFINITY");
            } else if (dval == -1.0D / 0.0) {
                writer.write("Double.NEGATIVE_INFINITY");
            } else if (dval == 0.0D / 0.0) {
                writer.write("Double.NaN");
            } else if (dval == 1.7976931348623157E308D) {
                writer.write("Double.MAX_VALUE");
            } else if (dval == 3.141592653589793D) {
                writer.write("Math.PI");
            } else if (dval == 2.718281828459045D) {
                writer.write("Math.E");
            } else {
                writer.write(Double.toString(dval));
            }
        }

    }

    protected boolean writeCommentIfResourceId(IndentingWriter writer) throws IOException {
        return this.writeCommentIfResourceId(writer, ((NarrowLiteralInstruction)this.instruction).getNarrowLiteral());
    }

    protected boolean writeCommentIfResourceId(IndentingWriter writer, int val) throws IOException {
        Map<Integer, String> resourceIds = this.methodDef.classDef.options.resourceIds;
        String resource = (String)resourceIds.get(val);
        if (resource != null) {
            writer.write("    # ");
            writer.write(resource);
            return true;
        } else {
            return false;
        }
    }

    protected void writeFieldOffset(IndentingWriter writer) throws IOException {
        writer.write("field@0x");
        writer.printUnsignedLongAsHex((long)((FieldOffsetInstruction)this.instruction).getFieldOffset());
    }

    protected void writeInlineIndex(IndentingWriter writer) throws IOException {
        writer.write("inline@");
        writer.printSignedIntAsDec(((InlineIndexInstruction)this.instruction).getInlineIndex());
    }

    protected void writeVtableIndex(IndentingWriter writer) throws IOException {
        writer.write("vtable@");
        writer.printSignedIntAsDec(((VtableIndexInstruction)this.instruction).getVtableIndex());
    }
}
