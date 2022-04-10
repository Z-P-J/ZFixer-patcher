package com.zpj.hotfix.patcher.utils;

import java.util.List;

/**
 * @author z-p-j
 */
public class FixMethodBuilder {

    private static void buildReturnSmali(StringBuilder builder, String returnType) {
//        if 有返回值:
//            if 是Ljava/lang/Object;类型:
//                move-result-object v0
//                return-object v0
//
//            else if 是基本数据类型:
//                move-result-object v0
//                check-cast v0, Ljava/lang/Integer;返回类型包装类
//                invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I
//                move-result v0
//                return v0
//
//            else:
//                move-result-object v0
//                check-cast v0, 返回类型
//                return-object v0
//
//        else:
//            return-void
        System.out.println("buildReturnSmali returnType=" + returnType);
        if (returnType == null || "V".equals(returnType)) {
            builder.append("return-void");
        } else {
            builder.append("move-result-object v0").append("\n\n");
            if (TypeHelper.TYPE_OBJECT.equals(returnType)) {
                builder.append("return-object v0").append("\n\n");
            } else if (TypeHelper.isPrimitive(returnType)) {
                String realType = TypeHelper.getRealType(returnType);
                String wrapType = TypeHelper.getWrapType(returnType);
                builder.append("check-cast v0, ").append(wrapType).append("\n\n");
                builder.append("invoke-virtual {v0}, ").append(wrapType).append("->").append(realType)
                        .append("Value()").append(returnType).append("\n\n");
                builder.append("move-result v0").append("\n\n");
                builder.append("return v0").append("\n\n");
            } else {
                // move-result-object v0
                // check-cast v0, 返回类型
                // return-object v0
                builder.append("check-cast v0, ").append(returnType).append("\n\n");
                builder.append("return-object v0").append("\n\n");
            }
        }
    }

    public static String buildAccessMethod(String methodName, List<String> parameterTypes, String returnType, String bugClazz, String fixClass, boolean isStatic) {
        StringBuilder builder = new StringBuilder();

        // .method private 方法名(所有参数类型)返回类型
        String parameters = String.join("", parameterTypes);
        builder.append(".method private ").append(isStatic ? "static " : "").append(methodName)
                .append("(").append(parameters).append(")").append(returnType);
        builder.append("\n\n");

        int n = parameterTypes.size();
        int registers = n == 0 ? 3 : 2 * n + 4;
        if (isStatic) {
            // 静态方法没有寄存器p0
            registers -= 1;
        }
        builder.append(".registers ").append(registers).append("\n\n");

        // 参数
        for (int i = 0; i < n; i++) {
            // 静态方法参数寄存器从p0开始，普通方法从p1开始
            int r = isStatic ? i : (1 + i);
            builder.append(".param p").append(r).append(", \"arg").append(i).append("\"").append("\n\n");
        }

        // 抛出异常
        buildExceptionAnnotation(builder);

        builder.append(".prologue\n\n");

        if (n == 0) {
//            iget-object v0, p0, Lcom/zpj/hotfix/smali/Test;->bug:Lcom/zpj/hotfix/smali/Test_bug;
//            const-string v1, "方法名"
//            invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invoke(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

            if (isStatic) {
                builder.append("const-class v0, ").append(bugClazz);
            } else {
                builder.append("iget-object v0, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz);
            }
            builder.append("\n\n");
            builder.append("const-string v1, \"").append(methodName).append("\"");
            builder.append("\n\n");
            if (isStatic) {
                builder.append("invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invokeStatic(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;");
            } else {
                builder.append("invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invoke(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;");
            }

        } else {

            /*
                for (int i = 0; i < n; i++) {
                    // int: const/4或const/16
                    if (i > 7) {
                        const/16 v(3 + i), 0xi
                    } else {
                        const/4 v(3 + i), 0xi
                    }
                }
             */

            for (int i = 0; i < n; i++) {
                // TODO 0xi修改为i的8进制或16进制
                String num = "0x" + i;
                if (i > 7) {
                    builder.append("const/16 v").append(3 + i).append(", ").append(Long.toHexString(i)).append("\n\n");
                } else {
                    builder.append("const/4 v").append(3 + i).append(", 0x").append(i).append("\n\n");
                }
            }

            /*
                // 新建Class[]数组
                if (i > 7) {
                    const/16 v2, 0xi
                } else {
                    const/4 v2, 0xi
                }
                new-array v0, v2, [Ljava/lang/Class;
             */
            if (n > 7) {
                builder.append("const/16 v2, ").append(Long.toHexString(n)).append("\n\n");
            } else {
                builder.append("const/4 v2, 0x").append(n).append("\n\n");
            }
            builder.append("new-array v0, v2, [Ljava/lang/Class;").append("\n\n");

            /*
                // 将参数设置给数组
                for (int i = 0; i < n; i++) {
                    寄存器vx = v(3 + i)
                    if 是基础类型：
                        sget-object v2, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;
                        aput-object v2, v0, 寄存器vx
                    else:
                        const-class v2, Ljava/lang/String;
                        aput-object v2, v0, 寄存器vx
                }
             */
            for (int i = 0; i < n; i++) {
                // 寄存器v
                String v = "v" + (3 + i);
                String parameterType = parameterTypes.get(i);

                if (TypeHelper.isPrimitive(parameterType)) {
                    String wrapType = TypeHelper.getWrapType(parameterType);
                    builder.append("sget-object v2, ").append(wrapType).append("->TYPE:Ljava/lang/Class;").append("\n\n");
                } else {
                    builder.append("const-class v2, ").append(parameterType).append("\n\n");
                }
                builder.append("aput-object v2, v0, ").append(v).append("\n\n");
            }

            builder.append(".local v0, \"arr1\":[Ljava/lang/Class;").append("\n\n");

            // 新建Object[]数组
            // TODO 0xn
            if (n > 7) {
                builder.append("const/16 v2, 0x").append(n).append("\n\n");
            } else {
                builder.append("const/4 v2, 0x").append(n).append("\n\n");
            }
            builder.append("new-array v1, v2, [Ljava/lang/Object;").append("\n\n");

            /*
                for (int i = 0; i < n; i++) {
                    寄存器px = p(1 + i)
                    寄存器vx = v(3 + i)
                    if 基本数据类型：
                        invoke-static {寄存器px}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                        move-result-object v2
                        aput-object v2, v1, 寄存器vx
                    else:
                        aput-object px, v1, 寄存器vx
                }
             */
            for (int i = 0; i < n; i++) {
                int r = isStatic ? i : (1 + i);
                String p = "p" + r;
                String v = "v" + (3 + i);
                String parameterType = parameterTypes.get(i);
                if (TypeHelper.isPrimitive(parameterType)) {
                    String wrapType = TypeHelper.getWrapType(parameterType);
                    builder.append("invoke-static {").append(p).append("}, ").append(wrapType).append("->valueOf(")
                            .append(parameterType).append(")").append(wrapType).append("\n\n");
                    builder.append("move-result-object v2").append("\n\n");
                    builder.append("aput-object v2, v1, ").append(v).append("\n\n");
                } else {
                    builder.append("aput-object ").append(p).append(", v1, ").append(v).append("\n\n");
                }
            }

            builder.append(".local v1, \"arr2\":[Ljava/lang/Object; ").append("\n\n");

            // 获取bug对象
            // iget-object v0, p0, Lcom/zpj/hotfix/smali/Test;->bug:Lcom/zpj/hotfix/smali/Test_bug;
//            builder.append("iget-object v2, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz).append("\n\n");

            if (isStatic) {
                builder.append("const-class v2, ").append(bugClazz);
            } else {
                builder.append("iget-object v2, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz).append("\n\n");
            }


            // 方法名
            // const-string v1, "方法名"
            builder.append("const-string v3, \"").append(methodName).append("\"").append("\n\n");

            // 函数调用
            if (isStatic) {
                builder.append("invoke-static {v2, v3, v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invokeStatic(" +
                        "Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;");
            } else {
                builder.append("invoke-static {v2, v3, v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invoke(" +
                        "Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;");
            }
        }
        builder.append("\n\n");

        buildReturnSmali(builder, returnType);
        return builder.append(".end method").toString();
    }

    /**
     * .method private 获取字段方法名()字段类型
     *         .registers 3
     *         .annotation system Ldalvik/annotation/Throws;
     *             value = {
     *                 Ljava/lang/Exception;
     *             }
     *         .end annotation
     *
     *         .prologue
     *         iget-object v0, p0, fix类->bug:bug类
     *
     *         const-string v1, "字段名称"
     *
     *         invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->getField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
     *
     *         if 返回值是Object:
     *             move-result-object v0
     *             return-object v0
     *
     *         else if 返回值是基础类型:
     *             move-result-object v0
     *             check-cast v0, Ljava/lang/Integer;
     *             invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I
     *             move-result v0
     *             return v0
     *
     *          else:
     *             move-result-object v0
     *             check-cast v0, 字段类型
     *             return-object v0
     *
     *     .end method
     * @param fieldName
     * @param returnType
     * @param bugClazz
     * @param fixClass
     * @return
     */
    public static String buildGetMethod(String methodName, String fieldName, String returnType, String bugClazz, String fixClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(".method private " + methodName + "()" + returnType).append("\n\n");
        builder.append(".registers 3").append("\n\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("iget-object v0, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz).append("\n\n");
        builder.append("const-string v1, \"").append(fieldName).append("\"").append("\n\n");
        builder.append("invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->getField(" +
                "Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;").append("\n\n");

        buildReturnSmali(builder, returnType);
        return builder.append(".end method").toString();
    }

    /**
     *     .method private 设置字段方法(字段类型)V
     *         .registers 4
     *         .param p1, "参数名"    # 字段类型
     *         .annotation system Ldalvik/annotation/Throws;
     *             value = {
     *                 Ljava/lang/Exception;
     *             }
     *         .end annotation
     *
     *         .prologue
     *         iget-object v0, p0, Lcom/zpj/hotfix/patch_dev/field/Test_fix;->bug:Lcom/zpj/hotfix/patch_dev/field/Test_bug;
     *
     *         const-string v1, "字段名"
     *
     *         invoke-static {v0, v1, p1}, Lcom/zpj/hotfix/utils/Reflect;->setField(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
     *
     *         return-void
     *     .end method
     * @param fieldName
     * @param bugClazz
     * @param fixClass
     * @return
     */
    public static String buildSetMethod(String methodName, String fieldName, String bugClazz, String fixClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(".method private " + methodName + "()V").append("\n\n");
        builder.append(".registers 4").append("\n\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("iget-object v0, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz).append("\n\n");
        builder.append("const-string v1, \"").append(fieldName).append("\"").append("\n\n");
        builder.append("invoke-static {v0, v1, p1}, Lcom/zpj/hotfix/utils/Reflect;->setField(" +
                "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V").append("\n\n");

        buildReturnSmali(builder, null);
        return builder.append(".end method").toString();
    }

    private static void buildExceptionAnnotation(StringBuilder builder) {
//        builder.append(".annotation system Ldalvik/annotation/Throws;\n" +
//                "                value = {\n" +
//                "                    Ljava/lang/Exception;\n" +
//                "                }\n" +
//                "            .end annotation").append("\n\n");
        builder.append(".annotation system Ldalvik/annotation/Throws;").append("\n");
        builder.append("\t").append("value = {").append("\n");
        builder.append("\t\t").append("Ljava/lang/Exception;").append("\n");
        builder.append("\t").append("}").append("\n");
        builder.append(".end annotation").append("\n\n");
    }

}
