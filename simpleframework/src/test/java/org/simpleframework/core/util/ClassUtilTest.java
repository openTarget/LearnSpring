package org.simpleframework.core.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilTest {

    @DisplayName("提取目标方法: extractPackageClassTest")
    @Test
    void extractPackageClass() {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.imooc.entity");
        System.out.println(classSet.size());


    }

    @Test
    void loadClass() {
    }

    @Test
    void getClassLoader() {
    }

    @Test
    void main() {
    }
}