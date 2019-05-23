package cn.jiuzhou.bootstrap;

import cn.jiuzhou.annotation.profile.Caculate;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 验证profile 在不同配置下加载不同的内容
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/24
 */
@ComponentScan(basePackages = "cn.jiuzhou.annotation.profile")
public class ProfileBootStrap {

    public static void main(String[] args) {
        //获取spring上下文
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProfileBootStrap.class)
                .web(WebApplicationType.NONE)
//                .profiles("dev")
                .profiles("test")
                .run(args);
        Caculate caculate = context.getBean(Caculate.class);
        Integer sum = caculate.sum(1, 2, 3, 4, 5);
        System.out.println(sum);
        context.close();
    }
}
