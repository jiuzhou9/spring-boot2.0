package cn.jiuzhou.annotation;

import cn.jiuzhou.configure.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/05/20
 *
 * 可以通过 @EnableHelloWorld 激活 HelloWorld 模块，
 * 在激活过程中，可以执行 HelloWorldConfiguration 中的一系列 @Bean 等操作。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
