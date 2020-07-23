package org.simpleframework;

import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.core.util.ClassUtil;
import org.simpleframework.core.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放被配置标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Service.class, Controller.class, Repository.class);

    /**
     * 获取Bean容器实例
     * @return BeanContainer
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER,;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 容器是否已经加载过bean
     */
    private boolean loaded = false;

    /**
     * 是否已加载过Bean
     *
     * @return 是否已加载
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Bean实例的数量
     *
     * @return
     */
    public int size() {
        return beanMap.size();
    }

    public synchronized void loadBeans(String packageName) {
        // 判断bean容器是否被加载过
        if (isLoaded()) {
            log.warn("BeanContainer  has been loaded");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("packageName=null");
            return;
        }

        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                // 判断类上是否标记了自定义注解
                if (clazz.isAnnotationPresent(annotation)) {
                    // 将目标类本身作为键,目标类的实例作为值,放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstatnce(clazz, true));
                }
            }
        }
        loaded = true;
    }

}
