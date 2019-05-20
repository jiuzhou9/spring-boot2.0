package cn.jiuzhou.annotation.selector;

import cn.jiuzhou.configure.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/21
 *
 * 接口编程式驱动装配,并实现接口 ImportSelector
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //定义加载的 configuration
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
