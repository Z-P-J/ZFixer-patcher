//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.dexbacked.instruction;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.reference.DexBackedReference;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;
import org.jf.dexlib2.iface.reference.Reference;

public class DexBackedInstruction21c extends DexBackedInstruction implements Instruction21c {
    public DexBackedInstruction21c(DexBackedDexFile dexFile, Opcode opcode, int instructionStart) {
        super(dexFile, opcode, instructionStart);
    }

    public int getRegisterA() {
        return this.dexFile.readUbyte(this.instructionStart + 1);
    }

    public Reference getReference() {
        return DexBackedReference.makeReference(this.dexFile, this.opcode.referenceType, this.dexFile.readUshort(this.instructionStart + 2));
    }

    public int getReferenceType() {
        return this.opcode.referenceType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DexBackedInstruction21c) {
            return this.getOpcode() == ((DexBackedInstruction21c)obj).getOpcode()
                    && getReference().equals(((DexBackedInstruction21c) obj).getReference());
        } else {
            return false;
        }
    }
}
