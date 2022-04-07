//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zpj.hotfix.patcher.fix;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.annotation.MethodFixAnnotaion;
import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.Adaptors.CommentingIndentingWriter;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.util.IndentingWriter;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (this.classDef instanceof DexBackedClassDef) {
            virtualMethods = ((DexBackedClassDef)this.classDef).getVirtualMethods(false);
        } else {
            virtualMethods = this.classDef.getVirtualMethods();
        }

        for (Object virtualMethod : virtualMethods) {
//            FixMethod method = new FixMethod((Method) virtualMethod);
            Method method = (Method) virtualMethod;
            System.out.println("writeVirtualMethods method=" + method.getName() + " virtualMethod=" + virtualMethod);
            if (this.classDef instanceof DexBackedClassDef
                    && Patcher.isModifiedMethod((DexBackedClassDef) this.classDef, method)) {
                ((DexBackedMethod) method).setMethodReplace(new MethodFixAnnotaion(method.getDefiningClass(), method.getName()));
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
    }
}
