//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zpj.hotfix.patcher.fix;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.annotation.MethodFixAnnotaion;
import com.zpj.hotfix.patcher.diff.DiffInfo;
import org.jf.baksmali.Adaptors.*;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.*;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.util.IndentingWriter;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.util.*;

public class FixClassDefinition extends ClassDefinition {
    public final baksmaliOptions options;
    public final ClassDef classDef;

    private final String bugType;
    private final String fixType;

    public FixClassDefinition(baksmaliOptions options, ClassDef classDef) {
        super(options, classDef);
        this.options = options;
        this.classDef = classDef;
        this.bugType = this.classDef.getType();
        this.fixType = bugType.substring(0, bugType.length() - 1) + "_Fix;";
    }

    public void writeTo(IndentingWriter writer) throws IOException {
        this.writeClass(writer);
        this.writeSuper(writer);
        this.writeSourceFile(writer);
        this.writeInterfaces(writer);
        this.writeAnnotations(writer);
        Set<String> staticFields = this.writeStaticFields(writer);
        this.writeInstanceFields(writer, staticFields);
        Set<String> directMethods = this.writeDirectMethods(writer);
        this.writeVirtualMethods(writer, directMethods);
    }

    private void writeClass(IndentingWriter writer) throws IOException {
        writer.write(".class ");
        this.writeAccessFlags(writer);
        writer.write(fixType);
        writer.write(10);
    }

    private void writeAccessFlags(IndentingWriter writer) throws IOException {
        AccessFlags[] var2 = AccessFlags.getAccessFlagsForClass(this.classDef.getAccessFlags());
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            AccessFlags accessFlag = var2[var4];
            writer.write(accessFlag.toString());
            writer.write(32);
        }

    }

    private void writeSuper(IndentingWriter writer) throws IOException {
        String superClass = this.classDef.getSuperclass();
        if (superClass != null) {
            writer.write(".super ");
            writer.write(superClass);
            writer.write(10);
        }
    }

    private void writeSourceFile(IndentingWriter writer) throws IOException {
        String sourceFile = this.classDef.getSourceFile();
        if (sourceFile != null) {
            writer.write(".source \"");
            StringUtils.writeEscapedString(writer, sourceFile.replace(".", "_Fix."));
            writer.write("\"\n");
        }
    }

    private void writeInterfaces(IndentingWriter writer) throws IOException {


    }

    private void writeAnnotations(IndentingWriter writer) throws IOException {

    }

    private Set<String> writeStaticFields(IndentingWriter writer) throws IOException {
        return Collections.emptySet();
    }

    private void writeInstanceFields(IndentingWriter writer, Set<String> staticFields) throws IOException {
        writer.write("\n# instance fields\n" +
                ".field private final mBugObj:" + bugType + "\n\n");

        // TODO
    }

    private Set<String> writeDirectMethods(IndentingWriter writer) throws IOException {
        writer.write("# direct methods\n" +
                ".method public constructor <init>(" + bugType + ")V\n" +
                "    .registers 2\n" +
                "    .param p1, \"mBugObj\"\n" +
                "\n" +
                "    .prologue\n" +
                "    invoke-direct {p0}, Ljava/lang/Object;-><init>()V\n" +
                "\n" +
                "    iput-object p1, p0, " + fixType + "->mBugObj:" + bugType + "\n" +
                "\n" +
                "    return-void\n" +
                ".end method\n");


//        List<String> newMethodList = Patcher.getNewMethodList();
//        System.out.println("writeDirectMethods newMethodSize=" + newMethodList.size());
//        for (String newMethod : newMethodList) {
//            writer.write(newMethod);
//            writer.write("\n\n");
//        }
//        newMethodList.clear();

        return Collections.emptySet();
    }

    private void writeVirtualMethods(IndentingWriter writer, Set<String> directMethods) throws IOException {
        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet();
        Iterable virtualMethods;
        Set<? extends Method> modifieds = null;
        if (this.classDef instanceof DexBackedClassDef) {
            virtualMethods = ((DexBackedClassDef)this.classDef).getVirtualMethods(false);
            modifieds = DiffInfo.getInstance().getModifiedMethods();
        } else {
            virtualMethods = this.classDef.getVirtualMethods();
        }

        for (Object virtualMethod : virtualMethods) {
            FixMethod method = new FixMethod((Method) virtualMethod);
            System.out.println("writeVirtualMethods method=" + method.getName());
            if (modifieds != null && modifieds.contains(method)) {
                ((DexBackedMethod) virtualMethod).setMethodReplace(new MethodFixAnnotaion(method.getDefiningClass(), method.getName()));
            }
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# virtual methods");
                wroteHeader = true;
            }

            writer.write(10);
            String methodString = ReferenceUtil.getMethodDescriptor(method, true);
            System.out.println("writeVirtualMethods methodString=" + methodString);
            IndentingWriter methodWriter = writer;
            if (!writtenMethods.add(methodString)) {
                writer.write("# duplicate method ignored\n");
                methodWriter = new CommentingIndentingWriter(writer);
            } else if (directMethods.contains(methodString)) {
                writer.write("# There is both a direct and virtual method with this signature.\n# You will need to rename one of these methods, including all references.\n");
                System.err.println(String.format("Duplicate direct+virtual method found: %s->%s", this.classDef.getType(), methodString));
                System.err.println("You will need to rename one of these methods, including all references.");
            }

            MethodImplementation methodImpl = method.getImplementation();
            if (methodImpl == null) {
                MethodDefinition.writeEmptyMethodTo(methodWriter, method, this.options);
            } else {
                FixMethodDefinition methodDefinition = new FixMethodDefinition(this, method, methodImpl);
                methodDefinition.writeTo(methodWriter);
            }
        }

        List<String> newMethodList = Patcher.getInjectMethodList();
        System.out.println("writeVirtualMethods newMethodSize=" + newMethodList.size());
        for (String newMethod : newMethodList) {
            writer.write("\n\n");
            writer.write(newMethod);

        }
        Patcher.clear();

//        writer.write("\n.method public test2222(Ljava/lang/String;)V\n" +
//                "    .locals 2\n" +
//                "    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;\n" +
//                "        method = \"test2\"\n" +
//                "        clazz = \"com.zpj.sdk.TestSdk\"\n" +
//                "    .end annotation\n" +
//                "\n" +
//                "    .line 25\n" +
//                "    iget-object v0, p0, Lcom/zpj/sdk/TestSdk;->test:Lcom/zpj/sdk/Test;\n" +
//                "\n" +
//                "    const-string v1, \"test2\"\n" +
//                "\n" +
//                "    invoke-virtual {v0, v1}, Lcom/zpj/sdk/Test;->test(Ljava/lang/String;)V\n" +
//                "\n" +
//                "    .line 26\n" +
//                "    iget-object v0, p0, Lcom/zpj/sdk/TestSdk;->context:Landroid/content/Context;\n" +
//                "\n" +
//                "    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;\n" +
//                "\n" +
//                "    move-result-object v0\n" +
//                "\n" +
//                "    const/4 v1, 0x0\n" +
//                "\n" +
//                "    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;\n" +
//                "\n" +
//                "    move-result-object p1\n" +
//                "\n" +
//                "    invoke-virtual {p1}, Landroid/widget/Toast;->show()V\n" +
//                "\n" +
//                "    return-void\n" +
//                ".end method");

    }
}
