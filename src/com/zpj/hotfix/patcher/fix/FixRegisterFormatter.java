package com.zpj.hotfix.patcher.fix;

import org.jf.baksmali.Adaptors.RegisterFormatter;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.util.IndentingWriter;

import java.io.IOException;

public class FixRegisterFormatter extends RegisterFormatter {

    public final baksmaliOptions options;
    public final int registerCount;
    public final int parameterRegisterCount;

    private boolean addSelfItem;

    public boolean isAddSelfItem() {
        return addSelfItem;
    }

    public FixRegisterFormatter(baksmaliOptions options, int registerCount, int parameterRegisterCount) {
        super(options, registerCount, parameterRegisterCount);
        this.options = options;
        this.registerCount = registerCount;
        this.parameterRegisterCount = parameterRegisterCount;
    }

    public void writeRegisterRange(IndentingWriter writer, int startRegister, int lastRegister) throws IOException {
        // TODO
        if (!this.options.noParameterRegisters) {
            assert startRegister <= lastRegister;

            if (startRegister >= this.registerCount - this.parameterRegisterCount) {

//                if (addSelfItem) {
//                    startRegister += 1;
//                    lastRegister += 1;
//                }
                writer.printSignedIntAsDec(startRegister - (this.registerCount - this.parameterRegisterCount));
                writer.write(" .. p");
                writer.printSignedIntAsDec(lastRegister - (this.registerCount - this.parameterRegisterCount));
                writer.write(125);
                return;
            }
        }

        if (addSelfItem) {
            startRegister += 1;
            lastRegister += 1;
        }

        writer.write("{v");
        writer.printSignedIntAsDec(startRegister);
        writer.write(" .. v");
        writer.printSignedIntAsDec(lastRegister);
        writer.write(125);
    }

    public void writeTo(IndentingWriter writer, int register) throws IOException {
        if (!this.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {

            register = register - (this.registerCount - this.parameterRegisterCount);
            if (addSelfItem && register == 0) {
                writer.write('v');
            } else {
                writer.write('p');
            }
            writer.printSignedIntAsDec(register);
        } else {
            writer.write('v');
            if (addSelfItem) {
                register += 1;
            }
            writer.printSignedIntAsDec(register);
        }
    }

    public void writeTo(Opcode opcode, IndentingWriter writer, int register) throws IOException {
        if (!this.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {

            register = register - (this.registerCount - this.parameterRegisterCount);

            // TODO 排除函数调用
            if (addSelfItem && register == 0) { //  && opcode.format != Format.Format35c
                writer.write('v');
            } else {
                writer.write('p');
            }
            writer.printSignedIntAsDec(register);
        } else {
            writer.write('v');
            if (addSelfItem) {
                register += 1;
            }
            writer.printSignedIntAsDec(register);
        }
    }

    public void writeFirstInvokeTo(IndentingWriter writer, int register) throws IOException {
        if (!this.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {

            register = register - (this.registerCount - this.parameterRegisterCount);

            // TODO 排除函数调用
//            if (addSelfItem && register == 0) {
//                writer.write('v');
//            } else {
//                writer.write('p');
//            }
            writer.write('p');
            writer.printSignedIntAsDec(register);
        } else {
            writer.write('v');
            if (addSelfItem) {
                register += 1;
            }
            writer.printSignedIntAsDec(register);
        }
    }

    public boolean shouldAddSelfItem(int...registers) throws IOException {
        for (int register : registers) {
            RegisterInfo info = getRegisterInfo(register);
            if (info.getRegister() == 0 && info.getRegisterType() == 'p') {
                return true;
            }
        }
        return false;
    }

    public void setAddSelfItem(boolean addSelfItem) {
        this.addSelfItem = addSelfItem;
    }
}
