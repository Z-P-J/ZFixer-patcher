//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.baksmali.Adaptors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.zpj.hotfix.patcher.utils.MethodUtils;
import org.jf.baksmali.baksmaliOptions;
import org.jf.baksmali.Adaptors.Debug.DebugMethodItem;
import org.jf.baksmali.Adaptors.Format.InstructionMethodItemFactory;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.analysis.AnalysisException;
import org.jf.dexlib2.analysis.AnalyzedInstruction;
import org.jf.dexlib2.analysis.MethodAnalyzer;
import org.jf.dexlib2.dexbacked.DexBackedDexFile.InvalidItemIndex;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.ExceptionHandler;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.dexlib2.iface.TryBlock;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OffsetInstruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction31t;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.immutable.instruction.ImmutableInstruction31t;
import org.jf.dexlib2.util.InstructionOffsetMap;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.dexlib2.util.SyntheticAccessorResolver;
import org.jf.dexlib2.util.TypeUtils;
import org.jf.dexlib2.util.InstructionOffsetMap.InvalidInstructionOffset;
import org.jf.dexlib2.util.SyntheticAccessorResolver.AccessedMember;
import org.jf.util.ExceptionWithContext;
import org.jf.util.IndentingWriter;
import org.jf.util.SparseIntArray;

public class MethodDefinition {
    public final ClassDefinition classDef;
    public final Method method;
    public final MethodImplementation methodImpl;
    public final ImmutableList<Instruction> instructions;
    public final List<Instruction> effectiveInstructions;
    public final ImmutableList<MethodParameter> methodParameters;
    public RegisterFormatter registerFormatter;
    private final LabelCache labelCache = new LabelCache();
    private final SparseIntArray packedSwitchMap;
    private final SparseIntArray sparseSwitchMap;
    private final InstructionOffsetMap instructionOffsetMap;

    public MethodDefinition(ClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        this.classDef = classDef;
        this.method = method;
        this.methodImpl = methodImpl;

        try {
            this.instructions = ImmutableList.copyOf(methodImpl.getInstructions());
            this.methodParameters = ImmutableList.copyOf(method.getParameters());
            this.effectiveInstructions = Lists.newArrayList(this.instructions);
            this.packedSwitchMap = new SparseIntArray(0);
            this.sparseSwitchMap = new SparseIntArray(0);
            this.instructionOffsetMap = new InstructionOffsetMap(this.instructions);
            int endOffset = this.instructionOffsetMap.getInstructionCodeOffset(this.instructions.size() - 1) + this.instructions.get(this.instructions.size() - 1).getCodeUnits();

            for(int i = 0; i < this.instructions.size(); ++i) {
                Instruction instruction = this.instructions.get(i);
                Opcode opcode = instruction.getOpcode();
                boolean valid;
                int codeOffset;
                int targetOffset;
                Instruction payloadInstruction;
                if (opcode == Opcode.PACKED_SWITCH) {
                    valid = true;
                    codeOffset = this.instructionOffsetMap.getInstructionCodeOffset(i);
                    targetOffset = codeOffset + ((OffsetInstruction)instruction).getCodeOffset();

                    try {
                        targetOffset = this.findPayloadOffset(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                    } catch (InvalidSwitchPayload var13) {
                        valid = false;
                    }

                    if (valid) {
                        if (this.packedSwitchMap.get(targetOffset, -1) != -1) {
                            payloadInstruction = this.findSwitchPayload(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            this.effectiveInstructions.set(i, new ImmutableInstruction31t(opcode, ((Instruction31t)instruction).getRegisterA(), endOffset - codeOffset));
                            this.effectiveInstructions.add(payloadInstruction);
                            endOffset += payloadInstruction.getCodeUnits();
                        }

                        this.packedSwitchMap.append(targetOffset, codeOffset);
                    }
                } else if (opcode == Opcode.SPARSE_SWITCH) {
                    valid = true;
                    codeOffset = this.instructionOffsetMap.getInstructionCodeOffset(i);
                    targetOffset = codeOffset + ((OffsetInstruction)instruction).getCodeOffset();

                    try {
                        targetOffset = this.findPayloadOffset(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                    } catch (InvalidSwitchPayload var14) {
                        valid = false;
                    }

                    if (valid) {
                        if (this.sparseSwitchMap.get(targetOffset, -1) != -1) {
                            payloadInstruction = this.findSwitchPayload(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            this.effectiveInstructions.set(i, new ImmutableInstruction31t(opcode, ((Instruction31t)instruction).getRegisterA(), endOffset - codeOffset));
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

    public static void writeEmptyMethodTo(IndentingWriter writer, Method method, baksmaliOptions options) throws IOException {
        writer.write(".method ");
        writeAccessFlags(writer, method.getAccessFlags());
        writer.write(method.getName());
        writer.write("(");
        ImmutableList<MethodParameter> methodParameters = ImmutableList.copyOf(method.getParameters());
        UnmodifiableIterator var4 = methodParameters.iterator();

        while(var4.hasNext()) {
            MethodParameter parameter = (MethodParameter)var4.next();
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
        UnmodifiableIterator var3 = this.methodParameters.iterator();

        while(var3.hasNext()) {
            MethodParameter parameter = (MethodParameter)var3.next();
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
        if (this.classDef.options.useLocalsDirective) {
            writer.write(".locals ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() - parameterRegisterCount);
        } else {
            writer.write(".registers ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount());
        }

        writer.write(10);
        writeParameters(writer, this.method, this.methodParameters, this.classDef.options);
        if (this.registerFormatter == null) {
            this.registerFormatter = new RegisterFormatter(this.classDef.options, this.methodImpl.getRegisterCount(), parameterRegisterCount);
        }

        String containingClass = null;
        if (this.classDef.options.useImplicitReferences) {
            containingClass = this.method.getDefiningClass();
        }

        AnnotationFormatter.writeTo(writer, this.method.getAnnotations(), containingClass);
        writer.write(10);
        List<MethodItem> methodItems = this.getMethodItems();
        Iterator var9 = methodItems.iterator();

        while(var9.hasNext()) {
            MethodItem methodItem = (MethodItem)var9.next();
            if (methodItem.writeTo(writer)) {
                writer.write(10);
            }
        }

        writer.deindent(4);
        writer.write(".end method\n");
    }

    public Instruction findSwitchPayload(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InvalidInstructionOffset var5) {
            throw new InvalidSwitchPayload(targetOffset);
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

            throw new InvalidSwitchPayload(targetOffset);
        } else {
            return instruction;
        }
    }

    public int findPayloadOffset(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = this.instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InvalidInstructionOffset var5) {
            throw new InvalidSwitchPayload(targetOffset);
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

            throw new InvalidSwitchPayload(targetOffset);
        } else {
            return targetOffset;
        }
    }

    protected static void writeAccessFlags(IndentingWriter writer, int accessFlags) throws IOException {
        AccessFlags[] var2 = AccessFlags.getAccessFlagsForMethod(accessFlags);
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            AccessFlags accessFlag = var2[var4];
            writer.write(accessFlag.toString());
            writer.write(32);
        }

    }

    protected static void writeParameters(IndentingWriter writer, Method method, List<? extends MethodParameter> parameters, baksmaliOptions options) throws IOException {
        boolean isStatic = AccessFlags.STATIC.isSet(method.getAccessFlags());
        int registerNumber = isStatic ? 0 : 1;
        Iterator var6 = parameters.iterator();

        while(var6.hasNext()) {
            MethodParameter parameter = (MethodParameter)var6.next();
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

    public LabelCache getLabelCache() {
        return this.labelCache;
    }

    public int getPackedSwitchBaseAddress(int packedSwitchPayloadCodeOffset) {
        return this.packedSwitchMap.get(packedSwitchPayloadCodeOffset, -1);
    }

    public int getSparseSwitchBaseAddress(int sparseSwitchPayloadCodeOffset) {
        return this.sparseSwitchMap.get(sparseSwitchPayloadCodeOffset, -1);
    }

    protected List<MethodItem> getMethodItems() {
        ArrayList<MethodItem> methodItems = new ArrayList();
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
        Iterator<? extends Instruction> var1 = this.methodImpl.getInstructions().iterator();

        Instruction instruction;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            instruction = var1.next();
        } while(!instruction.getOpcode().odexOnly());

        return true;
    }

    private void addInstructionMethodItems(List<MethodItem> methodItems) {
        int currentCodeAddress = 0;

        for(int i = 0; i < this.effectiveInstructions.size(); ++i) {
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
                        writer.printUnsignedLongAsHex((long)this.codeAddress & 4294967295L);
                        return true;
                    }
                });
            }

            if (!this.classDef.options.noAccessorComments && instruction instanceof ReferenceInstruction) {
                Opcode opcode = instruction.getOpcode();
                if (opcode.referenceType == 3) {
                    MethodReference methodReference = null;

                    try {
                        methodReference = (MethodReference)((ReferenceInstruction)instruction).getReference();
                    } catch (InvalidItemIndex var9) {
                    }

                    if (methodReference != null && SyntheticAccessorResolver.looksLikeSyntheticAccessor(methodReference.getName())) {
                        AccessedMember accessedMember = this.classDef.options.syntheticAccessorResolver.getAccessedMember(methodReference);
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

        for(int i = 0; i < instructions.size(); ++i) {
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
                        writer.printUnsignedLongAsHex((long)this.codeAddress & 4294967295L);
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
            Iterator var5 = tryBlocks.iterator();

            while(var5.hasNext()) {
                TryBlock<? extends ExceptionHandler> tryBlock = (TryBlock)var5.next();
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

                while(var11.hasNext()) {
                    ExceptionHandler handler = (ExceptionHandler)var11.next();
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
        Iterator var2 = this.methodImpl.getDebugItems().iterator();

        while(var2.hasNext()) {
            DebugItem debugItem = (DebugItem)var2.next();
            methodItems.add(DebugMethodItem.build(this.registerFormatter, debugItem));
        }

    }

    private void setLabelSequentialNumbers() {
        HashMap<String, Integer> nextLabelSequenceByType = new HashMap();
        ArrayList<LabelMethodItem> sortedLabels = new ArrayList(this.labelCache.getLabels());
        Collections.sort(sortedLabels);
        Iterator var3 = sortedLabels.iterator();

        while(var3.hasNext()) {
            LabelMethodItem labelMethodItem = (LabelMethodItem)var3.next();
            Integer labelSequence = nextLabelSequenceByType.get(labelMethodItem.getLabelPrefix());
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
            super("No switch payload at offset: %d", payloadOffset);
            this.payloadOffset = payloadOffset;
        }
    }

    public static class LabelCache {
        protected HashMap<LabelMethodItem, LabelMethodItem> labels = new HashMap();

        public LabelCache() {
        }

        public LabelMethodItem internLabel(LabelMethodItem labelMethodItem) {
            LabelMethodItem internedLabelMethodItem = this.labels.get(labelMethodItem);
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

    public RegisterFormatter getRegisterFormatter() {
        return registerFormatter;
    }

}
