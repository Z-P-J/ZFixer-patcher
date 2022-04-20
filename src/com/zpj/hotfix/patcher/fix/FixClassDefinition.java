//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zpj.hotfix.patcher.fix;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.annotation.MethodFixAnnotaion;
import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import com.zpj.hotfix.patcher.utils.MethodUtils;
import com.zpj.hotfix.patcher.utils.TypeHelper;
import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.Adaptors.CommentingIndentingWriter;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.util.IndentingWriter;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.util.*;

public class FixClassDefinition extends ClassDefinition {

    private final Map<String, String> injectMethodMap = new HashMap<>();

    public final baksmaliOptions options;
    public final FixClassDef classDef;

    private final DiffClassInfo superClassInfo;

    /**
     * 修复类以普通类的形式（false）还是内部类的形式（true）
     */
    private boolean innerClassMode = true;

    public FixClassDefinition(baksmaliOptions options, FixClassDef classDef) {
        super(options, classDef);
        this.options = options;
        this.classDef = classDef;
        superClassInfo = Patcher.getClassInfo(this.classDef.getSuperclass());
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

        List<String> newMethodList = getInjectMethodList();
        for (String newMethod : newMethodList) {
            writer.write("\n\n");
            writer.write(newMethod);
        }
    }

    private void writeClass(IndentingWriter writer) throws IOException {
        writer.write(".class ");
        this.writeAccessFlags(writer);
        writer.write(this.classDef.getFixType());
        writer.write(10);
    }

    private void writeAccessFlags(IndentingWriter writer) throws IOException {
        AccessFlags[] var2 = AccessFlags.getAccessFlagsForClass(this.classDef.getAccessFlags());
        for (AccessFlags accessFlag : var2) {
            writer.write(accessFlag.toString());
            writer.write(32);
        }
    }

    private void writeSuper(IndentingWriter writer) throws IOException {
        writer.write(".super ");
        if (superClassInfo != null && superClassInfo.isModified()) {
            writer.write(superClassInfo.getFixType());
        } else {
            writer.write(TypeHelper.TYPE_OBJECT);
        }
        writer.write(10);
    }

    private void writeSourceFile(IndentingWriter writer) throws IOException {
        String sourceFile = this.classDef.getSourceFile();
        if (sourceFile != null) {
            writer.write(".source \"");
            if (innerClassMode) {
                StringUtils.writeEscapedString(writer, sourceFile);
            } else {
                StringUtils.writeEscapedString(writer, sourceFile.replace(".", "_Fix."));
            }
            writer.write("\"\n");
        }
    }

    private void writeInterfaces(IndentingWriter writer) throws IOException {


    }

    private void writeAnnotations(IndentingWriter writer) throws IOException {
        if (innerClassMode) {
//            writer.write("\n# annotations\n" +
//                    ".annotation system Ldalvik/annotation/EnclosingClass;\n" +
//                    "    value = " + this.classDef.getType() + "\n" +
//                    ".end annotation\n" +
//                    "\n" +
//                    ".annotation system Ldalvik/annotation/InnerClass;\n" +
//                    "    accessFlags = 0x0\n" +
//                    "    name = null\n" +
//                    ".end annotation\n\n");

            writer.write("\n.annotation system Ldalvik/annotation/InnerClass;\n" +
                    "    accessFlags = 0x0\n" +
                    "    name = null\n" +
                    ".end annotation\n\n");
        }
    }

    private Set<String> writeStaticFields(IndentingWriter writer) throws IOException {
        return Collections.emptySet();
    }

    private void writeInstanceFields(IndentingWriter writer, Set<String> staticFields) throws IOException {
//        if (superClassInfo != null && superClassInfo.isModified()) {
//            return;
//        }
        if (innerClassMode) {
            writer.write("\n# instance fields\n" +
                    ".field private final synthetic mBugObj:" + this.classDef.getType() + "\n\n");
        } else {
            writer.write("\n# instance fields\n" +
                    ".field private final mBugObj:" + this.classDef.getType() + "\n\n");
        }


        // TODO
    }

    private Set<String> writeDirectMethods(IndentingWriter writer) throws IOException {
        if (superClassInfo != null && superClassInfo.isModified()) {
            writer.write(".method public constructor <init>(" + this.classDef.getType() + ")V\n" +
                    "    .registers 2\n" +
                    "    .param p1, \"test\"    # " + this.classDef.getType() + "\n" +
                    "\n" +
                    "    .prologue\n" +
                    "    invoke-direct {p0, p1}, " + superClassInfo.getFixType() + "-><init>(" + superClassInfo.getType() + ")V\n" +
                    "\n" +
                    "    iput-object p1, p0, " + this.classDef.getFixType() + "->mBugObj:" + this.classDef.getType() + "\n" +
                    "\n" +
                    "    return-void\n" +
                    ".end method");
        } else {
            writer.write("# direct methods\n" +
                    ".method public constructor <init>(" + this.classDef.getType() + ")V\n" +
                    "    .registers 2\n" +
                    "    .param p1, \"mBugObj\"\n" +
                    "\n" +
                    "    .prologue\n" +
                    "    invoke-direct {p0}, Ljava/lang/Object;-><init>()V\n" +
                    "\n" +
                    "    iput-object p1, p0, " + this.classDef.getFixType() + "->mBugObj:" + this.classDef.getType() + "\n" +
                    "\n" +
                    "    return-void\n" +
                    ".end method\n");
        }

        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet<>();

        DiffClassInfo classInfo = this.classDef.getClassInfo();
        for (DexBackedMethod method : this.classDef.getDirectMethods(false)) {
//            System.out.println("writeDirectMethods method=" + method.getName());
            if (classInfo.isModifiedMethod(method)) {
                method.setMethodReplace(new MethodFixAnnotaion(method.getDefiningClass(), method.getName()));
            } else if (!classInfo.isAddedMethod(method)) {
                continue;
            }

            System.out.println("writeDirectMethods method=" + method.getName() + " method=" + method);

            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# direct methods");
                wroteHeader = true;
            }

            writer.write(10);
            String methodString = MethodUtils.getMethodDescriptor(method);
            IndentingWriter methodWriter = writer;
            if (!writtenMethods.add(methodString)) {
                writer.write("# duplicate method ignored\n");
                methodWriter = new CommentingIndentingWriter(writer);
            }

            MethodImplementation methodImpl = method.getImplementation();
            if (methodImpl == null) {
                MethodDefinition.writeEmptyMethodTo(methodWriter, method, this.options);
            } else {
                MethodDefinition methodDefinition = createMethodDefinition(this, method, methodImpl);
                methodDefinition.writeTo(methodWriter);
            }
        }

        return writtenMethods;
    }

    private void writeVirtualMethods(IndentingWriter writer, Set<String> directMethods) throws IOException {
        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet<>();

        DiffClassInfo classInfo = this.classDef.getClassInfo();
        for (DexBackedMethod method : this.classDef.getVirtualMethods()) {
            System.out.println("writeVirtualMethods method=" + method.getName() + " virtualMethod=" + method);

            if (classInfo.isModifiedMethod(method)) {
                method.setMethodReplace(new MethodFixAnnotaion(method.getDefiningClass(), method.getName()));
            } else if (!classInfo.isAddedMethod(method)) {
                continue;
            }

            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# virtual methods");
                wroteHeader = true;
            }

            writer.write(10);
            String methodString = MethodUtils.getMethodDescriptor(method);
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
                MethodDefinition methodDefinition = createMethodDefinition(this, method, methodImpl);
                methodDefinition.writeTo(methodWriter);
            }
        }
    }

    @Override
    protected MethodDefinition createMethodDefinition(ClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        return new FixMethodDefinition(this, (DexBackedMethod) method, methodImpl);
    }

    public void putNewMethod(String key, String method) {
        injectMethodMap.put(key, method);
    }

    public boolean shouldInjectMethod(String key) {
        return !injectMethodMap.containsKey(key);
    }

    public List<String> getInjectMethodList() {
        return new ArrayList<>(injectMethodMap.values());
    }

}
