package cn.jiuzhou.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author wangjiuzhou (jiuzhou@shanshu.ai)
 * @date 2019/06/12
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("hello listener application id:" + event.getApplicationContext().getId());
    }
}
