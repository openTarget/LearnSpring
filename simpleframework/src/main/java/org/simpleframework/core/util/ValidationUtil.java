package org.simpleframework.core.util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {


    /**
     * Collection 是否为null或size为0
     *
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * String 是否为null或size为0
     *
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
     * Object[] 是否为null或size为0
     *
     * @param obj Object[]
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    /**
     * Map 是否为null或size为0
     *
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }


}
