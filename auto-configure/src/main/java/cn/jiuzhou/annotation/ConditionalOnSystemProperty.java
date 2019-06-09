package cn.jiuzhou.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 条件装配注解
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/06/09
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnSystem.class)
public @interface ConditionalOnSystemProperty {

    String value();

    String name();
}
