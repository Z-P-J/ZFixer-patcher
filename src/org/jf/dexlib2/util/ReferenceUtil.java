//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2.util;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import com.zpj.hotfix.patcher.fix.FixClassDef;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;
import org.jf.dexlib2.iface.reference.*;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.io.Writer;

public final class ReferenceUtil {

    public static String getMethodDescriptor(Opcode opcode, MethodReference methodReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = methodReference.getDefiningClass();
//            if (Patcher.getModifiedClasses(clazz) != null) {
//                clazz = TypeGenUtil.newType(clazz);
//            }

            DiffClassInfo classInfo = Patcher.getClassInfo(clazz);
            boolean isStatic = opcode == Opcode.INVOKE_STATIC || opcode == Opcode.INVOKE_STATIC_RANGE;
            if (classInfo != null) {
//                DexBackedMethod method = classInfo.getAddedMethod(methodReference.getName(),
//                        ((DexBackedMethod) methodReference).getParameterTypes());
//                if (method != null && method.isStatic()) {
//                    clazz = clazz.substring(0, clazz.length() - 1) + "_Fix;";
//                }
                if (classInfo.isAddedMethod(methodReference.getName(), ((DexBackedMethodReference) methodReference).getParameterTypes())) {
                    if (isStatic) {
                        clazz = clazz.substring(0, clazz.length() - 1) + "_Fix;";
                    }
                }
            }

            sb.append(clazz);
            sb.append("->");
        }

        sb.append(methodReference.getName());
        sb.append('(');

        for (CharSequence paramType : methodReference.getParameterTypes()) {
            sb.append(paramType);
        }

        sb.append(')');
        sb.append(methodReference.getReturnType());
        return sb.toString();
    }

    public static String getMethodDescriptor(FixClassDef classDef, MethodReference methodReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = methodReference.getDefiningClass();

            if (classDef.getType().equals(clazz)) {
                clazz = classDef.getFixType();
            } else {
                DiffClassInfo classInfo = Patcher.getClassInfo(clazz);
                if (classInfo != null) {
                    DexBackedMethod method = classInfo.getAddedMethod(methodReference.getName(),
                            ((DexBackedMethodReference) methodReference).getParameterTypes());
                    if (method != null && method.isStatic()) {
                        clazz = clazz.substring(0, clazz.length() - 1) + "_Fix;";
                    }
                }

            }

            sb.append(clazz);
            sb.append("->");
        }

        sb.append(methodReference.getName());
        sb.append('(');

        for (CharSequence paramType : methodReference.getParameterTypes()) {
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
            writer.write(clazz);
            writer.write("->");
        }

        writer.write(methodReference.getName());
        writer.write(40);

        for (CharSequence paramType : methodReference.getParameterTypes()) {
            writer.write(paramType.toString());
        }

        writer.write(41);
        writer.write(methodReference.getReturnType());
    }

    public static String getFieldDescriptor(FieldReference fieldReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = fieldReference.getDefiningClass();
//            DexBackedClassDef modifiedClazz = Patcher.getModifiedClasses(clazz);
//            if (modifiedClazz != null && !isStaticFiled(modifiedClazz, fieldReference)) {
//                clazz = TypeGenUtil.newType(clazz);
//            }
            sb.append(clazz);
            sb.append("->");
        }

        sb.append(fieldReference.getName());
        sb.append(':');
        sb.append(fieldReference.getType());
        return sb.toString();
    }

    public static String getFieldDescriptor(FixClassDef classDef, FieldReference fieldReference, boolean useImplicitReference) {
        StringBuilder sb = new StringBuilder();
        if (!useImplicitReference) {
            String clazz = fieldReference.getDefiningClass();
            if (classDef.getType().equals(clazz)) {
                clazz = classDef.getFixType();
            }
            sb.append(clazz);
            sb.append("->");
        }

        sb.append(fieldReference.getName());
        sb.append(':');
        sb.append(fieldReference.getType());
        return sb.toString();
    }

    public static String getShortFieldDescriptor(FieldReference fieldReference) {
        return fieldReference.getName() +
                ':' +
                fieldReference.getType();
    }

    public static void writeFieldDescriptor(Writer writer, FieldReference fieldReference) throws IOException {
        writeFieldDescriptor(writer, fieldReference, false);
    }

    public static void writeFieldDescriptor(Writer writer, FieldReference fieldReference, boolean implicitReference) throws IOException {
        if (!implicitReference) {
            String clazz = fieldReference.getDefiningClass();
//            DexBackedClassDef modifiedClazz = Patcher.getModifiedClasses(clazz);
//            if (modifiedClazz != null && !isStaticFiled(modifiedClazz, fieldReference)) {
//                clazz = TypeGenUtil.newType(clazz);
//            }

            writer.write(clazz);
            writer.write("->");
        }

        writer.write(fieldReference.getName());
        writer.write(58);
        writer.write(fieldReference.getType());
    }

    public static String getReferenceString(Opcode opcode, Reference reference) {
        return getReferenceString(opcode, reference, (String)null);
    }

    public static String getReferenceString(Opcode opcode, Reference reference, String containingClass) {
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
                return getMethodDescriptor(opcode, (MethodReference)reference, useImplicitReference);
            } else {
                return null;
            }
        }
    }

    public static String getReferenceString(FixClassDef classDef, Reference reference, String containingClass) {
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
                return getFieldDescriptor(classDef, (FieldReference)reference, useImplicitReference);
            } else if (reference instanceof MethodReference) {
                MethodReference methodReference = (MethodReference)reference;
                useImplicitReference = methodReference.getDefiningClass().equals(containingClass);
                return getMethodDescriptor(classDef, (MethodReference)reference, useImplicitReference);
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
