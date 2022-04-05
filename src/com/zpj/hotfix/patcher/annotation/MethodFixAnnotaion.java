package com.zpj.hotfix.patcher.annotation;

import java.util.HashSet;
import java.util.Set;
import org.jf.dexlib2.AnnotationVisibility;
import org.jf.dexlib2.base.BaseAnnotation;
import org.jf.dexlib2.base.BaseAnnotationElement;
import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.immutable.value.ImmutableStringEncodedValue;

public class MethodFixAnnotaion extends BaseAnnotation {
    private static final String ANNOTATION = "Lcom/zpj/hotfix/annotation/Fix;";
    private Set<BaseAnnotationElement> mElements = new HashSet();

    @Override // org.jf.dexlib2.iface.Annotation
    public int getVisibility() {
        return AnnotationVisibility.getVisibility("runtime");
    }

    @Override // org.jf.dexlib2.iface.BasicAnnotation, org.jf.dexlib2.iface.Annotation
    public String getType() {
        return ANNOTATION;
    }

    public MethodFixAnnotaion(final String clazz, final String method) {
        this.mElements.add(new BaseAnnotationElement() {
            /* class com.euler.patch.annotation.MethodReplaceAnnotaion.AnonymousClass1 */

            @Override // org.jf.dexlib2.iface.AnnotationElement
            public EncodedValue getValue() {
                return new ImmutableStringEncodedValue(clazz.substring(1, clazz.length() - 1).replace('/', '.'));
            }

            @Override // org.jf.dexlib2.iface.AnnotationElement
            public String getName() {
                return "clazz";
            }
        });
        this.mElements.add(new BaseAnnotationElement() {
            /* class com.euler.patch.annotation.MethodReplaceAnnotaion.AnonymousClass2 */

            @Override // org.jf.dexlib2.iface.AnnotationElement
            public EncodedValue getValue() {
                return new ImmutableStringEncodedValue(method);
            }

            @Override // org.jf.dexlib2.iface.AnnotationElement
            public String getName() {
                return "method";
            }
        });
    }

    @Override // org.jf.dexlib2.iface.BasicAnnotation, org.jf.dexlib2.iface.Annotation
    public Set<? extends AnnotationElement> getElements() {
        return this.mElements;
    }
}