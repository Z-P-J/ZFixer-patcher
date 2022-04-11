package com.zpj.hotfix.patcher.fix;

import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.util.IndentingWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddedClassDefinition extends ClassDefinition {


    private final Map<String, String> injectMethodMap = new HashMap<>();


    public AddedClassDefinition(baksmaliOptions options, ClassDef classDef) {
        super(options, classDef);
    }

    @Override
    public void writeTo(IndentingWriter writer) throws IOException {
        super.writeTo(writer);

        List<String> newMethodList = getInjectMethodList();
        for (String newMethod : newMethodList) {
            writer.write("\n\n");
            writer.write(newMethod);
        }
    }

    @Override
    protected MethodDefinition createMethodDefinition(ClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        return new AddedMethodDefinition(this, (DexBackedMethod) method, methodImpl);
    }

    public void putNewMethod(String key, String method) {
        injectMethodMap.put(key, method);
    }

    public boolean shouldInjectMethod(String key) {
        return !injectMethodMap.containsKey(key);
    }

    public List<String> getInjectMethodList() {
        return new ArrayList<>(injectMethodMap.values());
    }

}
