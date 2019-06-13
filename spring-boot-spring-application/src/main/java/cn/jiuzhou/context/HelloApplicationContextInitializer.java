package cn.jiuzhou.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/06/12
 * <p>
 * 最高优先级初始化器
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C> {

    @Override
    public void initialize(C applicationContext) {
        System.out.println("hello initializer application id:" + applicationContext.getId());
    }
}
