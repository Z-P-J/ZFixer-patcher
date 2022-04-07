//package com.zpj.hotfix.patcher;
//
//import com.zpj.hotfix.patcher.diff.DexDiffer;
//import com.zpj.hotfix.patcher.diff.DiffInfo;
//import com.zpj.hotfix.patcher.fix.FixClassDefinition;
//import org.antlr.runtime.RecognitionException;
//import org.apache.commons.io.FileUtils;
//import org.jf.baksmali.Adaptors.ClassDefinition;
//import org.jf.baksmali.baksmaliOptions;
//import org.jf.dexlib2.Opcodes;
//import org.jf.dexlib2.dexbacked.DexBackedClassDef;
//import org.jf.dexlib2.iface.ClassDef;
//import org.jf.dexlib2.util.SyntheticAccessorResolver;
//import org.jf.util.IndentingWriter;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.HashSet;
//import java.util.Set;
//
//public class ApkPatch2 {
//    private Set<String> classes;
//    private final File from;
//    private final File to;
//
//    protected static final String SUFFIX = ".apatch";
//    private String alias;
//    private String entry;
//    private String keystore;
//    protected String name;
//    protected File out;
//    private String password;
//
//    public ApkPatch2(File from2, File to2, String name2, File out2, String keystore2, String password2, String alias2, String entry2) {
//
//        this.name = name2;
//        this.out = out2;
//        this.keystore = keystore2;
//        this.password = password2;
//        this.alias = alias2;
//        this.entry = entry2;
//        if (!out2.exists()) {
//            out2.mkdirs();
//        } else if (!out2.isDirectory()) {
//            throw new RuntimeException("output path must be directory.");
//        }
//
//        this.from = from2;
//        this.to = to2;
//    }
//
//    public void doPatch() {
//        try {
//            File smaliDir = new File(this.out, "smali");
//            if (!smaliDir.exists()) {
//                smaliDir.mkdir();
//            }
//            FileUtils.cleanDirectory(smaliDir);
//            File dexFile = new File(this.out, "diff.dex");
//            if (!dexFile.exists() || dexFile.delete()) {
//                File outFile = new File(this.out, "diff.apatch");
//                if (!outFile.exists() || outFile.delete()) {
//                    this.classes = buildCode(smaliDir, dexFile, new DexDiffer().diff(this.from, this.to));
////                    build(outFile, dexFile);
////                    release(this.out, dexFile, outFile);
//                    return;
//                }
//                throw new RuntimeException("diff.apatch can't be removed.");
//            }
//            throw new RuntimeException("diff.dex can't be removed.");
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
//    }
//
//    private static Set<String> buildCode(File smaliDir, File dexFile, DiffInfo info) throws IOException, RecognitionException {
//        Set<String> classes2 = new HashSet<>();
//        Set<DexBackedClassDef> list = new HashSet<>();
//        list.addAll(info.getAddedClasses());
//        list.addAll(info.getModifiedClasses());
//        baksmaliOptions options = new baksmaliOptions();
//        options.deodex = false;
//        options.noParameterRegisters = false;
//        options.useLocalsDirective = true;
//        options.useSequentialLabels = true;
//        options.outputDebugInfo = true;
//        options.addCodeOffsets = false;
//        options.jobs = -1;
//        options.noAccessorComments = false;
//        options.registerInfo = 0;
//        options.ignoreErrors = false;
//        options.inlineResolver = null;
//        options.checkPackagePrivateAccess = false;
//        options.syntheticAccessorResolver = new SyntheticAccessorResolver(Opcodes.forApi(19), list);
//
//        System.out.println("buildCode size=" + list.size());
//        for (DexBackedClassDef classDef : list) {
//
//            test(classDef, options);
//        }
//        return classes2;
//    }
//
//    public static String getSmali(ClassDef classDef, baksmaliOptions options) {
//        String code = null;
//        try {
//            StringWriter stringWriter = new StringWriter();
//            IndentingWriter writer = new IndentingWriter(stringWriter);
//            ClassDefinition classDefinition = new ClassDefinition(options, classDef);
//            classDefinition.writeTo(writer);
//            writer.close();
//            code = stringWriter.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return code;
//    }
//
//    public static String getFixSmali(ClassDef classDef, baksmaliOptions options) {
//        String code = null;
//        try {
//            StringWriter stringWriter = new StringWriter();
//            IndentingWriter writer = new IndentingWriter(stringWriter);
//            FixClassDefinition classDefinition = new FixClassDefinition(options, classDef);
//            classDefinition.writeTo(writer);
//            writer.close();
//            code = stringWriter.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return code;
//    }
//
//    private static void test(DexBackedClassDef classDef, baksmaliOptions options) {
//        System.out.println("test className=" + classDef.getType());
//        // TODO
//
//        String smali = getSmali(classDef, options);
//        System.out.println("test smali=\n" + smali);
//
//        String fixSmali = getFixSmali(classDef, options);
//        System.out.println("test fixSmali=\n" + fixSmali);
//    }
//
//}
