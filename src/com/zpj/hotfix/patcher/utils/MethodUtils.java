package com.zpj.hotfix.patcher.utils;

import org.jf.dexlib2.iface.reference.MethodReference;

public class MethodUtils {

    public static String getMethodDescriptor(MethodReference methodReference) {
        StringBuilder sb = new StringBuilder();

        sb.append(methodReference.getName());
        sb.append('(');

        for (CharSequence paramType : methodReference.getParameterTypes()) {
            sb.append(paramType);
        }

        sb.append(')');
        sb.append(methodReference.getReturnType());
        return sb.toString();
    }

}
