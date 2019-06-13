package cn.jiuzhou.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/06/12
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class AfterHelloApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("after hello initializer application id:" + applicationContext.getId());
    }
}
