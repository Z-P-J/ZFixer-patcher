package com.zpj.hotfix.patcher;

import com.zpj.hotfix.patcher.Patcher;

public class Main {

    public static void main(String[] args) {
//        apkpatch.bat -f new.apk -t old.apk -o output -k debug.keystore -p android -a androiddebugkey -e android
//        new ApkPatch(new File("new.apk"), new File("old.apk"),
//                "main", new File("output"),
//                "debug.keystore", "android", "androiddebugkey", "android")
//                .doPatch();


//        new ApkPatch2(new File("fix.apk"), new File("bug.apk"),
//                "patch", new File("output"),
//                "debug.keystore", "android", "androiddebugkey", "android")
//                .doPatch();

        Patcher.start();

//        new ApkPatch2(new File("new.apk"), new File("old.apk"),
//                "main", new File("output"),
//                "debug.keystore", "android", "androiddebugkey", "android")
//                .doPatch();
    }

}
