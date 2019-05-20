package cn.jiuzhou.bootstrap;

import cn.jiuzhou.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/20
 *
 * 启动类，加载spring上下文
 * @EnableHelloWorld 模块装配激活
 */
@EnableHelloWorld
public class EnableHelloWorldBootStrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println("helloWorld Bean:" + helloWorld);
    }
}
