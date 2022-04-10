package com.zpj.hotfix.patcher.fix;

import com.zpj.hotfix.patcher.Patcher;
import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import org.jf.baksmali.Adaptors.*;
import org.jf.baksmali.Adaptors.Format.InstructionMethodItem;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.util.TypeUtils;
import org.jf.util.IndentingWriter;

import java.io.IOException;
import java.util.List;

public class AddedMethodDefinition extends MethodDefinition {

    private final DexBackedMethod method;

    public AddedMethodDefinition(ClassDefinition classDef, DexBackedMethod method, MethodImplementation methodImpl) {
        super(classDef, method, methodImpl);
        this.method = method;
    }

    @Override
    public void writeTo(IndentingWriter writer) throws IOException {
        int parameterRegisterCount = 0;
        if (!AccessFlags.STATIC.isSet(this.method.getAccessFlags())) {
            ++parameterRegisterCount;
        }

        writer.write(".method ");
        writeAccessFlags(writer, this.method.getAccessFlags());
        writer.write(this.method.getName());
        writer.write("(");

        for (MethodParameter parameter : this.methodParameters) {
            String type = parameter.getType();
            writer.write(type);
            ++parameterRegisterCount;
            if (TypeUtils.isWideType(type)) {
                ++parameterRegisterCount;
            }
        }

        writer.write(")");
        writer.write(this.method.getReturnType());
        writer.write(10);
        writer.indent(4);
        if (this.classDef.options.useLocalsDirective) {
            writer.write(".locals ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount() - parameterRegisterCount);
        } else {
            writer.write(".registers ");
            writer.printSignedIntAsDec(this.methodImpl.getRegisterCount());
        }

        writer.write(10);
        writeParameters(writer, this.method, this.methodParameters, this.classDef.options);
        if (this.registerFormatter == null) {
            this.registerFormatter = new RegisterFormatter(this.classDef.options, this.methodImpl.getRegisterCount(), parameterRegisterCount);
        }

        String containingClass = null;
        if (this.classDef.options.useImplicitReferences) {
            containingClass = this.method.getDefiningClass();
        }

        AnnotationFormatter.writeTo(writer, this.method.getAnnotations(), containingClass);
        writer.write(10);
        List<MethodItem> methodItems = this.getMethodItems();

        for (MethodItem methodItem : methodItems) {
            if (canWrite(writer, methodItem) && methodItem.writeTo(writer)) {
                writer.write(10);
            }
        }

        writer.deindent(4);
        writer.write(".end method\n");
    }

    private boolean canWrite(IndentingWriter writer, MethodItem methodItem) {
//        Instruction instruction = ((InstructionMethodItem<?>) methodItem).getInstruction();
//        Opcode opcode = instruction.getOpcode();
//        boolean isStatic = opcode == Opcode.INVOKE_STATIC || opcode == Opcode.INVOKE_STATIC_RANGE;
//        if (!isStatic) {
//
//        }
//        if (opcode.format == Format.Format35c) {
//            // invoke
//            DiffClassInfo classInfo = Patcher.getClassInfo(methodItem.);
//        } else if (opcode.format == Format.Format3rc) {
//            // invoke range
//        }
        // TODO 新增方法的调用需要添加如下指令：
        /**
         * invoke-virtual {p0}, Lcom/zpj/hotfix/demo/NewClass;->getBugClass()Lcom/zpj/hotfix/demo/BugClass;
         * move-result-object v0
         */
        return true;
    }



}
