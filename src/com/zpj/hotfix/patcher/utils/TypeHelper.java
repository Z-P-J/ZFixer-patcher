package com.zpj.hotfix.patcher.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Z boolean
 * B byte
 * S short
 * C char
 * I int
 * J long
 * F float
 * D double
 * @author Z-P-J
 */
public class TypeHelper {

    private static final Map<String, String> REAL_TYPE_MAP = new HashMap<>();
    private static final Map<String, String> WRAP_TYPE_MAP = new HashMap<>();

    public static final String TYPE_OBJECT = "Ljava/lang/Object;";

    static {

        REAL_TYPE_MAP.put("Z", "boolean");
        REAL_TYPE_MAP.put("B", "byte");
        REAL_TYPE_MAP.put("S", "short");
        REAL_TYPE_MAP.put("C", "char");
        REAL_TYPE_MAP.put("I", "int");
        REAL_TYPE_MAP.put("J", "long");
        REAL_TYPE_MAP.put("F", "float");
        REAL_TYPE_MAP.put("D", "double");


        WRAP_TYPE_MAP.put("Z", "Ljava/lang/Boolean;");
        WRAP_TYPE_MAP.put("B", "Ljava/lang/Byte;");
        WRAP_TYPE_MAP.put("S", "Ljava/lang/Short;");
        WRAP_TYPE_MAP.put("C", "Ljava/lang/Character;");
        WRAP_TYPE_MAP.put("I", "Ljava/lang/Integer;");
        WRAP_TYPE_MAP.put("J", "Ljava/lang/Long;");
        WRAP_TYPE_MAP.put("F", "Ljava/lang/Float;");
        WRAP_TYPE_MAP.put("D", "Ljava/lang/Double;");
    }

    public static boolean isPrimitive(String type) {
        return REAL_TYPE_MAP.containsKey(type);
    }

    public static boolean isWidthType(String type) {
        return "J".equals(type) || "D".equals(type);
    }

    public static String getRealType(String type) {
        return REAL_TYPE_MAP.getOrDefault(type, type);
    }

    public static String getWrapType(String type) {
        return WRAP_TYPE_MAP.getOrDefault(type, type);
    }

}
