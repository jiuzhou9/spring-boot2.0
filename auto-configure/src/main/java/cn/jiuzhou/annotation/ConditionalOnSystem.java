package cn.jiuzhou.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 条件装配裁决类
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/06/09
 */
class ConditionalOnSystem implements Condition {

    /**
     * 断言条件是否满足
     * @param context
     * @param metadata
     * @return 返回true那么component将会被注册，返回false那么component将不会被注册
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取ConditionalOnSystemProperty注解上的属性集合
        Map<String, Object> conditionOnSystem = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        //获取ConditionalOnSystemProperty注解上的属性
        String name = String.valueOf(conditionOnSystem.get("name"));
        String value = String.valueOf(conditionOnSystem.get("value"));

        String property = System.getProperty(name);
        if (property.equals(value)){
            return true;
        }

        return false;
    }
}
