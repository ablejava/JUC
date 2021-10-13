package com.classload;

import java.io.FileInputStream;

/**
 * @Author: xiazhongwei
 * @Date: 2021/10/13 18:04
 */
public class MyClassLoader extends ClassLoader{

    private String classPath;
    public MyClassLoader() {
    }
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }


    private byte[] loadByte(String name) throws Exception {
        //ll.
        name = name.replace(".", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name+ ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    @Override
    public Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                // 如果不是自己定义的类，使用默认appClassLoader
                if (!name.startsWith("com.classload")) {
                    c = this.getParent().loadClass(name);
                } else {
                    // 使用自定义的classloader
                    c = findClass(name);
                }
//                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
