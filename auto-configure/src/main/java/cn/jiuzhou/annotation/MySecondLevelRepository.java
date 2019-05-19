package cn.jiuzhou.annotation;

import java.lang.annotation.*;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyFirstLevelRepository
public @interface MySecondLevelRepository {
    String value() default "";
}
