//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.dexbacked;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.base.BaseAnnotation;
import org.jf.dexlib2.base.reference.BaseMethodReference;
import org.jf.dexlib2.dexbacked.util.AnnotationsDirectory;
import org.jf.dexlib2.dexbacked.util.AnnotationsDirectory.AnnotationIterator;
import org.jf.dexlib2.dexbacked.util.FixedSizeList;
import org.jf.dexlib2.dexbacked.util.ParameterIterator;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.util.AbstractForwardSequentialList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DexBackedMethod extends BaseMethodReference implements Method {
    
    public final DexBackedDexFile dexFile;
    
    public final DexBackedClassDef classDef;
    public final int accessFlags;
    private final int codeOffset;
    private final int parameterAnnotationSetListOffset;
    private final int methodAnnotationSetOffset;
    public final int methodIndex;
    private int methodIdItemOffset;
    private int protoIdItemOffset;
    private int parametersOffset = -1;
    private BaseAnnotation methodReplace;

    public DexBackedMethod(DexReader reader,  DexBackedClassDef classDef, int previousMethodIndex) {
        this.dexFile = (DexBackedDexFile)reader.dexBuf;
        this.classDef = classDef;
        int methodIndexDiff = reader.readLargeUleb128();
        this.methodIndex = methodIndexDiff + previousMethodIndex;
        this.accessFlags = reader.readSmallUleb128();
        this.codeOffset = reader.readSmallUleb128();
        this.methodAnnotationSetOffset = 0;
        this.parameterAnnotationSetListOffset = 0;
    }

    public DexBackedMethod( DexReader reader,  DexBackedClassDef classDef, int previousMethodIndex,  AnnotationIterator methodAnnotationIterator,  AnnotationIterator paramaterAnnotationIterator) {
        this.dexFile = (DexBackedDexFile)reader.dexBuf;
        this.classDef = classDef;
        int methodIndexDiff = reader.readLargeUleb128();
        this.methodIndex = methodIndexDiff + previousMethodIndex;
        this.accessFlags = reader.readSmallUleb128();
        this.codeOffset = reader.readSmallUleb128();
        this.methodAnnotationSetOffset = methodAnnotationIterator.seekTo(this.methodIndex);
        this.parameterAnnotationSetListOffset = paramaterAnnotationIterator.seekTo(this.methodIndex);
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    public boolean isStatic() {
        return AccessFlags.STATIC.isSet(getAccessFlags());
    }

    
    public String getDefiningClass() {
        return this.classDef.getType();
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    
    public String getName() {
        return this.dexFile.getString(this.dexFile.readSmallUint(this.getMethodIdItemOffset() + 4));
    }

    
    public String getReturnType() {
        return this.dexFile.getType(this.dexFile.readSmallUint(this.getProtoIdItemOffset() + 4));
    }

    
    public List<? extends MethodParameter> getParameters() {
        int parametersOffset = this.getParametersOffset();
        if (parametersOffset > 0) {
            final List<String> parameterTypes = this.getParameterTypes();
            return new AbstractForwardSequentialList<MethodParameter>() {
                
                public Iterator<MethodParameter> iterator() {
                    return new ParameterIterator(parameterTypes, DexBackedMethod.this.getParameterAnnotations(), DexBackedMethod.this.getParameterNames());
                }

                public int size() {
                    return parameterTypes.size();
                }
            };
        } else {
            return ImmutableList.of();
        }
    }

    
    public List<? extends Set<? extends DexBackedAnnotation>> getParameterAnnotations() {
        return AnnotationsDirectory.getParameterAnnotations(this.dexFile, this.parameterAnnotationSetListOffset);
    }

    
    public Iterator<String> getParameterNames() {
        DexBackedMethodImplementation methodImpl = this.getImplementation();
        return (Iterator)(methodImpl != null ? methodImpl.getParameterNames((DexReader)null) : Iterators.emptyIterator());
    }

    
    public List<String> getParameterTypes() {
        int parametersOffset = this.getParametersOffset();
        if (parametersOffset > 0) {
            final int parameterCount = this.dexFile.readSmallUint(parametersOffset + 0);
            final int paramListStart = parametersOffset + 4;
            return new FixedSizeList<String>() {
                
                public String readItem(int index) {
                    return DexBackedMethod.this.dexFile.getType(DexBackedMethod.this.dexFile.readUshort(paramListStart + 2 * index));
                }

                public int size() {
                    return parameterCount;
                }
            };
        } else {
            return ImmutableList.of();
        }
    }

    
    public Set<? extends Annotation> getAnnotations() {
        Set<BaseAnnotation> ret = new HashSet<>(AnnotationsDirectory.getAnnotations(this.dexFile, this.methodAnnotationSetOffset));
        if (this.methodReplace != null) {
            ret.add(this.methodReplace);
        }

        return ret;
    }

    
    public DexBackedMethodImplementation getImplementation() {
        return this.codeOffset > 0 ? new DexBackedMethodImplementation(this.dexFile, this, this.codeOffset) : null;
    }

    private int getMethodIdItemOffset() {
        if (this.methodIdItemOffset == 0) {
            this.methodIdItemOffset = this.dexFile.getMethodIdItemOffset(this.methodIndex);
        }

        return this.methodIdItemOffset;
    }

    private int getProtoIdItemOffset() {
        if (this.protoIdItemOffset == 0) {
            int protoIndex = this.dexFile.readUshort(this.getMethodIdItemOffset() + 2);
            this.protoIdItemOffset = this.dexFile.getProtoIdItemOffset(protoIndex);
        }

        return this.protoIdItemOffset;
    }

    private int getParametersOffset() {
        if (this.parametersOffset == -1) {
            this.parametersOffset = this.dexFile.readSmallUint(this.getProtoIdItemOffset() + 8);
        }

        return this.parametersOffset;
    }

    public static void skipMethods( DexReader reader, int count) {
        for(int i = 0; i < count; ++i) {
            reader.skipUleb128();
            reader.skipUleb128();
            reader.skipUleb128();
        }

    }

    public void setMethodReplace(BaseAnnotation annotation) {
        this.methodReplace = annotation;
    }
}
