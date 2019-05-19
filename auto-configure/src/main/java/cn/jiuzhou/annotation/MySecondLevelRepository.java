package cn.jiuzhou.annotation;

import java.lang.annotation.*;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/05/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyFirstLevelRepository
public @interface MySecondLevelRepository {
    String value() default "";
}
