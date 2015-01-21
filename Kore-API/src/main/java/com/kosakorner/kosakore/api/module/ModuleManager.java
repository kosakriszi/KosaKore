package com.kosakorner.kosakore.api.module;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class ModuleManager {

    private Map<String, Module>   moduleInfos     = new HashMap<String, Module>();
    private Map<String, Object>   moduleInstances = new HashMap<String, Object>();
    private Map<String, Class<?>> moduleClasses   = new HashMap<String, Class<?>>();
    private List<Class<?>>        enableOrder     = new ArrayList<Class<?>>();
    private Reflections loader;

    public ModuleManager(File moduleDir, ClassLoader loader) {
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
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.addUrls(urls);
        config.addClassLoaders(URLClassLoader.newInstance(urls), loader);
        this.loader = config.build();
    }

    public void loadModules() {
        try {
            Set<Class<?>> modules = loader.getTypesAnnotatedWith(Module.class);

            for (Class<?> module : modules) {
                Object instance = module.newInstance();
                Module annotation = module.getAnnotation(Module.class);
                System.out.println(annotation.id());
                if (!moduleClasses.containsKey(annotation.id())) {
                    moduleInfos.put(annotation.id(), annotation);
                    moduleClasses.put(annotation.id(), module);
                    moduleInstances.put(annotation.id(), instance);

                    if (annotation.dependencies().equals("")) {
                        enableOrder.add(module);
                    }
                    else {
                        String[] dependencies = annotation.dependencies().split(";");

                        for (String dependencyChunk : dependencies) {
                            String[] chunks = dependencyChunk.split(":");

                            if (chunks[0].equals("after")) {
                                enableOrder.add(enableOrder.indexOf(moduleClasses.get(chunks[1])), module);
                            }
                            else if (chunks[0].equals("required-after")) {
                                if (moduleClasses.containsKey(chunks[1])) {
                                    enableOrder.add(enableOrder.indexOf(moduleClasses.get(chunks[1])), module);
                                }
                            }
                        }
                    }
                }
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
