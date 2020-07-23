package dome.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationParser {

    // 解析类的注解
    public static void parseType1() throws ClassNotFoundException {

        Class<?> aClass = Class.forName("dome.annotation.ImoocCourse");
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
            System.out.println(courseInfoAnnotation.courseName());
        }
    }

    // 解析成员变量上的标签
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("dome.annotation.ImoocCourse");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(PersonInfoAnnotation.class)) {
                System.out.println(declaredField.getAnnotation(PersonInfoAnnotation.class).name());
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //AnnotationParser.parseType1();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        parseFieldAnnotation();
    }

}
