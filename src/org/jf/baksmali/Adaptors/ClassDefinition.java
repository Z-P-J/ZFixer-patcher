//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.baksmali.Adaptors;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.annotation.FixMethodAnnotation;
import com.zpj.hotfix.patcher.utils.MethodUtils;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedDexFile.InvalidItemIndex;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.*;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.formats.Instruction21c;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.util.IndentingWriter;
import org.jf.util.StringUtils;

import java.io.IOException;
import java.util.*;

public class ClassDefinition {
    public final baksmaliOptions options;
    public final ClassDef classDef;
    private final HashSet<String> fieldsSetInStaticConstructor;

    public ClassDefinition(baksmaliOptions options, ClassDef classDef) {
        this.options = options;
        this.classDef = classDef;
        this.fieldsSetInStaticConstructor = this.findFieldsSetInStaticConstructor();
    }

    private HashSet<String> findFieldsSetInStaticConstructor() {
        HashSet<String> fieldsSetInStaticConstructor = new HashSet();
        Iterator var2 = this.classDef.getDirectMethods().iterator();

        while(true) {
            MethodImplementation impl;
            do {
                Method method;
                do {
                    if (!var2.hasNext()) {
                        return fieldsSetInStaticConstructor;
                    }

                    method = (Method)var2.next();
                } while(!method.getName().equals("<clinit>"));

                impl = method.getImplementation();
            } while(impl == null);

            Iterator var5 = impl.getInstructions().iterator();

            while(var5.hasNext()) {
                Instruction instruction = (Instruction)var5.next();
                switch(instruction.getOpcode()) {
                    case SPUT:
                    case SPUT_BOOLEAN:
                    case SPUT_BYTE:
                    case SPUT_CHAR:
                    case SPUT_OBJECT:
                    case SPUT_SHORT:
                    case SPUT_WIDE:
                        Instruction21c ins = (Instruction21c)instruction;
                        FieldReference fieldRef = null;

                        try {
                            fieldRef = (FieldReference)ins.getReference();
                        } catch (InvalidItemIndex var10) {
                        }

                        if (fieldRef != null && fieldRef.getDefiningClass().equals(this.classDef.getType())) {
                            fieldsSetInStaticConstructor.add(ReferenceUtil.getShortFieldDescriptor(fieldRef));
                        }
                }
            }
        }
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
        writer.write(this.classDef.getType());
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
            StringUtils.writeEscapedString(writer, sourceFile);
            writer.write("\"\n");
        }

    }

    private void writeInterfaces(IndentingWriter writer) throws IOException {
        List<String> interfaces = this.classDef.getInterfaces();
        if (interfaces.size() != 0) {
            writer.write(10);
            writer.write("# interfaces\n");
            Iterator var3 = interfaces.iterator();

            while(var3.hasNext()) {
                String interfaceName = (String)var3.next();
                writer.write(".implements ");
                writer.write(interfaceName);
                writer.write(10);
            }
        }

    }

    private void writeAnnotations(IndentingWriter writer) throws IOException {
        Collection<? extends Annotation> classAnnotations = this.classDef.getAnnotations();
        if (classAnnotations.size() != 0) {
            writer.write("\n\n");
            writer.write("# annotations\n");
            String containingClass = null;
            if (this.options.useImplicitReferences) {
                containingClass = this.classDef.getType();
            }

            AnnotationFormatter.writeTo(writer, classAnnotations, containingClass);
        }

    }

    private Set<String> writeStaticFields(IndentingWriter writer) throws IOException {
        boolean wroteHeader = false;
        Set<String> writtenFields = new HashSet();
        Iterable staticFields;
        if (this.classDef instanceof DexBackedClassDef) {
            staticFields = ((DexBackedClassDef)this.classDef).getStaticFields(false);
        } else {
            staticFields = this.classDef.getStaticFields();
        }

        Field field;
        boolean setInStaticConstructor;
        Object fieldWriter;
        for(Iterator var5 = staticFields.iterator(); var5.hasNext(); FieldDefinition.writeTo(this.options, (IndentingWriter)fieldWriter, field, setInStaticConstructor)) {
            field = (Field)var5.next();
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# static fields");
                wroteHeader = true;
            }

            writer.write(10);
            fieldWriter = writer;
            String fieldString = ReferenceUtil.getShortFieldDescriptor(field);
            if (!writtenFields.add(fieldString)) {
                writer.write("# duplicate field ignored\n");
                fieldWriter = new CommentingIndentingWriter(writer);
                System.err.println(String.format("Ignoring duplicate field: %s->%s", this.classDef.getType(), fieldString));
                setInStaticConstructor = false;
            } else {
                setInStaticConstructor = this.fieldsSetInStaticConstructor.contains(fieldString);
            }
        }

        return writtenFields;
    }

    private void writeInstanceFields(IndentingWriter writer, Set<String> staticFields) throws IOException {
        boolean wroteHeader = false;
        Set<String> writtenFields = new HashSet();
        Iterable instanceFields;
        if (this.classDef instanceof DexBackedClassDef) {
            instanceFields = ((DexBackedClassDef)this.classDef).getInstanceFields(false);
        } else {
            instanceFields = this.classDef.getInstanceFields();
        }

        Field field;
        Object fieldWriter;
        for(Iterator var6 = instanceFields.iterator(); var6.hasNext(); FieldDefinition.writeTo(this.options, (IndentingWriter)fieldWriter, field, false)) {
            field = (Field)var6.next();
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# instance fields");
                wroteHeader = true;
            }

            writer.write(10);
            fieldWriter = writer;
            String fieldString = ReferenceUtil.getShortFieldDescriptor(field);
            if (!writtenFields.add(fieldString)) {
                writer.write("# duplicate field ignored\n");
                fieldWriter = new CommentingIndentingWriter(writer);
                System.err.println(String.format("Ignoring duplicate field: %s->%s", this.classDef.getType(), fieldString));
            } else if (staticFields.contains(fieldString)) {
                System.err.println(String.format("Duplicate static+instance field found: %s->%s", this.classDef.getType(), fieldString));
                System.err.println("You will need to rename one of these fields, including all references.");
                writer.write("# There is both a static and instance field with this signature.\n# You will need to rename one of these fields, including all references.\n");
            }
        }

    }

    private Set<String> writeDirectMethods(IndentingWriter writer) throws IOException {
        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet();
        Iterable directMethods;
        if (this.classDef instanceof DexBackedClassDef) {
            directMethods = ((DexBackedClassDef)this.classDef).getDirectMethods(false);
        } else {
            directMethods = this.classDef.getDirectMethods();
        }

        for (Object directMethod : directMethods) {
            Method method = (Method) directMethod;
//            System.out.println("writeDirectMethods method=" + method.getName());
            if (this.classDef instanceof DexBackedClassDef
                    && Patcher.isModifiedMethod((DexBackedClassDef) this.classDef, method)) {
                ((DexBackedMethod) method).setMethodReplace(new FixMethodAnnotation(method.getDefiningClass(), method.getName()));
            }
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

    protected MethodDefinition createMethodDefinition(ClassDefinition classDef, Method method, MethodImplementation methodImpl) {
        return new MethodDefinition(this, method, methodImpl);
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
            Method method = (Method) virtualMethod;
            if (this.classDef instanceof DexBackedClassDef
                    && Patcher.isModifiedMethod((DexBackedClassDef) this.classDef, method)) {
                ((DexBackedMethod) method).setMethodReplace(new FixMethodAnnotation(method.getDefiningClass(), method.getName()));
            }
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# virtual methods");
                wroteHeader = true;
            }

            writer.write(10);
            String methodString = MethodUtils.getMethodDescriptor(method);
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

}
