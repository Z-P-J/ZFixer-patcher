package com.zpj.hotfix.patcher.utils;

import org.jf.dexlib2.dexbacked.DexBackedField;

import java.util.List;
import java.util.Set;

public class Formater {

    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

    public static String format(List<DexBackedField> fields) {
        StringBuffer buffer = new StringBuffer();
        for (DexBackedField field : fields) {
            buffer.append(String.valueOf(field.getName()) + "(" + field.getType() + "), ");
        }
        return buffer.toString();
    }

    public static String formatStringList(List<String> strings) {
        StringBuffer buffer = new StringBuffer();
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    public static String dotStringList(Set<String> strings) {
        StringBuffer buffer = new StringBuffer();
        for (String string : strings) {
            if (buffer.length() > 0) {
                buffer.append(',');
            }
            buffer.append(string);
        }
        return buffer.toString();
    }
}
