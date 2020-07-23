package dome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.zip.Deflater;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {

    public String name();

    public int age() default 19;

    public String gender() default "男";

    public String[] language();

}
