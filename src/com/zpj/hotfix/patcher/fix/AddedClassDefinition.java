package com.zpj.hotfix.patcher.fix;

import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;

public class AddedClassDefinition extends ClassDefinition {

    public AddedClassDefinition(baksmaliOptions options, ClassDef classDef) {
        super(options, classDef);
    }

    @Override
    protected MethodDefinition createMethodDefinition(ClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        return new AddedMethodDefinition(this, (DexBackedMethod) method, methodImpl);
    }
}
