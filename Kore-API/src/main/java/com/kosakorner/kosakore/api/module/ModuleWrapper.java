package com.kosakorner.kosakore.api.module;

public class ModuleWrapper {

    private String id;
    private String name;
    private String version;
    private String dependencies;
    private Object instance;

    public ModuleWrapper(Object instance) {
        this.id = ModuleLoader.getClassAnnotationValue(instance.getClass(), Module.class, "id");
        this.name = ModuleLoader.getClassAnnotationValue(instance.getClass(), Module.class, "name");
        this.version = ModuleLoader.getClassAnnotationValue(instance.getClass(), Module.class, "version");
        this.dependencies = ModuleLoader.getClassAnnotationValue(instance.getClass(), Module.class, "dependencies");
        this.instance = instance;
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
