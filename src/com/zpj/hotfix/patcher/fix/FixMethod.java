package com.zpj.hotfix.patcher.fix;

import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.dexlib2.iface.reference.MethodReference;

import java.util.List;
import java.util.Set;

public class FixMethod implements Method {

    private final Method method;

    public FixMethod(Method method) {
        this.method = method;
    }

    @Override
    public String getDefiningClass() {
        return method.getDefiningClass() + "_Fix";
    }

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public List<? extends CharSequence> getParameterTypes() {
        return method.getParameterTypes();
    }

    @Override
    public List<? extends MethodParameter> getParameters() {
        return method.getParameters();
    }

    @Override
    public String getReturnType() {
        return method.getReturnType();
    }

    @Override
    public int compareTo(MethodReference methodReference) {
        return method.compareTo(methodReference);
    }

    @Override
    public int getAccessFlags() {
        return method.getAccessFlags();
    }

    @Override
    public Set<? extends Annotation> getAnnotations() {
        return method.getAnnotations();
    }

    @Override
    public MethodImplementation getImplementation() {
        return method.getImplementation();
    }
}
