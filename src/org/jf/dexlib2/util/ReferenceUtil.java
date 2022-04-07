//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.util;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.utils.TypeGenUtil;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.jf.dexlib2.iface.reference.*;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public final class ReferenceUtil {
    public static String getMethodDescriptor(MethodReference methodReference) {
        return getMethodDescriptor(methodReference, false);
    }

    public static String getMethodDescriptor(MethodReference methodReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = methodReference.getDefiningClass();
            if (Patcher.getModifiedClasses(clazz) != null) {
                clazz = TypeGenUtil.newType(clazz);
            }

            sb.append(clazz);
            sb.append("->");
        }

        sb.append(methodReference.getName());
        sb.append('(');
        Iterator var3 = methodReference.getParameterTypes().iterator();

        while(var3.hasNext()) {
            CharSequence paramType = (CharSequence)var3.next();
            sb.append(paramType);
        }

        sb.append(')');
        sb.append(methodReference.getReturnType());
        return sb.toString();
    }

    public static void writeMethodDescriptor(Writer writer, MethodReference methodReference) throws IOException {
        writeMethodDescriptor(writer, methodReference, false);
    }

    public static void writeMethodDescriptor(Writer writer, MethodReference methodReference, boolean useImplicitReference) throws IOException {
        if (!useImplicitReference) {
            String clazz = methodReference.getDefiningClass();
            if (Patcher.getModifiedClasses(clazz) != null) {
                clazz = TypeGenUtil.newType(clazz);
            }

            writer.write(clazz);
            writer.write("->");
        }

        writer.write(methodReference.getName());
        writer.write(40);
        Iterator var3 = methodReference.getParameterTypes().iterator();

        while(var3.hasNext()) {
            CharSequence paramType = (CharSequence)var3.next();
            writer.write(paramType.toString());
        }

        writer.write(41);
        writer.write(methodReference.getReturnType());
    }

    public static String getFieldDescriptor(FieldReference fieldReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = fieldReference.getDefiningClass();
            System.out.println("getFieldDescriptor getDefiningClass=" + clazz);
            DexBackedClassDef modifiedClazz = Patcher.getModifiedClasses(clazz);
            if (modifiedClazz != null && !isStaticFiled(modifiedClazz, fieldReference)) {
                clazz = TypeGenUtil.newType(clazz);
            }
            sb.append(clazz);
            sb.append("->");
        }

        sb.append(fieldReference.getName());
        sb.append(':');
        sb.append(fieldReference.getType());
        System.out.println("getFieldDescriptor getType=" + fieldReference.getType());
        return sb.toString();
    }

    public static String getShortFieldDescriptor(FieldReference fieldReference) {
        StringBuilder sb = new StringBuilder();
        sb.append(fieldReference.getName());
        sb.append(':');
        sb.append(fieldReference.getType());
        return sb.toString();
    }

    public static void writeFieldDescriptor(Writer writer, FieldReference fieldReference) throws IOException {
        writeFieldDescriptor(writer, fieldReference, false);
    }

    public static void writeFieldDescriptor(Writer writer, FieldReference fieldReference, boolean implicitReference) throws IOException {
        if (!implicitReference) {
            String clazz = fieldReference.getDefiningClass();
            DexBackedClassDef modifiedClazz = Patcher.getModifiedClasses(clazz);
            if (modifiedClazz != null && !isStaticFiled(modifiedClazz, fieldReference)) {
                clazz = TypeGenUtil.newType(clazz);
            }

            writer.write(clazz);
            writer.write("->");
        }

        writer.write(fieldReference.getName());
        writer.write(58);
        writer.write(fieldReference.getType());
    }

    public static String getReferenceString(Reference reference) {
        return getReferenceString(reference, (String)null);
    }

    public static String getReferenceString(Reference reference, String containingClass) {
        System.out.println("getReferenceString reference=" + reference + " containingClass=" + containingClass);
        if (reference instanceof StringReference) {
            return String.format("\"%s\"", StringUtils.escapeString(((StringReference)reference).getString()));
        } else if (reference instanceof TypeReference) {
            return ((TypeReference)reference).getType();
        } else {
            boolean useImplicitReference;
            if (reference instanceof FieldReference) {
                FieldReference fieldReference = (FieldReference)reference;
                useImplicitReference = fieldReference.getDefiningClass().equals(containingClass);
                return getFieldDescriptor((FieldReference)reference, useImplicitReference);
            } else if (reference instanceof MethodReference) {
                MethodReference methodReference = (MethodReference)reference;
                useImplicitReference = methodReference.getDefiningClass().equals(containingClass);
                return getMethodDescriptor((MethodReference)reference, useImplicitReference);
            } else {
                return null;
            }
        }
    }

    private static boolean isStaticFiled(DexBackedClassDef classDef, FieldReference reference) {
        for (DexBackedField field : classDef.getStaticFields()) {
            if (field.equals(reference)) {
                return true;
            }
        }
        return false;
    }


}
