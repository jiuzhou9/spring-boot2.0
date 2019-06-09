package cn.jiuzhou.bootstrap;

import cn.jiuzhou.annotation.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 自定义条件装配启动类
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/06/09
 */
public class ConditionalBootStrap {

    @Bean
    @ConditionalOnSystemProperty(name = "user.name", value = "wangjiuzhou")
    public String hello(){
        System.out.println("hello 被注入了");
        return "hello";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String hello = context.getBean("hello", String.class);
        System.out.println(hello);
        context.close();
    }
}
