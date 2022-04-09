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

    private final String fixType;

    public FixClassDef(DexBackedClassDef classDef, DiffClassInfo classInfo) {
        this.classDef = classDef;
        this.classInfo = classInfo;
        String type = this.classDef.getType();
        if (classInfo.isModified()) {
            this.fixType = type.substring(0, type.length() - 1) + "_Fix;";
        } else {
            this.fixType = type;
        }

    }

    public String getFixType() {
        return fixType;
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
