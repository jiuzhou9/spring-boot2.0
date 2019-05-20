package cn.jiuzhou.annotation;

import cn.jiuzhou.annotation.selector.HelloWorldImportSelector;
import cn.jiuzhou.configure.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/20
 *
 * 可以通过 @EnableHelloWorld 激活 HelloWorld 模块，
 * 在激活过程中，可以执行 HelloWorldConfiguration 中的一系列 @Bean 等操作。
 *
 * @Import(HelloWorldConfiguration.class) 是注解驱动式装配
 * @Import(HelloWorldImportSelector.class) 是接口（编程）驱动式装配
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
