//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.dexbacked;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction;
import org.jf.dexlib2.dexbacked.util.DebugInfo;
import org.jf.dexlib2.dexbacked.util.FixedSizeList;
import org.jf.dexlib2.dexbacked.util.VariableSizeLookaheadIterator;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.util.AlignmentUtils;
import org.jf.util.ExceptionWithContext;

public class DexBackedMethodImplementation implements MethodImplementation {
    public final DexBackedDexFile dexFile;
    public final DexBackedMethod method;
    private final int codeOffset;

    public DexBackedMethodImplementation(DexBackedDexFile dexFile, DexBackedMethod method, int codeOffset) {
        this.dexFile = dexFile;
        this.method = method;
        this.codeOffset = codeOffset;
    }

    public int getRegisterCount() {
        return this.dexFile.readUshort(this.codeOffset);
    }

    public Iterable<? extends Instruction> getInstructions() {
        int instructionsSize = this.dexFile.readSmallUint(this.codeOffset + 12);
        final int instructionsStartOffset = this.codeOffset + 16;
        final int endOffset = instructionsStartOffset + instructionsSize * 2;
        return new Iterable<Instruction>() {
            public Iterator<Instruction> iterator() {
                return new VariableSizeLookaheadIterator<Instruction>(DexBackedMethodImplementation.this.dexFile, instructionsStartOffset) {
                    protected Instruction readNextItem(DexReader reader) {
                        if (reader.getOffset() >= endOffset) {
                            return this.endOfData();
                        } else {
                            Instruction instruction = DexBackedInstruction.readFrom(reader);
                            int offset = reader.getOffset();
                            if (offset <= endOffset && offset >= 0) {
                                return instruction;
                            } else {
                                throw new ExceptionWithContext("The last instruction in the method is truncated");
                            }
                        }
                    }
                };
            }
        };
    }

    public List<? extends DexBackedTryBlock> getTryBlocks() {
        final int triesSize = this.dexFile.readUshort(this.codeOffset + 6);
        if (triesSize > 0) {
            int instructionsSize = this.dexFile.readSmallUint(this.codeOffset + 12);
            final int triesStartOffset = AlignmentUtils.alignOffset(this.codeOffset + 16 + instructionsSize * 2, 4);
            final int handlersStartOffset = triesStartOffset + triesSize * 8;
            return new FixedSizeList<DexBackedTryBlock>() {
                public DexBackedTryBlock readItem(int index) {
                    return new DexBackedTryBlock(DexBackedMethodImplementation.this.dexFile, triesStartOffset + index * 8, handlersStartOffset);
                }

                public int size() {
                    return triesSize;
                }
            };
        } else {
            return ImmutableList.of();
        }
    }

    private DebugInfo getDebugInfo() {
        return DebugInfo.newOrEmpty(this.dexFile, this.dexFile.readSmallUint(this.codeOffset + 8), this);
    }

    public Iterable<? extends DebugItem> getDebugItems() {
        return this.getDebugInfo();
    }

    public Iterator<String> getParameterNames(DexReader dexReader) {
        return this.getDebugInfo().getParameterNames(dexReader);
    }

    public boolean equals(Object obj) {
        if (obj instanceof DexBackedMethodImplementation) {
            return this.getRegisterCount() == ((DexBackedMethodImplementation)obj).getRegisterCount()
                    && this.equalTryBlocks(this.getTryBlocks(), ((DexBackedMethodImplementation)obj).getTryBlocks())
                    && this.equalParameterNames(this.getInstructions(), ((DexBackedMethodImplementation)obj).getInstructions());
        } else {
            return false;
        }
    }

    private boolean equalTryBlocks(List<? extends DexBackedTryBlock> a, List<? extends DexBackedTryBlock> b) {
        if (a.size() != b.size()) {
            return false;
        } else {
            for(int i = 0; i < a.size(); ++i) {
                DexBackedTryBlock at = a.get(i);
                DexBackedTryBlock bt = b.get(i);
                if (!at.equals(bt)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean equalParameterNames(Iterable<? extends Instruction> ai, Iterable<? extends Instruction> bi) {
        ImmutableList<? extends Instruction> a = ImmutableList.copyOf(ai);
        ImmutableList<? extends Instruction> b = ImmutableList.copyOf(bi);
        if (a.size() != b.size()) {
            return false;
        } else {
            for(int i = 0; i < a.size(); ++i) {
                Instruction at = a.get(i);
                Instruction bt = b.get(i);
                if (!at.equals(bt)) {
                    return false;
                }
            }

            return true;
        }
    }
}
