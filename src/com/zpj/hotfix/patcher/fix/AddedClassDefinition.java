package com.zpj.hotfix.patcher.fix;

import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.baksmaliOptions;
import org.jf.dexlib2.iface.ClassDef;

public class AddedClassDefinition extends ClassDefinition {

    public AddedClassDefinition(baksmaliOptions options, ClassDef classDef) {
        super(options, classDef);
    }

}
