package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.BeanContainer;
import org.simpleframework.core.util.ClassUtil;
import org.simpleframework.core.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;


@Slf4j
public class DependencyInjector {

    /**
     * Bean 容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc() {
        if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("empty class set in BeanContainer");
            return;
        }
        //1.遍历Bean容器中所有的Class对象
        for (Class<?> clazz : beanContainer.getClasses()) {
            //2.遍历Class对象的所有成员变量
            Field[] finalize = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(finalize)) {
                continue;
            }
            for (Field field : finalize) {
                //3.找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4.获取这些成员变量的类型
                    Class<?> fieldType = field.getType();
                    Object fieldValue = getFieldInstance(fieldType, autowiredValue);
                    //5.获取这些成员变量的类型在容器里对应的实例
                    if (fieldValue == null) {
                        throw new RuntimeException("target fieldClass is:" + fieldType.getClass());
                    } else {
                        //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, fieldValue, true);
                    }
                }

            }

        }
    }

    /**
     * 根据Class在beanContainer里获取其 实例或者实现类
     * @param fieldClass
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass,String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null) {
            return fieldValue;
        }else {
            Class<?> implementClass = getImplementClass(fieldClass, autowiredValue);
            if (implementClass != null) {
                return beanContainer.getBean(implementClass);
            } else {
                return null;
            }
        }

    }

    private Class<?> getImplementClass(Class<?> fieldClass,String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(autowiredValue)) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    throw new RuntimeException("multiple implemented classes for" + fieldClass.getName() + "please");
                }
            } else {
                for (Class<?> clazz : classSet) {
                    if (autowiredValue.equals(clazz.getCanonicalName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
