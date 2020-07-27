package org.simpleframework.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     *
     * @param packageName 类路径
     * @return 类集合
     * <p>
     * 获取到类的加载器
     * 通过类加载器获取到加载的资源信息
     * 依据不同的资源类型，采用不同的方式获取资源的集合
     * <p>
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
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
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
                //1.从class文件的绝对值路径里提取出包含了package的类名
                //如/Users/baidu/imooc/springframework/sampleframework/
                //需要弄成com.imooc.entity.dto.MainPagelnfoDTd I
                fileAbsolutePath = fileAbsolutePath.replace(File.separator, ".");
                String className = fileAbsolutePath.substring(fileAbsolutePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //2.通过反射机制获取对应的Class对象并加入到classSet里
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });
        if (files != null) {
            for (File file : files) {
                extractClassFile(emptyClassSet, file, packageName);
            }
        }

    }

    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化 class
     * @param clazz Class
     * @param accessible 是否决定使用反射去实例化私有的
     * @param <T> class的类型
     * @return 类的实例
     */
    public static <T> T newInstatnce(Class<?> clazz,boolean accessible) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            // 通过调用无参构造器实例化对象
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstatnce errot:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取classloader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 设置类的属性值
     *
     * @param field      成员变量
     * @param target     类实例
     * @param value      成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int i = 1;
        log.warn("1{}", i);


    }

}
