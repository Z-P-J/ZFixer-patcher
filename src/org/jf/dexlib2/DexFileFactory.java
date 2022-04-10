//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jf.dexlib2;

import com.google.common.base.MoreObjects;
import com.google.common.io.ByteStreams;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.DexBackedOdexFile;
import org.jf.dexlib2.dexbacked.OatFile;
import org.jf.dexlib2.dexbacked.DexBackedDexFile.NotADexFile;
import org.jf.dexlib2.dexbacked.DexBackedOdexFile.NotAnOdexFile;
import org.jf.dexlib2.dexbacked.OatFile.NotAnOatFileException;
import org.jf.dexlib2.dexbacked.OatFile.OatDexFile;
import org.jf.util.ExceptionWithContext;

public final class DexFileFactory {

    public static DexBackedDexFile[] loadDexFiles(File apkFile, int api, boolean experimental) throws IOException {
        ZipFile zipFile = null;
        boolean isZipFile = false;

        try {
            zipFile = new ZipFile(apkFile);
            isZipFile = true;



            Enumeration<? extends ZipEntry> enumeration =  zipFile.entries();
            List<ZipEntry> entries = new ArrayList<>();
            while (enumeration.hasMoreElements()) {

                ZipEntry entry = enumeration.nextElement();
                String name = entry.getName();
                if (!name.contains("/") && name.endsWith(".dex")) {
                    entries.add(entry);
                }
            }

            DexBackedDexFile[] dexFiles = new DexBackedDexFile[entries.size()];
            for (int i = 0; i < entries.size(); i++) {
                ZipEntry zipEntry = entries.get(i);
                long fileLength = zipEntry.getSize();
                byte[] dexBytes = new byte[(int)fileLength];
                ByteStreams.readFully(zipFile.getInputStream(zipEntry), dexBytes);
                dexFiles[i] = new DexBackedDexFile(Opcodes.forApi(19), dexBytes);
            }
            return dexFiles;
        } catch (IOException var38) {
            throw var38;
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException var34) {
                }
            }

        }
    }

    public static DexBackedDexFile loadDexFile(File dexFile, int api, boolean experimental) throws IOException {
        return loadDexFile(dexFile, (String)null, Opcodes.forApi(api, experimental));
    }

    public static DexBackedDexFile loadDexFile(File dexFile, String dexEntry, int api, boolean experimental) throws IOException {
        return loadDexFile(dexFile, dexEntry, Opcodes.forApi(api, experimental));
    }

    public static DexBackedDexFile loadDexFile(File dexFile, String dexEntry, Opcodes opcodes) throws IOException {
        ZipFile zipFile = null;
        boolean isZipFile = false;

        try {
            zipFile = new ZipFile(dexFile);
            isZipFile = true;
            String zipEntryName = (String)MoreObjects.firstNonNull(dexEntry, "classes.dex");
            ZipEntry zipEntry = zipFile.getEntry(zipEntryName);
            System.out.println("zipEntryName=" + zipEntryName);
            if (zipEntry == null) {
                throw new DexFileNotFound("zip file %s does not contain a %s file", new Object[]{dexFile.getName(), zipEntryName});
            }

            long fileLength = zipEntry.getSize();
            if (fileLength < 40L) {
                throw new ExceptionWithContext("The %s file in %s is too small to be a valid dex file", new Object[]{zipEntryName, dexFile.getName()});
            }

            if (fileLength > 2147483647L) {
                throw new ExceptionWithContext("The %s file in %s is too large to read in", new Object[]{zipEntryName, dexFile.getName()});
            }

            byte[] dexBytes = new byte[(int)fileLength];
            ByteStreams.readFully(zipFile.getInputStream(zipEntry), dexBytes);
            DexBackedDexFile var10 = new DexBackedDexFile(opcodes, dexBytes);
            return var10;
        } catch (IOException var38) {
            if (isZipFile) {
                throw var38;
            }
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException var34) {
                }
            }

        }

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(dexFile));

        try {
            OatFile oatFile;
            try {
                DexBackedDexFile var44 = DexBackedDexFile.fromInputStream(opcodes, inputStream);
                return var44;
            } catch (NotADexFile var36) {
                try {
                    DexBackedOdexFile var42 = DexBackedOdexFile.fromInputStream(opcodes, inputStream);
                    return var42;
                } catch (NotAnOdexFile var35) {
                    oatFile = null;

                    try {
                        oatFile = OatFile.fromInputStream(inputStream);
                    } catch (NotAnOatFileException var33) {
                    }
                }
            }

            if (oatFile == null) {
                throw new ExceptionWithContext("%s is not an apk, dex, odex or oat file.", new Object[]{dexFile.getPath()});
            } else if (oatFile.isSupportedVersion() == 0) {
                throw new UnsupportedOatVersionException(oatFile);
            } else {
                List<OatDexFile> oatDexFiles = oatFile.getDexFiles();
                if (oatDexFiles.size() == 0) {
                    throw new DexFileNotFound("Oat file %s contains no dex files", new Object[]{dexFile.getName()});
                } else if (dexEntry == null) {
                    if (oatDexFiles.size() > 1) {
                        throw new MultipleDexFilesException(oatFile);
                    } else {
                        DexBackedDexFile var45 = (DexBackedDexFile)oatDexFiles.get(0);
                        return var45;
                    }
                } else {
                    Iterator var8 = oatFile.getDexFiles().iterator();

                    OatDexFile oatDexFile;
                    do {
                        if (!var8.hasNext()) {
                            if (!dexEntry.contains("/")) {
                                var8 = oatFile.getDexFiles().iterator();

                                while(var8.hasNext()) {
                                    oatDexFile = (OatDexFile)var8.next();
                                    File oatEntryFile = new File(oatDexFile.filename);
                                    if (oatEntryFile.getName().equals(dexEntry)) {
                                        OatDexFile var11 = oatDexFile;
                                        return var11;
                                    }
                                }
                            }

                            throw new DexFileNotFound("oat file %s does not contain a dex file named %s", new Object[]{dexFile.getName(), dexEntry});
                        }

                        oatDexFile = (OatDexFile)var8.next();
                    } while(!oatDexFile.filename.equals(dexEntry));

                    OatDexFile var47 = oatDexFile;
                    return var47;
                }
            }
        } finally {
            inputStream.close();
        }
    }

    public static class UnsupportedOatVersionException extends ExceptionWithContext {
        public final OatFile oatFile;

        public UnsupportedOatVersionException(OatFile oatFile) {
            super("Unsupported oat version: %d", new Object[]{oatFile.getOatVersion()});
            this.oatFile = oatFile;
        }
    }

    public static class MultipleDexFilesException extends ExceptionWithContext {
        public final OatFile oatFile;

        public MultipleDexFilesException(OatFile oatFile) {
            super("Oat file has multiple dex files.", new Object[0]);
            this.oatFile = oatFile;
        }
    }

    public static class DexFileNotFound extends ExceptionWithContext {
        public DexFileNotFound(String message, Object... formatArgs) {
            super(message, formatArgs);
        }
    }
}
