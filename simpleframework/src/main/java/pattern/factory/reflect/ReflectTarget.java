package pattern.factory.reflect;

import java.lang.reflect.Constructor;

public class ReflectTarget {



    public static void main(String[] args) throws ClassNotFoundException {
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<? extends ReflectTarget> aClass1 = reflectTarget.getClass();
        System.out.println(aClass1.getName());
        Class<ReflectTarget> reflectTargetClass = ReflectTarget.class;
        System.out.println(reflectTargetClass.getName());
        if (aClass1 == reflectTargetClass) {
            System.out.println("true");
        }

        Class<?> aClass = Class.forName("pattern.factory.reflect.ReflectTarget");
        System.out.println(aClass.getName());
        System.out.println(reflectTargetClass == aClass);
    }
}
class classDome{
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.lang.Object");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }
}