package org.simpleframework;

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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放被配置标记的目标对象的Map
     */
    private final Map <Class <?>, Object> beanMap = new ConcurrentHashMap <>();

    /**
     * 加载bean的注解列表
     */
    private static final List <Class <? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Service.class, Controller.class, Repository.class);

    /**
     * 获取Bean容器实例
     *
     * @return BeanContainer
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER,
        ;
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

    /**
     * 获取所有加注解的类的实例
     * @param packageName
     */
    public synchronized void loadBeans(String packageName) {
        // 判断bean容器是否被加载过
        if (isLoaded()) {
            log.warn("BeanContainer  has been loaded");
            return;
        }

        Set <Class <?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("packageName=null");
            return;
        }

        for (Class <?> clazz : classSet) {
            for (Class <? extends Annotation> annotation : BEAN_ANNOTATION) {
                // 判断类上是否标记了自定义注解
                if (clazz.isAnnotationPresent(annotation)) {
                    // 将目标类本身作为键,目标类的实例作为值,放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstatnce(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 功能描述: <br>
     * 〈添加一个class对象及其Bean实例〉
     *
     * @param clazz Class对象
     * @param bean  Bean实例
     * @return: 原有的Bean实例, 没有就返回null
     * @since: 1.0.0
     * @Author:lhn
     * @Date: 2020/7/24 13:13
     */
    public Object addBean(Class <?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     * @return 删除的Bean实例，没有则返回null
     */
    public Object removeBean(Class <?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class <?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set <Class <?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean集合
     *
     * @return Bean集合
     * @param clazz
     */
    public Set <Object> getBeans(Class<?> clazz) {
        return new HashSet <>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class集合
     */
    public Set <Class <?>> getClassesByAnnotation(Class <? extends Annotation> annotation) {
        //1.获取beanMap的所有class对象
        Set <Class <?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("notthing in beanMap");
            return null;
        }
        //2.通过注解筛选被注解标记的class对象，并添加到classSet里
        Set <Class <?>> classSet = new HashSet <>();
        for (Class <?> clazz : keySet) {
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
    *通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
    *@param interfaceOrClass 接口Class或者父类Class
    *@return Class集合
    */
    public Set <Class <?>> getClassesBySuper(Class <?> interfaceOrClass) {
        //1.获取beanMap的所有class对象
        Set <Class <?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("notthing in beanMap");
            return null;
        }
        //2.判断keySet里的元素是否是传入的接口或者类的子类，如果是，就将其添加到classSet里
        Set <Class <?>> classSet = new HashSet <>();
        for (Class <?> clazz : keySet) {
            // 判断keySet里的元素是否是传入的接口或者类的子类
            if (interfaceOrClass.isAssignableFrom(clazz) && clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }
}
