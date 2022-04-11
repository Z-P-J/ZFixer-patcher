//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.dexbacked.reference;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.jf.dexlib2.base.reference.BaseMethodReference;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.util.FixedSizeList;

public class DexBackedMethodReference extends BaseMethodReference {
    public final DexBackedDexFile dexFile;
    public final int methodIdItemOffset;
    private int protoIdItemOffset;

    private String definingClass;

    public DexBackedMethodReference(DexBackedDexFile dexFile, int methodIndex) {
        this.dexFile = dexFile;
        this.methodIdItemOffset = dexFile.getMethodIdItemOffset(methodIndex);
    }

    public void replaceDefiningClass(String definingClass) {
        this.definingClass = definingClass;
    }

    public String getDefiningClass() {
        if (definingClass != null && !definingClass.isEmpty()) {
            return definingClass;
        }
        return this.dexFile.getType(this.dexFile.readUshort(this.methodIdItemOffset));
    }

    public String getName() {
        return this.dexFile.getString(this.dexFile.readSmallUint(this.methodIdItemOffset + 4));
    }

    public List<String> getParameterTypes() {
        int protoIdItemOffset = this.getProtoIdItemOffset();
        int parametersOffset = this.dexFile.readSmallUint(protoIdItemOffset + 8);
        if (parametersOffset > 0) {
            final int parameterCount = this.dexFile.readSmallUint(parametersOffset);
            final int paramListStart = parametersOffset + 4;
            return new FixedSizeList<String>() {
                public String readItem(int index) {
                    return DexBackedMethodReference.this.dexFile.getType(DexBackedMethodReference.this.dexFile.readUshort(paramListStart + 2 * index));
                }

                public int size() {
                    return parameterCount;
                }
            };
        } else {
            return ImmutableList.of();
        }
    }

    public String getReturnType() {
        int protoIdItemOffset = this.getProtoIdItemOffset();
        return this.dexFile.getType(this.dexFile.readSmallUint(protoIdItemOffset + 4));
    }

    private int getProtoIdItemOffset() {
        if (this.protoIdItemOffset == 0) {
            this.protoIdItemOffset = this.dexFile.getProtoIdItemOffset(this.dexFile.readUshort(this.methodIdItemOffset + 2));
        }

        return this.protoIdItemOffset;
    }
}
