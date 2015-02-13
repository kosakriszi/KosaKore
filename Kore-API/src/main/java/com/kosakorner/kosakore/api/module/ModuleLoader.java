package com.kosakorner.kosakore.api.module;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.event.Handler;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleLoader {

    private IKore kore;

    private Map<String, ModuleWrapper> modules = new HashMap<String, ModuleWrapper>();

    public ModuleLoader(IKore kore, File moduleDir) {
        this.kore = kore;
        loadFiles(moduleDir);
    }

    public void loadFiles(File folder) {
        try {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    System.out.println("Loading: " + file.getName());
                    loadJar(file);
                    JarFile jarFile = new JarFile(file);
                    Enumeration e = jarFile.entries();

                    while (e.hasMoreElements()) {
                        JarEntry je = (JarEntry) e.nextElement();
                        if (je.isDirectory() || !je.getName().endsWith(".class")) {
                            continue;
                        }
                        // -6 because of .class
                        String className = je.getName().substring(0, je.getName().length() - 6);
                        className = className.replace('/', '.');
                        Class c = this.getClass().getClassLoader().loadClass(className);
                        scanForAnnotations(c);
                    }
                }
                else {
                    loadFiles(file);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scanForAnnotations(Class clazz) {
        for (Annotation a : clazz.getAnnotations()) {
            if (a instanceof Module) {
                try {
                    ModuleWrapper wrapper = new ModuleWrapper(clazz);
                    modules.put(wrapper.getId(), wrapper);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (Method method : clazz.getMethods()) {
            for (Annotation a : method.getAnnotations()) {
                if (a instanceof Handler) {

                }
            }
        }
    }

    public Object getModule(String id) {
        return modules.get(id).getInstance();
    }

    public List<String> listModules() {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, ModuleWrapper> entry : modules.entrySet()) {
            ModuleWrapper moduleInfo = entry.getValue();
            list.add(moduleInfo.getName());
        }
        return list;
    }

    protected static Method ADD_URL;

    private void loadJar(File targetJar) {
        try {
            if (ADD_URL == null) {
                ADD_URL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                ADD_URL.setAccessible(true);
            }

            ADD_URL.invoke(this.getClass().getClassLoader(), targetJar.toURI().toURL());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
