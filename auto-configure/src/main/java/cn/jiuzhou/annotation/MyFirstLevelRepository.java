package cn.jiuzhou.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 *
 * 自定义注解
 * 具有派生性、层次性
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface MyFirstLevelRepository {
    String value() default "";
}
