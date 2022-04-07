//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.util;

import java.io.IOException;
import java.io.Writer;

public class IndentingWriter extends Writer {
    protected final Writer writer;
    protected final char[] buffer = new char[24];
    protected int indentLevel = 0;
    private boolean beginningOfLine = true;
    private static final String newLine = System.getProperty("line.separator");

    public IndentingWriter(Writer writer) {
        this.writer = writer;
    }

    protected void writeIndent() throws IOException {
        for(int i = 0; i < this.indentLevel; ++i) {
            this.writer.write(32);
        }

    }

    public void write(int chr) throws IOException {
//        System.out.println("crite: " + (char) (chr));
        if (chr == 10) {
            this.writer.write(newLine);
            this.beginningOfLine = true;
        } else {
            if (this.beginningOfLine) {
                this.writeIndent();
            }

            this.beginningOfLine = false;
            this.writer.write(chr);
        }

    }

    private void writeLine(char[] chars, int start, int len) throws IOException {
        if (this.beginningOfLine && len > 0) {
            this.writeIndent();
            this.beginningOfLine = false;
        }

        this.writer.write(chars, start, len);
    }

    private void writeLine(String str, int start, int len) throws IOException {
        if (this.beginningOfLine && len > 0) {
            this.writeIndent();
            this.beginningOfLine = false;
        }

        this.writer.write(str, start, len);
    }

    public void write(char[] chars) throws IOException {
        this.write((char[])chars, 0, chars.length);
    }

    public void write(char[] chars, int start, int len) throws IOException {
        int end = start + len;
        int pos = start;

        while(pos < end) {
            if (chars[pos] == '\n') {
                this.writeLine(chars, start, pos - start);
                this.writer.write(newLine);
                this.beginningOfLine = true;
                ++pos;
                start = pos;
            } else {
                ++pos;
            }
        }

        this.writeLine(chars, start, pos - start);
    }

    public void write(String s) throws IOException {
//        System.out.println("write: " + s);
        this.write((String)s, 0, s.length());
    }

    public void write(String str, int start, int len) throws IOException {
        int end = start + len;

        for(int pos = start; pos < end; start = pos + 1) {
            pos = str.indexOf(10, start);
            if (pos == -1 || pos >= end) {
                this.writeLine(str, start, end - start);
                return;
            }

            this.writeLine(str, start, pos - start);
            this.writer.write(newLine);
            this.beginningOfLine = true;
        }

    }

    public Writer append(CharSequence charSequence) throws IOException {
        this.write(charSequence.toString());
        return this;
    }

    public Writer append(CharSequence charSequence, int start, int len) throws IOException {
        this.write(charSequence.subSequence(start, len).toString());
        return this;
    }

    public Writer append(char c) throws IOException {
        this.write(c);
        return this;
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }

    public void indent(int indentAmount) {
        this.indentLevel += indentAmount;
        if (this.indentLevel < 0) {
            this.indentLevel = 0;
        }

    }

    public void deindent(int indentAmount) {
        this.indentLevel -= indentAmount;
        if (this.indentLevel < 0) {
            this.indentLevel = 0;
        }

    }

    public void printUnsignedLongAsHex(long value) throws IOException {
        int bufferIndex = 23;

        do {
            int digit = (int)(value & 15L);
            if (digit < 10) {
                this.buffer[bufferIndex--] = (char)(digit + 48);
            } else {
                this.buffer[bufferIndex--] = (char)(digit - 10 + 97);
            }

            value >>>= 4;
        } while(value != 0L);

        ++bufferIndex;
        this.writeLine(this.buffer, bufferIndex, 24 - bufferIndex);
    }

    public void printSignedLongAsDec(long value) throws IOException {
        int bufferIndex = 23;
        if (value < 0L) {
            value *= -1L;
            this.write(45);
        }

        do {
            long digit = value % 10L;
            this.buffer[bufferIndex--] = (char)((int)(digit + 48L));
            value /= 10L;
        } while(value != 0L);

        ++bufferIndex;
        this.writeLine(this.buffer, bufferIndex, 24 - bufferIndex);
    }

    public void printSignedIntAsDec(int value) throws IOException {
//        System.out.println("print: " + value);
        int bufferIndex = 15;
        if (value < 0) {
            value *= -1;
            this.write(45);
        }

        do {
            int digit = value % 10;
            this.buffer[bufferIndex--] = (char)(digit + 48);
            value /= 10;
        } while(value != 0);

        ++bufferIndex;
        this.writeLine(this.buffer, bufferIndex, 16 - bufferIndex);
    }

    public void printUnsignedIntAsDec(int value) throws IOException {
        if (value < 0) {
            this.printSignedLongAsDec((long)value & 4294967295L);
        } else {
            this.printSignedIntAsDec(value);
        }

    }
}
