package com.zpj.hotfix.patcher.annotation;

import java.util.HashSet;
import java.util.Set;
import org.jf.dexlib2.AnnotationVisibility;
import org.jf.dexlib2.base.BaseAnnotation;
import org.jf.dexlib2.base.BaseAnnotationElement;
import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.immutable.value.ImmutableStringEncodedValue;

public class FixMethodAnnotation extends BaseAnnotation {

    private static final String ANNOTATION = "Lcom/zpj/hotfix/annotation/Fix;";
    private final Set<BaseAnnotationElement> mElements = new HashSet<>();

    public FixMethodAnnotation(final String clazz, final String method) {
        this.mElements.add(new BaseAnnotationElement() {

            @Override
            public EncodedValue getValue() {
                return new ImmutableStringEncodedValue(clazz.substring(1, clazz.length() - 1).replace('/', '.'));
            }

            @Override
            public String getName() {
                return "clazz";
            }
        });
        this.mElements.add(new BaseAnnotationElement() {
            @Override
            public EncodedValue getValue() {
                return new ImmutableStringEncodedValue(method);
            }

            @Override
            public String getName() {
                return "method";
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