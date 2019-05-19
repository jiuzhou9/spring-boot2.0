package cn.jiuzhou.configure;

import org.springframework.context.annotation.Bean;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/05/20
 *
 * HelloWorld 模块配置
 */
public class HelloWorldConfiguration {

    /**
     * 方法名即 Bean 名称
     * @return
     */
    @Bean
    public String helloWorld() {
        return "Hello,World 2019";
    }
}
