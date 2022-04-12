package com.zpj.hotfix.patcher;

import com.zpj.hotfix.patcher.diff.DiffClassInfo;
import com.zpj.hotfix.patcher.fix.AddedClassDefinition;
import com.zpj.hotfix.patcher.fix.FixClassDef;
import com.zpj.hotfix.patcher.fix.FixClassDefinition;
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
import org.jf.util.IndentingWriter;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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

    public static void start() {
//        new Patcher(new File("fix.apk"), new File("bug.apk"),
//                "patch", new File("output"))
//                .doPatch();

        new Patcher(new File("new7.apk"), new File("old7.apk"),
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
    }

    public void doPatch() {
        try {
            File smaliDir = new File(this.out, "smali");
            if (!smaliDir.exists()) {
                smaliDir.mkdir();
            }
            FileUtils.cleanDirectory(smaliDir);
            File dexFile = new File(this.out, "diff.dex");
            if (!dexFile.exists() || dexFile.delete()) {
                File outFile = new File(this.out, "diff.apatch");
                if (!outFile.exists() || outFile.delete()) {
//                    buildCode(new DexDiffer().diff(this.from, this.to));

                    diff(this.from, this.to);

                    return;
                }
                throw new RuntimeException("diff.apatch can't be removed.");
            }
            throw new RuntimeException("diff.dex can't be removed.");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void diff(File newFile, File oldFile) throws IOException {


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

    private static Set<String> buildCode() {
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
            testInjectSmali(classDef, options);
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

    public static String getFixSmali(DexBackedClassDef classDef, baksmaliOptions options) {
        String code = null;
        try {
            StringWriter stringWriter = new StringWriter();
            IndentingWriter writer = new IndentingWriter(stringWriter);
            DiffClassInfo classInfo = DIFF_CLASS_INFO_MAP.get(classDef);

            ClassDefinition classDefinition;
            if (classInfo.isModified()) {
                classDefinition = new FixClassDefinition(options, new FixClassDef(classDef, classInfo));
            } else {
                classDefinition = new AddedClassDefinition(options, classDef);
            }

            classDefinition.writeTo(writer);
            writer.close();
            code = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }

    private static void testInjectSmali(DexBackedClassDef classDef, baksmaliOptions options) {
        System.out.println("testInjectSmali className=" + classDef.getType());
        // TODO

//        String smali = getSmali(classDef, options);
//        System.out.println("testInjectSmali smali=\n" + smali);

        String fixSmali = getFixSmali(classDef, options);
        System.out.println("testInjectSmali fixSmali=\n" + fixSmali);
    }

}
