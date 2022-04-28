package com.zpj.hotfix.patcher.annotation;

import org.jf.dexlib2.AnnotationVisibility;
import org.jf.dexlib2.base.BaseAnnotation;
import org.jf.dexlib2.base.BaseAnnotationElement;
import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.immutable.value.ImmutableStringEncodedValue;

import java.util.HashSet;
import java.util.Set;

public class FixClassAnnotation extends BaseAnnotation {

    private static final String ANNOTATION = "Lcom/zpj/hotfix/annotation/FixClass;";
    private final Set<BaseAnnotationElement> mElements = new HashSet<>();

    public FixClassAnnotation(final String clazz, final String method) {
        this.mElements.add(new BaseAnnotationElement() {

            @Override
            public EncodedValue getValue() {
                return new ImmutableStringEncodedValue(clazz.substring(1, clazz.length() - 1).replace('/', '.'));
            }

            @Override
            public String getName() {
                return "name";
            }
        });
    }

    @Override
    public int getVisibility() {
        return AnnotationVisibility.getVisibility("runtime");
    }

    @Override
    public String getType() {
        return ANNOTATION;
    }

    @Override
    public Set<? extends AnnotationElement> getElements() {
        return this.mElements;
    }
}