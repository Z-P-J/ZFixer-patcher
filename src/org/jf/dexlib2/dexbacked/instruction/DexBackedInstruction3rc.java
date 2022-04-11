//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.dexbacked.instruction;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.reference.DexBackedReference;
import org.jf.dexlib2.iface.instruction.formats.Instruction3rc;
import org.jf.dexlib2.iface.reference.Reference;

public class DexBackedInstruction3rc extends DexBackedInstruction implements Instruction3rc {

    private Reference reference;

    public DexBackedInstruction3rc(DexBackedDexFile dexFile, Opcode opcode, int instructionStart) {
        super(dexFile, opcode, instructionStart);
    }

    public int getRegisterCount() {
        return this.dexFile.readUbyte(this.instructionStart + 1);
    }

    public int getStartRegister() {
        return this.dexFile.readUshort(this.instructionStart + 4);
    }

    public Reference getReference() {
        if (reference == null) {
            reference = DexBackedReference.makeReference(this.dexFile, this.opcode.referenceType, this.dexFile.readUshort(this.instructionStart + 2));
        }
        return reference;
    }

    public int getReferenceType() {
        return this.opcode.referenceType;
    }
}
