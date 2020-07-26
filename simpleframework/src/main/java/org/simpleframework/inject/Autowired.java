package org.simpleframework.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 只能添加到成员变量上
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
