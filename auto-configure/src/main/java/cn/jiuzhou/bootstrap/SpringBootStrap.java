package cn.jiuzhou.bootstrap;

import cn.jiuzhou.repository.MyFirstRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 *
 * 配置类，加载spring上下文
 * @ComponentScan 设置扫描的目标路径
 */
@ComponentScan(value = "cn.jiuzhou.repository")
public class SpringBootStrap {

    public static void main(String[] args) {
        //获取spring上下文
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        //获取指定bean
        MyFirstRepository myFirstRepository = context.getBean("myFirstRepository", MyFirstRepository.class);
        System.out.println("获取myFirstRepository：" + myFirstRepository);
    }
}
