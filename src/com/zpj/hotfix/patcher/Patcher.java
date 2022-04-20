package com.zpj.hotfix.patcher;

import brut.androlib.mod.SmaliMod;
import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import com.zpj.hotfix.patcher.fix.AddedClassDefinition;
import com.zpj.hotfix.patcher.fix.FixClassDef;
import com.zpj.hotfix.patcher.fix.FixClassDefinition;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.apache.commons.io.FileUtils;
import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.util.SyntheticAccessorResolver;
import org.jf.dexlib2.writer.builder.DexBuilder;
import org.jf.dexlib2.writer.io.FileDataStore;
import org.jf.smali.LexerErrorInterface;
import org.jf.smali.smaliFlexLexer;
import org.jf.smali.smaliParser;
import org.jf.smali.smaliTreeWalker;
import org.jf.util.ClassFileNameHandler;
import org.jf.util.IndentingWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Patcher {

    private static final Map<DexBackedClassDef, DiffClassInfo> DIFF_CLASS_INFO_MAP = new HashMap<>();

    private final File from;
    private final File to;

    protected String name;
    protected File out;

    private final File smaliDir;
    private final File dexFile;

    private final DexBuilder dexBuilder = DexBuilder.makeDexBuilder();

    public static void start() {
//        new Patcher(new File("fix.apk"), new File("bug.apk"),
//                "patch", new File("output"))
//                .doPatch();

//        new Patcher(new File("new7.apk"), new File("old7.apk"),
//                "patch", new File("output"))
//                .doPatch();

//        new Patcher(new File("test_extend_new.apk"), new File("test_extend_old.apk"),
//                "patch", new File("output"))
//                .doPatch();

//        new Patcher(new File("test_inner_new.apk"), new File("test_inner_old.apk"),
//                "patch", new File("output"))
//                .doPatch();

        new Patcher(new File("test_super_new.apk"), new File("test_super_old.apk"),
                "patch", new File("output"))
                .doPatch();
    }

    public static boolean isModifiedClass(DexBackedClassDef classDef) {
        DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);
        if (classInfo != null) {
            return classInfo.isModified();
        }
        return false;
    }

    public static DexBackedClassDef getModifiedClasses(String clazz) {
        for (DexBackedClassDef classDef : DIFF_CLASS_INFO_MAP.keySet()) {
            DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);

            if (classInfo.isModified() && classDef.getType().equals(clazz)) {
                return classDef;
            }
        }
        return null;
    }

    public static DexBackedClassDef getAddedClasses(String clazz) {
        for (DexBackedClassDef classDef : DIFF_CLASS_INFO_MAP.keySet()) {
            DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);
            if (!classInfo.isModified() && classDef.getType().equals(clazz)) {
                return classDef;
            }
        }
        return null;
    }

    public static DiffClassInfo getClassInfo(String clazz) {
        for (DexBackedClassDef classDef : DIFF_CLASS_INFO_MAP.keySet()) {
            DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);
            if (classDef.getType().equals(clazz)) {
                return classInfo;
            }
        }
        return null;
    }

    public static boolean isModifiedMethod(DexBackedClassDef classDef, Method method) {
        DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);
        if (classInfo != null) {
            return classInfo.isModified() && classInfo.getModifiedMethods().contains(method);
        }
        return false;
    }

    private Patcher(File from2, File to2, String name2, File out2) {

        this.name = name2;
        this.out = out2;
        if (!out2.exists()) {
            out2.mkdirs();
        } else if (!out2.isDirectory()) {
            throw new RuntimeException("output path must be directory.");
        }

        this.from = from2;
        this.to = to2;

        smaliDir = new File(this.out, "smali");
        if (!smaliDir.exists()) {
            smaliDir.mkdir();
        }
        dexFile = new File(this.out, "diff.dex");
    }

    public void doPatch() {
        try {

            FileUtils.cleanDirectory(smaliDir);

            if (!dexFile.exists() || dexFile.delete()) {
                diff(this.from, this.to);
                dexBuilder.writeTo(new FileDataStore(dexFile));
            } else {
                throw new RuntimeException("diff.dex can't be removed.");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void diff(File newFile, File oldFile) throws IOException, RecognitionException {


        DexBackedDexFile[] newDexFiles = DexFileFactory.loadDexFiles(newFile, 19, true);
        DexBackedDexFile[] oldDexFiles = DexFileFactory.loadDexFiles(oldFile, 19, true);

        Map<DexBackedClassDef, DexBackedClassDef> oldClassMap = new HashMap<>();

        for (DexBackedDexFile dexFile : oldDexFiles) {
            for (DexBackedClassDef classDef : dexFile.getClasses()) {
                oldClassMap.put(classDef, classDef);
            }
        }

        for (DexBackedDexFile newDexFile : newDexFiles) {
            for (DexBackedClassDef newClazz : newDexFile.getClasses()) {
                DexBackedClassDef oldClazz = oldClassMap.get(newClazz);
                if (oldClazz == null) {
                    DIFF_CLASS_INFO_MAP.put(newClazz, new DiffClassInfo(null, newClazz));
                } else {
                    DiffClassInfo diffClassInfo = new DiffClassInfo(oldClazz, newClazz);
                    if (diffClassInfo.isModified()) {
                        DIFF_CLASS_INFO_MAP.put(newClazz, diffClassInfo);
                    }
                }
            }
        }
        buildCode();



    }

    private Set<String> buildCode() throws RecognitionException {
        Set<String> classes2 = new HashSet<>();
        Set<DexBackedClassDef> list = new HashSet<>(DIFF_CLASS_INFO_MAP.keySet());
        baksmaliOptions options = new baksmaliOptions();
        options.deodex = false;
        options.noParameterRegisters = false;
//        options.useLocalsDirective = true;
        options.useSequentialLabels = true;
        options.outputDebugInfo = true;
        options.addCodeOffsets = false;
        options.jobs = -1;
        options.noAccessorComments = false;
        options.registerInfo = 0;
        options.ignoreErrors = false;
        options.inlineResolver = null;
        options.checkPackagePrivateAccess = false;
        options.syntheticAccessorResolver = new SyntheticAccessorResolver(Opcodes.forApi(19), list);

        System.out.println("buildCode size=" + list.size());
        System.out.println("=========================");
        for (DexBackedClassDef classDef : list) {
            System.out.println("testInjectSmali className=" + classDef.getType());

//        String smali = getSmali(classDef, options);
//        System.out.println("testInjectSmali smali=\n" + smali);

            String fixSmali = getFixSmali(classDef, options);
            System.out.println("testInjectSmali fixSmali=\n" + fixSmali);
        }
        return classes2;
    }

    public static String getSmali(ClassDef classDef, baksmaliOptions options) {
        String code = null;
        try {
            StringWriter stringWriter = new StringWriter();
            IndentingWriter writer = new IndentingWriter(stringWriter);
            ClassDefinition classDefinition = new ClassDefinition(options, classDef);
            classDefinition.writeTo(writer);
            writer.close();
            code = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }

    public String getFixSmali(DexBackedClassDef classDef, baksmaliOptions options) throws RecognitionException {
        DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);
        if (classInfo == null) {
            return null;
        }

        String code = null;
        IndentingWriter writer = null;

        ClassFileNameHandler outFileNameHandler = new ClassFileNameHandler(smaliDir, ".smali");
        File smaliFile = outFileNameHandler.getUniqueFilenameForClass(classInfo.getFixType());
        try {

            ClassDefinition classDefinition;
            if (classInfo.isModified()) {
                classDefinition = new FixClassDefinition(options, new FixClassDef(classDef, classInfo));
            } else {
                classDefinition = new AddedClassDefinition(options, classDef);
            }

//            classDefinition.writeTo(writer);
//            writer.close();
//            code = stringWriter.toString();


            File smaliParent = smaliFile.getParentFile();
            if (!smaliParent.exists() && !smaliParent.mkdirs() && !smaliParent.exists()) {
                System.out.println("Unable to create directory " + smaliParent + " - skipping class");
                return null;
            }

            if (smaliFile.exists() || smaliFile.createNewFile()) {
                System.out.println("disassembleClass smaliFile=" + smaliFile);
                BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(smaliFile), StandardCharsets.UTF_8));

                writer = new IndentingWriter(bufWriter);
                classDefinition.writeTo(writer);

                writer.flush();

                assembleSmaliFile(smaliFile, dexBuilder, true, true);

                return code;
            }

            System.out.println("Unable to create file " + smaliFile + " - skipping class");
        } catch (IOException e) {
            e.printStackTrace();
            smaliFile.delete();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Throwable var19) {
                System.out.println("\n\nError occurred while closing file " + smaliFile);
                var19.printStackTrace();
            }
        }

        return code;
    }

    public static boolean assembleSmaliFile(File smaliFile, DexBuilder dexBuilder, boolean verboseErrors, boolean printTokens) throws IOException, RecognitionException {
        InputStream is = new FileInputStream(smaliFile);
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        smaliFlexLexer lexer = new smaliFlexLexer(reader);
        lexer.setSourceFile(smaliFile);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.mark();
        if (printTokens) {
            tokens.getTokens();

            for(int i = 0; i < tokens.size(); ++i) {
                Token token = tokens.get(i);
                if (token.getChannel() != 99) {
                    System.out.println("printTokens " + smaliParser.tokenNames[token.getType()] + ": " + token.getText());
                }
            }
        }

        System.out.println("printTokens " + tokens.getTokens().size());

        smaliParser parser = new smaliParser(tokens);
        parser.setVerboseErrors(verboseErrors);
        smaliParser.smali_file_return result = parser.smali_file();
        if (parser.getNumberOfSyntaxErrors() <= 0 && lexer.getNumberOfSyntaxErrors() <= 0) {
            CommonTree t = result.getTree();
            CommonTreeNodeStream treeStream = new CommonTreeNodeStream(t);
            treeStream.setTokenStream(tokens);
            smaliTreeWalker dexGen = new smaliTreeWalker(treeStream);
            dexGen.setVerboseErrors(verboseErrors);
            dexGen.setDexBuilder(dexBuilder);
            dexGen.smali_file();
            is.close();
            reader.close();
            return dexGen.getNumberOfSyntaxErrors() == 0;
        } else {
            return false;
        }
    }

}
