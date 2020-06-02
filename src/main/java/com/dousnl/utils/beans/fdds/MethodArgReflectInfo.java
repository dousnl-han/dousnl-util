package com.dousnl.utils.beans.fdds;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/27 15:21
 */
public class MethodArgReflectInfo {
    private String name;
    private String type;
    private Map<Class<? extends Annotation>, Annotation> annos;

    public MethodArgReflectInfo(String name, String type, Map<Class<? extends Annotation>, Annotation> annos) {
        this.name = name;
        this.type = type;
        this.annos = annos;
    }

    public <T extends Annotation> T getAnnotation(Class<T> annoClz) {
        return (T) this.annos.get(annoClz);
    }

    public boolean hasAnnotationsAnnotatedBy(Class<? extends Annotation> annoClz) {
        Collection<Annotation> values = this.annos.values();
        Iterator var3 = values.iterator();

        Annotation anno;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            anno = (Annotation)var3.next();
        } while(anno.annotationType().getAnnotation(annoClz) == null);

        return true;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}
