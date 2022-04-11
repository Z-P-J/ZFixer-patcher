package com.zpj.hotfix.patcher.fix;

import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import org.jf.dexlib2.base.reference.BaseTypeReference;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;

import java.util.List;
import java.util.Set;

public class FixClassDef extends BaseTypeReference implements ClassDef {

    private final DexBackedClassDef classDef;
    private final DiffClassInfo classInfo;

    public FixClassDef(DexBackedClassDef classDef, DiffClassInfo classInfo) {
        this.classDef = classDef;
        this.classInfo = classInfo;
    }

    public String getFixType() {
        return this.classInfo.getFixType();
    }

    public DexBackedClassDef getClassDef() {
        return classDef;
    }

    public DiffClassInfo getClassInfo() {
        return classInfo;
    }

    @Override
    public int getAccessFlags() {
        return this.classDef.getAccessFlags();
    }

    @Override
    public String getSuperclass() {
        return this.classDef.getSuperclass();
    }

    @Override
    public List<String> getInterfaces() {
        return this.classDef.getInterfaces();
    }

    @Override
    public String getSourceFile() {
        return this.classDef.getSourceFile();
    }

    @Override
    public Set<? extends Annotation> getAnnotations() {
        return this.classDef.getAnnotations();
    }

    @Override
    public Iterable<? extends Field> getStaticFields() {
        return this.classDef.getStaticFields();
    }

    @Override
    public Iterable<? extends Field> getInstanceFields() {
        return this.classDef.getInstanceFields();
    }

    @Override
    public Iterable<? extends DexBackedMethod> getDirectMethods() {
        return this.classDef.getDirectMethods();
    }

    public Iterable<? extends DexBackedMethod> getDirectMethods(final boolean skipDuplicates) {
        return this.classDef.getDirectMethods(skipDuplicates);
    }

    @Override
    public Iterable<? extends DexBackedMethod> getVirtualMethods() {
        return this.classDef.getVirtualMethods();
    }

    @Override
    public Iterable<? extends Method> getMethods() {
        return this.classDef.getMethods();
    }

    @Override
    public String getType() {
        return this.classDef.getType();
    }
}
