package com.zpj.hotfix.patcher.fix;

import org.jf.baksmali.Adaptors.RegisterFormatter;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.Opcode;
import org.jf.util.IndentingWriter;

import java.io.IOException;

public class FixRegisterFormatter extends RegisterFormatter {

    public final baksmaliOptions options;
    public final int registerCount;
    public final int parameterRegisterCount;

    private boolean addSelfItem;

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
            if (register == 0) {
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
            if (addSelfItem && register == 0 && opcode != Opcode.INVOKE_DIRECT && opcode != Opcode.INVOKE_STATIC) {
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

    public static class RegisterInfo {

        private char registerType;

        private int register;

        public RegisterInfo(char registerType, int register) {
            this.registerType = registerType;
            this.register = register;
        }

        public char getRegisterType() {
            return registerType;
        }

        public int getRegister() {
            return register;
        }
    }

    public RegisterInfo getRegisterInfo(int register) throws IOException {
        if (!this.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {
            return new RegisterInfo('p', register - (this.registerCount - this.parameterRegisterCount));
        } else {
            return new RegisterInfo('v', register);
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
