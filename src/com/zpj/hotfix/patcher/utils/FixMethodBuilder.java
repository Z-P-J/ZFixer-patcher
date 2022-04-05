package com.zpj.hotfix.patcher.utils;

import java.util.Arrays;
import java.util.Collections;
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
            // TODO 基本数据类型
            if ("Ljava/lang/Object;".equals(returnType)) {
                builder.append("move-result-object v0").append("\n\n");
                builder.append("return-object v0").append("\n\n");
            } else {
                // move-result-object v0
                // check-cast v0, 返回类型
                // return-object v0
                builder.append("move-result-object v0").append("\n\n");
                builder.append("check-cast v0, ").append(returnType).append("\n\n");
                builder.append("return-object v0").append("\n\n");
            }
        }
    }

    public static String buildAccessMethod(String methodName, List<String> parameterTypes, String returnType, String bugClazz, String fixClass) {
        StringBuilder builder = new StringBuilder();

        // .method private 方法名(所有参数类型)返回类型
        String parameters = String.join("", parameterTypes);
        builder.append(".method private ").append(methodName).append("(").append(parameters).append(")").append(returnType);
        builder.append("\n\n");

        int n = parameterTypes.size();
        int registers = n == 0 ? 3 : 2 * n + 6;
        builder.append(".registers ").append(registers).append("\n\n");

        // 参数
        for (int i = 0; i < n; i++) {
            builder.append(".param p").append(1 + i).append(", \"arg").append(i).append("\"").append("\n\n");
        }

//                    .registers 3
//                    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
//                        clazz = "com.zpj.hotfix.demo.BugClass"
//                        method = "testPrivateMethod"
//                    .end annotation
//
//                    .annotation system Ldalvik/annotation/Throws;
//                        value = {
//                                Ljava/lang/Exception;
//                        }
//                    .end annotation

        // 注解
        // TODO 修改com.zpj.hotfix.demo.BugClass
//        builder.append(".annotation runtime Lcom/zpj/hotfix/annotation/Fix;\n" +
//                "                        clazz = \"com.zpj.hotfix.demo.BugClass\"\n" +
//                "                        method = \"" + methodName + "\"\n" +
//                "                    .end annotation").append("\n\n");

        builder.append(".annotation runtime Lcom/zpj/hotfix/annotation/Fix;").append("\n");
        builder.append("\t").append("clazz = \"com.zpj.hotfix.demo.BugClass\"").append("\n");
        builder.append("\t").append("method = \"").append(methodName).append("\"").append("\n");
        builder.append(".end annotation").append("\n\n");

        // 抛出异常
        buildExceptionAnnotation(builder);

        builder.append(".prologue\n\n");

        if (n == 0) {



//            .prologue
//            iget-object v0, p0, Lcom/zpj/hotfix/smali/Test;->bug:Lcom/zpj/hotfix/smali/Test_bug;
//
//            const-string v1, "方法名"
//
//            invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invoke(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

            builder.append("iget-object v0, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz);
            builder.append("\n\n");

            builder.append("const-string v1, \"").append(methodName).append("\"");

            builder.append("\n\n");

            builder.append("invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->invoke(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;");


        } else {




            for (int i = 0; i < n + 1; i++) {
                builder.append("const/4 v").append(4 + i).append(", 0x").append(i).append("\n\n");
            }

            // 获取bug对象
            // iget-object v0, p0, Lcom/zpj/hotfix/smali/Test;->bug:Lcom/zpj/hotfix/smali/Test_bug;
            builder.append("iget-object v0, p0, ").append(fixClass).append("->mBugObj:").append(bugClazz).append("\n\n");


            // 方法名
            // const-string v1, "方法名"
            builder.append("const-string v1, \"").append(methodName).append("\"").append("\n\n");

            // 新建Class[]数组
            // new-array v2, v(4+n), [Ljava/lang/Class;
            builder.append("new-array v2, v").append(4 + n).append(", [Ljava/lang/Class;");

            // 将参数设置给数组
            for (int i = 0; i < n; i++) {

                // 寄存器
                String v = "v" + (4 + i);

//                寄存器vx = v(4 + i)
//                if 是基础类型:
//                    sget-object v3, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;
//                    aput-object v3, v2, 寄存器vx
//                else:
//                    const-class v3, Ljava/lang/String;
//                    aput-object v3, v2, 寄存器vx

                String parameterType = parameterTypes.get(i);
//                // TODO
//                if (是基本数据类型) {
//
//                }
                builder.append("const-class v3, ").append(parameterType).append("\n\n");
                builder.append("aput-object v3, v2, ").append(v).append("\n\n");

            }

            // 新建Object[]数组
            // new-array v3, v(4+n), [Ljava/lang/Object;
            builder.append("new-array v2, v").append(4 + n).append(", [Ljava/lang/Object;").append("\n\n");

            for (int i = 0; i < n; i++) {
                // TODO 考虑基本数据类型情况
                builder.append("aput-object p").append(1 + i).append(", v3, v").append(4 + i).append("\n\n");
            }

            // 函数调用
            builder.append("invoke-static {v0, v1, v2, v3}, Lcom/zpj/hotfix/utils/Reflect;->" +
                    "invoke(Ljava/lang/Object;Ljava/lang/String;" +
                    "[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;").append("\n\n");
        }

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

        buildReturnSmali(builder, null);
        return builder.append(".end method").toString();
    }

    private static String buildVoidMethod(String name, List<String> parameters,
                                          List<String> parameterTypes) {
        return ".method private 方法名()返回值\n" +
                "    .registers 3\n" +
                "    .annotation system Ldalvik/annotation/Throws;\n" +
                "        value = {\n" +
                "            Ljava/lang/Exception;\n" +
                "        }\n" +
                "    .end annotation\n" +
                "\n" +
                "    .prologue\n" +
                "    .line 18\n" +
                "    iget-object v0, p0, 修复方法->bug:问题对象;\n" +
                "\n" +
                "    const-string v1, \"fangfaming\"\n" +
                "\n" +
                "    invoke-static {v0, v1}, Lcom/zpj/sdk/Reflect;->invoke(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;\n" +
                "\n" +
                "    move-result-object v0\n" +
                "\n" +
                "    return-object v0\n" +
                ".end method";
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
