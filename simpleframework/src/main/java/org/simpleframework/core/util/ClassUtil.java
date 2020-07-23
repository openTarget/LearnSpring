package org.simpleframework.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     * @param packageName 类路径
     * @return 类集合
     *
     * 获取到类的加载器
     * 通过类加载器获取到加载的资源信息
     * 依据不同的资源类型，采用不同的方式获取资源的集合
     *
     * ClassLoader 类加载器
     * 根据一个指定的类的名称，找到或者生成其对应的字节码
     * 加载Java应用所需的资源
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        ClassLoader classLoader = getClassLoader();

        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {
            log.info("unable to retrieve anything from package:{}", packageName);
            return null;
        }

        Set<Class<?>> classSet = null;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        return null;
    }

    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (fileSource.isDirectory()) {
            return;
        }
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String fileAbsolutePath = file.getAbsolutePath();
                    if (fileAbsolutePath.endsWith(".class")) {
                        // 若是class文件,则直接加载
                        addToClassSet(fileAbsolutePath);
                    }
                }
                return false;
            }
            // 根据class文件的绝对值路径,获取并生成class对象,并放入classSet中
            private void addToClassSet(String fileAbsolutePath) {
            }
        });
        if (files != null) {
            for (File file : files) {
                extractClassFile(emptyClassSet, file, packageName);
            }
        }

    }

    /**
     *获取classloader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {

        extractPackageClass("com.imooc.entity");

    }

}
