package com.kosakorner.kosakore.api.module;

import com.kosakorner.kosakore.api.util.algorithm.CollectionUtils;
import org.reflections.Reflections;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class ModuleManager {

    private Map<String, Module>   moduleInfos        = new HashMap<String, Module>();
    private Map<String, Object>   moduleInstances    = new HashMap<String, Object>();
    private Map<String, Class<?>> moduleClasses      = new HashMap<String, Class<?>>();
    private List<Class<?>>        instantiationOrder = new ArrayList<Class<?>>();
    private URLClassLoader loader;

    public ModuleManager(ClassLoader loader, File moduleDir) {
        File[] files = moduleDir.listFiles();
        URL[] urls = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            try {
                urls[i] = files[i].toURI().toURL();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.loader = new URLClassLoader(urls, loader);
    }

    public void loadModules() {
        try {
            Reflections reflections = new Reflections(loader);
            Set<Class<?>> modules = reflections.getTypesAnnotatedWith(Module.class);

            for (Class<?> module : modules) {
                Module annotation = module.getAnnotation(Module.class);

                if (!moduleClasses.containsKey(annotation.id())) {
                    moduleInfos.put(annotation.id(), annotation);
                    moduleClasses.put(annotation.id(), module);

                    if (annotation.dependencies().equals("")) {
                        instantiationOrder.add(module);
                    }
                    else {
                        String[] dependencies = annotation.dependencies().split(";");

                        for (String dependencyChunk : dependencies) {
                            String[] chunks = dependencyChunk.split(":");

                            if (chunks[0].equals("after")) {
                                instantiationOrder.add(instantiationOrder.indexOf(moduleClasses.get(chunks[1])), module);
                            }
                            else if (chunks[0].equals("required-after")) {
                                if (moduleClasses.containsKey(chunks[1])) {
                                    instantiationOrder.add(instantiationOrder.indexOf(moduleClasses.get(chunks[1])), module);
                                }
                            }
                        }
                    }
                }
            }

            Map<Class<?>, String> moduleClassesR = CollectionUtils.reverseMap(moduleClasses);
            for (Class<?> module : instantiationOrder) {
                moduleInstances.put(moduleClassesR.get(module), module.newInstance());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getModule(String id) {
        return moduleInstances.get(id);
    }

    public List<String> listModules() {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, Module> entry : moduleInfos.entrySet()) {
            Module moduleInfo = entry.getValue();
            list.add(moduleInfo.name());
        }
        return list;
    }

}
