package com.kosakorner.kosakore.api.module;

import java.lang.annotation.Annotation;

public class ModuleWrapper {

    private String id;
    private String name;
    private String version;
    private String dependencies;
    private Object instance;

    public ModuleWrapper(Class clazz) {
        try {
            Annotation a = clazz.getAnnotation(Module.class);
            this.id = (String) a.annotationType().getMethod("id").invoke(a);
            this.name = (String) a.annotationType().getMethod("name").invoke(a);
            this.version = (String) a.annotationType().getMethod("version").invoke(a);
            this.dependencies = (String) a.annotationType().getMethod("dependencies").invoke(a);
            this.instance = clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDependencies() {
        return dependencies;
    }

    public Object getInstance() {
        return instance;
    }

}
