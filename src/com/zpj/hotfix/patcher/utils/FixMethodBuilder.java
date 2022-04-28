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
                boolean isWideType = TypeHelper.isWideType(returnType);
                String realType = TypeHelper.getRealType(returnType);
                String wrapType = TypeHelper.getWrapType(returnType);
                builder.append("check-cast v0, ").append(wrapType).append("\n\n");
                builder.append("invoke-virtual {v0}, ").append(wrapType).append("->").append(realType)
                        .append("Value()").append(returnType).append("\n\n");
                if (isWideType) {
                    builder.append("move-result-wide v0").append("\n\n");
                    builder.append("return-wide v0").append("\n\n");
                } else {
                    builder.append("move-result v0").append("\n\n");
                    builder.append("return v0").append("\n\n");
                }

            } else {
                // move-result-object v0
                // check-cast v0, 返回类型
                // return-object v0
                builder.append("check-cast v0, ").append(returnType).append("\n\n");
                builder.append("return-object v0").append("\n\n");
            }
        }
    }

    public static String buildAccessAddedMethod(String methodName, String parameterType, String returnType) {
        // TODO
        /**
         * 模板
         * .method private static getBugClass(Lcom/zpj/hotfix/demo/BugClass;)Lcom/zpj/hotfix/demo/BugClass;
         *     .registers 2
         *     .param p0, "bugClass"    # Lcom/zpj/hotfix/demo/BugClass;
         *     .annotation system Ldalvik/annotation/Throws;
         *         value = {
         *             Ljava/lang/Exception;
         *         }
         *     .end annotation
         *
         *     .prologue
         *     const-class v0, Lcom/zpj/hotfix/demo/BugClass;
         *
         *     invoke-static {p0, v0}, Lcom/zpj/hotfix/FixObjectManager;->get(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
         *
         *     move-result-object v0
         *
         *     check-cast v0, Lcom/zpj/hotfix/demo/BugClass;
         *
         *     return-object v0
         * .end method
         */

        StringBuilder builder = new StringBuilder();


        builder.append(".method private static ").append(methodName).append("(").append(parameterType)
                .append(")").append(returnType).append("\n\n");


        builder.append(".registers 2").append("\n\n");

        builder.append(".param p0, \"bugObj\"    # ").append(parameterType).append("\n\n");

        buildExceptionAnnotation(builder);

        builder.append(".prologue").append("\n\n");

        builder.append("const-class v0, ").append(returnType).append("\n\n");

        builder.append("invoke-static {p0, v0}, Lcom/zpj/hotfix/FixObjectManager;->get(" +
                "Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;").append("\n\n");

        builder.append("move-result-object v0").append("\n\n");

        builder.append("check-cast v0, ").append(returnType).append("\n\n");

        builder.append("return-object v0").append("\n\n");



        return builder.append(".end method").toString();
    }

    public static String buildAccessMethod(String methodName, String name, List<String> parameterTypes, String returnType,
                                           String bugClazz, boolean isStatic) {
        StringBuilder builder = new StringBuilder();

        // .method private 方法名(所有参数类型)返回类型
        String parameters = String.join("", parameterTypes);
        builder.append(".method private static ").append(methodName)
                .append("(").append(parameters).append(")").append(returnType);
        builder.append("\n\n");

        int n = parameterTypes.size();
        int registers;
        if (n == 0) {
            registers = 3;
        } else {
//            registers = n + (isStatic ? 4 : 5);
            registers = n + 5;
            for (String type : parameterTypes) {
                if (TypeHelper.isWideType(type)) {
                    registers++;
                }
            }
        }
        builder.append(".registers ").append(registers).append("\n\n");

        // 参数
        /*
            int index = isStatic ? 0 : 1;
            for (int i = 0; i < n; i++) {
                .param p[index], "arg[i]"   # 类型
                if args[i]是long或double:
                    index++
                index++;
            }
         */

        if (!isStatic) {
            builder.append(".param p0, \"_this$p0\"   # ").append(bugClazz).append("\n\n");
        }
        int index = isStatic ? 0 : 1;
        for (int i = 0; i < n; i++) {
            String type = parameterTypes.get(i);
            builder.append(".param p").append(index++).append(", \"arg").append(i).append("\"   # ").append(type).append("\n\n");
            if (TypeHelper.isWideType(type)) {
                ++index;
            }
        }

        // 抛出异常
        buildExceptionAnnotation(builder);

        builder.append(".prologue\n\n");

        if (n == 0) {

            if (isStatic) {
                builder.append("const-class v0, ").append(bugClazz);
            } else {
                builder.append("move-object v0, p0");
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
                // 新建Class[]数组
                if (n >= 8) {
                    const/16 v2, 0xn
                } else {
                    const/4 v2, 0xn
                }

                new-array v0, v2, [Ljava/lang/Class;
             */
            if (n >= 8) {
                builder.append("const/16 v2, 0x").append(Long.toHexString(n)).append("\n\n");
            } else {
                builder.append("const/4 v2, 0x").append(n).append("\n\n");
            }
            builder.append("new-array v0, v2, [Ljava/lang/Class;").append("\n\n");

            /*
                // 将参数设置给数组
                for (int i = 0; i < n; i++) {
                    if (i >= 8) {
                        const/16 v2, 0xi
                    } else {
                        const/4 v2, 0xi
                    }
                    if args[i]是基本数据类型:
                        sget-object v3, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;
                    else
                        const-class v3, Ljava/lang/String;
                    aput-object v3, v1, v2
                }

                .local v0, "arr1":[Ljava/lang/Class;, "[Ljava/lang/Class<*>;"
             */
            for (int i = 0; i < n; i++) {
                if (i >= 8) {
                    builder.append("const/16 v2, 0x").append(Long.toHexString(i)).append("\n\n");
                } else {
                    builder.append("const/4 v2, 0x").append(i).append("\n\n");
                }

                String parameterType = parameterTypes.get(i);
                if (TypeHelper.isPrimitive(parameterType)) {
                    String wrapType = TypeHelper.getWrapType(parameterType);
                    builder.append("sget-object v3, ").append(wrapType).append("->TYPE:Ljava/lang/Class;").append("\n\n");
                } else {
                    builder.append("const-class v3, ").append(parameterType).append("\n\n");
                }
                builder.append("aput-object v3, v1, v2").append("\n\n");
            }

            builder.append(".local v0, \"arr1\":[Ljava/lang/Class;, \"[Ljava/lang/Class<*>;\"").append("\n\n");

            // 新建Object[]数组
            if (n >= 8) {
                builder.append("const/16 v2, 0x").append(Long.toHexString(n)).append("\n\n");
            } else {
                builder.append("const/4 v2, 0x").append(n).append("\n\n");
            }
            builder.append("new-array v1, v2, [Ljava/lang/Object;").append("\n\n");

        /*
            int index = isStatic ? 0 : 1;
            for (int i = 0; i < n; i++) {
                if (i >= 8) {
                    const/16 v2, 0xi
                } else {
                    const/4 v2, 0xi
                }
                if args[i]是基本数据类型:
                    # 以int为例
                    boolean isWidthType = args[i]是long或double
                    int start = index;
                    int end = isWidthType ? ++index : index;
                    if (start > 11 || end > 11) {
                        invoke-static/range {p[start] .. p[end]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                    } else {
                        if isWidthType:
                            invoke-static {p[start], p[end]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                        else
                            invoke-static {p[start]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                    }
                    move-result-object v3
                    aput-object v3, v1, v2
                else
                    aput-object p[index], v1, v2
                index++
            }

            .local v1, "arr2":[Ljava/lang/Object;
         */
            index = isStatic ? 0 : 1;
            for (int i = 0; i < n; i++) {
                if (i >= 8) {
                    builder.append("const/16 v1, 0x").append(Long.toHexString(i));
                } else {
                    builder.append("const/4 v1, 0x").append(i);
                }
                builder.append("\n\n");

                String type = parameterTypes.get(i);
                if (TypeHelper.isPrimitive(type)) {
                    boolean isWidthType = TypeHelper.isWideType(type);
                    String wrapType = TypeHelper.getWrapType(type);
                    int start = index;
                    int end = isWidthType ? ++index : index;

                    if (start > 11 || end > 11) {
                        builder.append("invoke-static/range {p").append(start).append(" .. p").append(end);
                    } else {
                        builder.append("invoke-static {p").append(start);
                        if (isWidthType) {
                            builder.append(", p").append(end);
                        }
                    }
                    builder.append("}, ").append(wrapType).append("->valueOf(").append(type)
                            .append(")").append(wrapType).append("\n\n");
                    builder.append("move-result-object v3").append("\n\n");
                    builder.append("aput-object v3, v1, v2");
                } else {
                    builder.append("aput-object p").append(index).append(", v1, v2");
                }
                builder.append("\n\n");
                index++;
            }

            builder.append(".local v1, \"arr2\":[Ljava/lang/Object; ").append("\n\n");

            if (isStatic) {
                builder.append("const-class v2, ").append(bugClazz);
            } else {
                builder.append("move-object v2, p0");
            }
            builder.append("\n\n");

            // 方法名
            builder.append("const-string v3, \"").append(name).append("\"").append("\n\n");

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

    public static String buildAccessSuperMethod(String methodName, List<String> parameterTypes,
                                                String returnType, String bugClazz) {
        StringBuilder builder = new StringBuilder();

        // .method private 方法名(所有参数类型)返回类型
        String parameters = String.join("", parameterTypes);
        String superMethodName = "_super_" + methodName;
        builder.append(".method private static ").append(superMethodName)
                .append("(").append(parameters).append(")").append(returnType);
        builder.append("\n\n");

        /*
            # 寄存器数 = 1（p0） + n（p1~pn） + 4（v0~v3） = n + 5
            # 参数中每有一个long或double，寄存器数加1
            int count = n + 5;
            for arg : args {
                if arg是long或double
                    count++;
            }
            .registers count
         */

        int n = parameterTypes.size();
        int count = n + 5;
        for (String type : parameterTypes) {
            if (TypeHelper.isWideType(type)) {
                count++;
            }
        }
        builder.append(".registers ").append(count).append("\n\n");

        /*
            # 定义参数寄存器
            int index = 1;
            for (int i = 0; i < n; i++) {
                .param p[index], "arg[i]"   # 类型
                if args[i]是long或double:
                    index++
                index++;
            }
         */
        builder.append(".param p0, \"_this$p0\"   # ").append(bugClazz).append("\n\n");
        int index = 1;
        for (int i = 0; i < n; i++) {
            String type = parameterTypes.get(i);
            builder.append(".param p").append(index++).append(", \"arg").append(i).append("\"   # ").append(type).append("\n\n");
            if (TypeHelper.isWideType(type)) {
                ++index;
            }
        }

        buildExceptionAnnotation(builder);

        builder.append(".prologue").append("\n\n");

        /*
            const/16 v1, 0xn
            new-array v0, v1, [Ljava/lang/Object;
         */
        if (n >= 8) {
            builder.append("const/16 v1, 0x").append(Long.toHexString(n));
        } else {
            builder.append("const/4 v1, 0x").append(n);
        }
        builder.append("\n\n");

        // 创建Object[]数组
        builder.append("new-array v0, v1, [Ljava/lang/Object;").append("\n\n");

        /*
            int index = 1;
            for (int i = 0; i < n; i++) {
                const/4 v1, 0xi
                if args[i]是基本数据类型:
                    # 以int为例
                    if index > 11 {
                        if 是long或double:
                            invoke-static/range {p[index] .. p[++index]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                        else
                            invoke-static/range {p[index] .. p[index]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                    } else {
                        if 是long或double:
                            invoke-static {p[index], p[++index]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                        else
                            invoke-static {p[index]}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
                    }

                    move-result-object v2
                    aput-object v2, v0, v1
                else
                    aput-object p[index], v0, v1
                index++
            }
         */
        index = 1;
        for (int i = 0; i < n; i++) {
            if (i >= 8) {
                builder.append("const/16 v1, 0x").append(Long.toHexString(i));
            } else {
                builder.append("const/4 v1, 0x").append(i);
            }
            builder.append("\n\n");

            String type = parameterTypes.get(i);
            if (TypeHelper.isPrimitive(type)) {
                boolean isWidthType = TypeHelper.isWideType(type);
                String wrapType = TypeHelper.getWrapType(type);
                if (index > 11) {
                    if (isWidthType) {
                        builder.append("invoke-static/range {p").append(index).append(" .. p").append(++index);
                    } else {
                        builder.append("invoke-static/range {p").append(index).append(" .. p").append(index);
                    }
                } else {
                    if (isWidthType) {
                        builder.append("invoke-static {p").append(index).append(", p").append(++index);
                    } else {
                        builder.append("invoke-static {p").append(index);
                    }
                }
                builder.append("}, ").append(wrapType).append("->valueOf(").append(type)
                        .append(")").append(wrapType).append("\n\n");
                builder.append("move-result-object v2").append("\n\n");
                builder.append("aput-object v2, v0, v1");
            } else {
                builder.append("aput-object p").append(index).append(", v0, v1");
            }
            builder.append("\n\n");
            index++;
        }

        // .local v0, "arr":[Ljava/lang/Object;
        builder.append(".local v0, \"arr\":[Ljava/lang/Object;").append("\n\n");

        // move-object v1, p0
        builder.append("move-object v1, p0");

        // const-string v2, "方法名"
        builder.append("const-string v2, \"").append(methodName).append("\"").append("\n\n");

        // const-string v3, "方法签名"
        String signature = MethodUtils.getMethodSignature(parameterTypes, returnType);
        builder.append("const-string v3, \"").append(signature).append("\"").append("\n\n");

        // invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperVoid(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        if ("V".equals(returnType)) {
            builder.append("invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperVoid(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V");
        } else {
            builder.append("invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperObject(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;");
        }

        builder.append("\n\n");

        buildReturnSmali(builder, returnType);
        return builder.append(".end method").toString();
    }

    /**
     * .method private static getA(Lcom/zpj/hotfix/demo/patch_dev/field/Test;)I
     *     .registers 2
     *     .param p0, "test"    # Lcom/zpj/hotfix/demo/patch_dev/field/Test;
     *     .annotation system Ldalvik/annotation/Throws;
     *         value = {
     *             Ljava/lang/Exception;
     *         }
     *     .end annotation
     *
     *     .prologue
     *     .line 18
     *     const-string v0, "a"
     *
     *     invoke-static {p0, v0}, Lcom/zpj/hotfix/utils/Reflect;->getField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
     *
     *     move-result-object v0
     *
     *     check-cast v0, Ljava/lang/Integer;
     *
     *     invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I
     *
     *     move-result v0
     *
     *     return v0
     * .end method
     * @param methodName
     * @param fieldName
     * @param returnType
     * @param bugClazz
     * @return
     */
    public static String buildGetFieldMethod(String methodName, String fieldName, String returnType, String bugClazz) {
        StringBuilder builder = new StringBuilder();
        boolean isWideType = TypeHelper.isWideType(returnType);
        builder.append(".method private static ").append(methodName).append("(").append(bugClazz).append(")")
                .append(returnType).append("\n");
        builder.append(".registers ").append(isWideType ? 3 : 2).append("\n");
        builder.append(".param p0, \"_this$p0\"    # ").append(bugClazz).append("\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("const-string v0, \"").append(fieldName).append("\"").append("\n\n");
        builder.append("invoke-static {p0, v0}, Lcom/zpj/hotfix/utils/Reflect;->getField(" +
                "Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;").append("\n\n");

        buildReturnSmali(builder, returnType);
        return builder.append(".end method").toString();
    }

    /**
     * .method private static setB(Lcom/zpj/hotfix/demo/patch_dev/field/Test;I)V
     *     .registers 4
     *     .param p0, "test"    # Lcom/zpj/hotfix/demo/patch_dev/field/Test;
     *     .param p1, "b"    # I
     *     .annotation system Ldalvik/annotation/Throws;
     *         value = {
     *             Ljava/lang/Exception;
     *         }
     *     .end annotation
     *
     *     .prologue
     *     const-string v0, "b"
     *     invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
     *     move-result-object v1
     *     invoke-static {p0, v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->setField(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
     *
     *     .line 25
     *     return-void
     * .end method
     * @param methodName
     * @param fieldName
     * @param fieldType
     * @param bugClazz
     * @return
     */
    public static String buildSetFieldMethod(String methodName, String fieldName, String fieldType,
                                             String bugClazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(".method private static ").append(methodName).append("(").append(bugClazz).append(fieldType).append(")V").append("\n\n");
        builder.append(".registers 4").append("\n");
        builder.append(".param p0 \"_this$p0\"    # ").append(bugClazz).append("\n");
        builder.append(".param p1, \"value\"").append("\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("const-string v0, \"").append(fieldName).append("\"").append("\n\n");

        if (TypeHelper.isPrimitive(fieldType)) {
            String wrapType = TypeHelper.getWrapType(fieldType);
            builder.append("invoke-static {p1}, ").append(wrapType).append("->valueOf(")
                    .append(fieldType).append(")").append(wrapType).append("\n\n");
            builder.append("move-result-object v1").append("\n\n");
        } else {
            builder.append("move-object v1, p1").append("\n\n");
        }

        builder.append("invoke-static {p0, v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->setField(" +
                "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V").append("\n\n");

        buildReturnSmali(builder, null);
        return builder.append(".end method").toString();
    }

    /**
     * .method public static testGet()I
     *     .registers 2
     *     .annotation system Ldalvik/annotation/Throws;
     *         value = {
     *             Ljava/lang/Exception;
     *         }
     *     .end annotation
     *
     *     .prologue
     *     .line 16
     *     const-class v0, Lcom/zpj/hotfix/patch_dev/field/Test;
     *
     *     const-string v1, "a"
     *
     *     invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->getStaticField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
     *
     *     move-result-object v0
     *
     *     check-cast v0, Ljava/lang/Integer;
     *
     *     invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I
     *
     *     move-result v0
     *
     *     return v0
     * .end method
     * @param methodName
     * @param fieldName
     * @param returnType
     * @param bugClazz
     * @return
     */
    public static String buildGetStaticFieldMethod(String methodName, String fieldName, String returnType, String bugClazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(".method private static").append(methodName).append("()").append(returnType).append("\n\n");
        builder.append(".registers 2").append("\n\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("const-class v0, ").append(bugClazz).append("\n\n");
        builder.append("const-string v1, \"").append(fieldName).append("\"").append("\n\n");
        builder.append("invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->getStaticField(" +
                "Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;").append("\n\n");

        buildReturnSmali(builder, returnType);
        return builder.append(".end method").toString();
    }

    /**
     * .method public static testSet(Ljava/lang/Object;)V
     *     .registers 3
     *     .param p0, "value"    # Ljava/lang/Object;
     *     .annotation system Ldalvik/annotation/Throws;
     *         value = {
     *             Ljava/lang/Exception;
     *         }
     *     .end annotation
     *
     *     .prologue
     *     const-class v0, Lcom/zpj/hotfix/patch_dev/field/Test;
     *     const-string v1, "a"
     *     invoke-static {v0, v1, p0}, Lcom/zpj/hotfix/utils/Reflect;->setStaticField(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V
     *
     *     return-void
     * .end method
     * @param methodName
     * @param fieldName
     * @param bugClazz
     * @return
     */
    public static String buildSetStaticFieldMethod(String methodName, String fieldName, String fieldType, String bugClazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(".method private ").append(methodName).append("()V").append("\n");
        builder.append(".registers 3").append("\n\n");
        builder.append(".param p0, \"value\"    # ").append(fieldType).append("\n");
        buildExceptionAnnotation(builder);
        builder.append(".prologue").append("\n\n");
        builder.append("const-class v0, ").append(bugClazz).append("\n\n");
        builder.append("const-string v1, \"").append(fieldName).append("\"").append("\n\n");
        builder.append("invoke-static {v0, v1, p0}, Lcom/zpj/hotfix/utils/Reflect;->setStaticField(" +
                "Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V").append("\n\n");

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
