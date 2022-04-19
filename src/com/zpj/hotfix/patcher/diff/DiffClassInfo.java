package com.zpj.hotfix.patcher.diff;

import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;
import org.jf.dexlib2.iface.value.EncodedValue;

import java.util.*;

public class DiffClassInfo {

    private final DexBackedClassDef oldClazz;
    private final DexBackedClassDef newClazz;

    private final Set<DexBackedField> addedFields = new HashSet<>();
    private final Set<DexBackedMethod> addedMethods = new HashSet<>();
    private final Set<DexBackedField> modifiedFields = new HashSet<>();
    private final Set<DexBackedMethod> modifiedMethods = new HashSet<>();

    private final String type;
    private final String fixType;

    public DiffClassInfo(DexBackedClassDef oldClazz, DexBackedClassDef newClazz) {
        this.oldClazz = oldClazz;
        this.newClazz = newClazz;
        if (oldClazz != null) {
            compare();
        }
        type = this.newClazz.getType();
        if (isModified()) {
            this.fixType = type.substring(0, type.length() - 1) + "_Fix;";
        } else {
            this.fixType = type;
        }
    }

    public String getType() {
        return type;
    }

    public String getFixType() {
        return fixType;
    }

    public String getFixClassName() {
        return this.newClazz.getSourceFile().replace(".java", "_Fix");
    }

    public boolean isModified() {
        return !addedMethods.isEmpty() || !modifiedMethods.isEmpty();
    }

    public boolean isModifiedMethod(DexBackedMethod method) {
        return !modifiedMethods.isEmpty() && modifiedMethods.contains(method);
    }

    public boolean isAddedMethod(DexBackedMethod method) {
        return addedMethods.contains(method);
    }

    public boolean isModifiedMethod(String methodName, List<String> parameterTypes) {
        for (DexBackedMethod method : modifiedMethods) {
            if (method.getName().equals(methodName)) {
                if (method.getParameterTypes().equals(parameterTypes)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAddedMethod(String methodName, List<String> parameterTypes) {
        for (DexBackedMethod method : addedMethods) {
            if (method.getName().equals(methodName)) {
                if (method.getParameterTypes().equals(parameterTypes)) {
                    return true;
                }
            }
        }
        return false;
    }

    public DexBackedMethod getAddedMethod(String methodName, List<String> parameterTypes) {
        for (DexBackedMethod method : addedMethods) {
            if (method.getName().equals(methodName)) {
                if (method.getParameterTypes().equals(parameterTypes)) {
                    return method;
                }
            }
        }
        return null;
    }

    public boolean isFixMethod(String methodName, List<String> parameterTypes) {
        return isModifiedMethod(methodName, parameterTypes) || isAddedMethod(methodName, parameterTypes);
    }

    public DexBackedClassDef getOldClazz() {
        return oldClazz;
    }

    public DexBackedClassDef getNewClazz() {
        return newClazz;
    }

    public Set<DexBackedField> getAddedFields() {
        return addedFields;
    }

    public Set<DexBackedMethod> getAddedMethods() {
        return addedMethods;
    }

    public Set<DexBackedField> getModifiedFields() {
        return modifiedFields;
    }

    public Set<DexBackedMethod> getModifiedMethods() {
        return modifiedMethods;
    }

    private void compare() {
        Map<DexBackedMethod, DexBackedMethod> methodMap = new HashMap<>();
        for (DexBackedMethod oldMethod : oldClazz.getMethods()) {
            if (!"<clinit>".equals(oldMethod.getName())) {
                methodMap.put(oldMethod, oldMethod);
            }
        }

        for (DexBackedMethod newMethod : newClazz.getMethods()) {
            // TODO 比较构造方法
            if ("<clinit>".equals(newMethod.getName())) {
                continue;
            }
            DexBackedMethod oldMethod = methodMap.get(newMethod);
            if (oldMethod == null) {
                addedMethods.add(newMethod);
            } else {

                DexBackedMethodImplementation newImpl = newMethod.getImplementation();
                DexBackedMethodImplementation oldImpl = oldMethod.getImplementation();

                if ((oldImpl == null) && (newImpl == null)) {
                    continue;
                }

                if (oldImpl == null) {
                    modifiedMethods.add(newMethod);
                } else if (newImpl == null) {
                    modifiedMethods.add(newMethod);
                } else if (!oldImpl.equals(newImpl)) {
                    modifiedMethods.add(newMethod);
                }

            }
        }



        compareField(newClazz.getStaticFields(), oldClazz.getStaticFields());
        compareField(newClazz.getInstanceFields(), oldClazz.getInstanceFields());

    }


    public void compareField(Iterable<? extends DexBackedField> news, Iterable<? extends DexBackedField> olds) {

        Map<DexBackedField, DexBackedField> fieldMap = new HashMap<>();

        for (DexBackedField reference : olds) {
            fieldMap.put(reference, reference);
        }

        for (DexBackedField newField : news) {

            DexBackedField oldField = fieldMap.get(newField);
            if (oldField == null) {
                addedFields.add(newField);
            } else {
                EncodedValue newValue = newField.getInitialValue();
                EncodedValue oldValue = oldField.getInitialValue();

                if (oldValue == null && newValue == null) {
                    continue;
                }

                if (oldValue == null) {
                    modifiedFields.add(newField);
                } else if (newValue == null) {
                    modifiedFields.add(newField);
                } else if (oldValue.compareTo(newValue) != 0) {
                    modifiedFields.add(newField);
                }

            }
        }
    }

    @Override
    public String toString() {
        return "DiffClassInfo{" +
                "oldClazz=" + oldClazz +
                ", newClazz=" + newClazz +
                ", addedFields=" + addedFields +
                ", addedMethods=" + addedMethods +
                ", modifiedFields=" + modifiedFields +
                ", modifiedMethods=" + modifiedMethods +
                '}';
    }
}
