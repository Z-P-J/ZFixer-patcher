//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.baksmali.Adaptors;

import java.io.IOException;

import com.zpj.hotfix.patcher.fix.FixRegisterFormatter;
import org.jf.baksmali.baksmaliOptions;
import org.jf.util.IndentingWriter;

public class RegisterFormatter {
    public final baksmaliOptions options;
    public final int registerCount;
    public final int parameterRegisterCount;

    public RegisterFormatter(baksmaliOptions options, int registerCount, int parameterRegisterCount) {
        this.options = options;
        this.registerCount = registerCount;
        this.parameterRegisterCount = parameterRegisterCount;
    }

    public int getRegisterCount() {
        return registerCount;
    }

    public int getParameterRegisterCount() {
        return parameterRegisterCount;
    }

    public int getStartParameterRegister() {
        return this.registerCount - this.parameterRegisterCount;
    }

    public void writeRegisterRange(IndentingWriter writer, int startRegister, int lastRegister) throws IOException {
        if (!this.options.noParameterRegisters) {
            assert startRegister <= lastRegister;

            if (startRegister >= this.registerCount - this.parameterRegisterCount) {
                writer.write("{p");
                writer.printSignedIntAsDec(startRegister - (this.registerCount - this.parameterRegisterCount));
                writer.write(" .. p");
                writer.printSignedIntAsDec(lastRegister - (this.registerCount - this.parameterRegisterCount));
                writer.write(125);
                return;
            }
        }

        writer.write("{v");
        writer.printSignedIntAsDec(startRegister);
        writer.write(" .. v");
        writer.printSignedIntAsDec(lastRegister);
        writer.write(125);
    }

    public void writeTo(IndentingWriter writer, int register) throws IOException {
        RegisterInfo info = getRegisterInfo(register);
        writer.write(info.getRegisterType());
        writer.printSignedIntAsDec(info.getRegister());
    }

    public RegisterInfo getRegisterInfo(int register) throws IOException {
        if (!this.options.noParameterRegisters && register >= this.registerCount - this.parameterRegisterCount) {
            return new RegisterInfo('p', register - (this.registerCount - this.parameterRegisterCount));
        } else {
            return new RegisterInfo('v', register);
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
}
