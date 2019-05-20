- [auto-configure](#auto-configure)
  - [元注解（释）](#%E5%85%83%E6%B3%A8%E8%A7%A3%E9%87%8A)
  - [什么是 Stereotype Annotations（模式注解装配）](#%E4%BB%80%E4%B9%88%E6%98%AF-stereotype-annotations%E6%A8%A1%E5%BC%8F%E6%B3%A8%E8%A7%A3%E8%A3%85%E9%85%8D)
  - [如何自定义模式注解](#%E5%A6%82%E4%BD%95%E8%87%AA%E5%AE%9A%E4%B9%89%E6%A8%A1%E5%BC%8F%E6%B3%A8%E8%A7%A3)
    - [@Component的"派生性"](#component%E7%9A%84%22%E6%B4%BE%E7%94%9F%E6%80%A7%22)
    - [@Component的层次性](#component%E7%9A%84%E5%B1%82%E6%AC%A1%E6%80%A7)
  - [什么是@Enable模块装配](#%E4%BB%80%E4%B9%88%E6%98%AFenable%E6%A8%A1%E5%9D%97%E8%A3%85%E9%85%8D)
    - [模块装配示例](#%E6%A8%A1%E5%9D%97%E8%A3%85%E9%85%8D%E7%A4%BA%E4%BE%8B)
    - [如何自定义注解驱动式模块装配](#%E5%A6%82%E4%BD%95%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B3%A8%E8%A7%A3%E9%A9%B1%E5%8A%A8%E5%BC%8F%E6%A8%A1%E5%9D%97%E8%A3%85%E9%85%8D)
    - [如何自定义接口编程式驱动模块装配](#%E5%A6%82%E4%BD%95%E8%87%AA%E5%AE%9A%E4%B9%89%E6%8E%A5%E5%8F%A3%E7%BC%96%E7%A8%8B%E5%BC%8F%E9%A9%B1%E5%8A%A8%E6%A8%A1%E5%9D%97%E8%A3%85%E9%85%8D)

# auto-configure
> 基于springBoot 2.0.2.RELEASE版本,spring boot 的自动装配,主要包括"模式注解装配"、"Enable模块装配"
spring文档：https://docs.spring.io/spring/docs/
    
## 元注解（释） 
https://github.com/spring-projects/spring-framework/wiki/Spring-Annotation-Programming-Model#meta-annotations  

    A meta-annotation is an annotation that is declared on another annotation. 
    An annotation is therefore meta-annotated if it is annotated with another annotation. 
    For example, any annotation that is declared to be documented is meta-annotated with 
    @Documented from the java.lang.annotation package.
    
## 什么是 Stereotype Annotations（模式注解装配）  
https://github.com/spring-projects/spring-framework/wiki/Spring-Annotation-Programming-Model#stereotype-annotations  
  
> A stereotype annotation is an annotation that is used to declare the role that a 
component plays within the application. For example, the `@Repository` annotation in 
the Spring Framework is a marker for any class that fulfills the role or stereotype 
of a repository (also known as Data Access Object or DAO).  
`@Component` is a generic stereotype for any Spring-managed component. Any component 
annotated with `@Component` is a candidate for component scanning. Similarly, any 
component annotated with an annotation that is itself meta-annotated with `@Component` 
is also a candidate for component scanning. For example, `@Service` is meta-annotated 
with `@Component`.  
Core Spring provides several stereotype annotations out of the box, including but 
not limited to: `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`, 
and `@Configuration`. `@Repository`, `@Service`, etc. are specializations of @Component.
    
> 注释：
模式注解是一种用于声明在应用中扮演“组件”角色的注解。如 Spring Framework 中的 `@Repository` 标注在任何类上 ，用
于扮演仓储角色的模式注解。
`@Component` 作为一种由 Spring 容器托管的通用模式组件，任何被 `@Component` 标准的组件均为组件扫描的候选对象。
类似地，凡是被 @Component 元标注(meta-annotated)的注解，如 `@Service` ，当任何组件标注它时，也被视作组件扫 描的候选对象

注解模式就是我们通常用的 `@ComponentScan(basePackages="xxx.xxx.xxx")` 

## 如何自定义模式注解
### @Component的"派生性"
Java中没有"派生性"的概念因此打引号。通过自定义注解 + 手动装配的方式，说明"派生性"。（见下面示例）

```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 *
 * 自定义注解
 * 具有派生性、层次性
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface MyFirstLevelRepository {
    String value() default "";
}
```
使用自定义注解：
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 *
 * 第一个 MyFirstRepository
 */
@MyFirstLevelRepository(value = "myFirstRepository")
public class MyFirstRepository {
}
```
从spring的上下文获取被注解标定的bean：
```
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
```
### @Component的层次性
例如：
定义二级注解，派生于一级注解：
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyFirstLevelRepository
public @interface MySecondLevelRepository {
    String value() default "";
}
```
定义新的Java Bean,并且使用二级注解：
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/19
 *
 * 第二个 mySecondRepository
 */
@MySecondLevelRepository(value = "mySecondRepository")
public class MySecondRepository {
}
```
同样的方式获取bean：
```
MySecondRepository mySecondRepository = context.getBean("mySecondRepository", MySecondRepository.class);
System.out.println("获取 mySecondRepository：" + mySecondRepository);
```
层次性表现：  
    
    @Component
        @Repository
            @MyFirstLevelRepository
                @MySecondLevelRepository
                
## 什么是@Enable模块装配
见下面示例和说明。
### 模块装配示例
> 可以通过此注解激活，一个系列的配置。
例如：
`@EnableAutoConfiguration`、
`@EnableManagementContext`、
`@EnableConfigurationPropertie`等。

### 如何自定义注解驱动式模块装配  
`@EnableWebMvc`就是一个现成的注解驱动式模块装配。

例如：  
定义一个 `@EnableHelloWorld` 相当于激活 HelloWorld 一系列（模块）的入口。
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/20
 *
 * 可以通过 @EnableHelloWorld 激活 HelloWorld 模块，
 * 在激活过程中，可以执行 HelloWorldConfiguration 中的一系列 @Bean 等操作。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
```
定义相应的 `HelloWorldConfiguration` 相当于 HelloWorld 模块的配置。下面的例子中只是注册了一个bean 名字为 `helloWorld`
```
/**
 * @author wangjiuzhou (835540436@qq.com)
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
```
定义一个启动类，加载spring上下文：  
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/20
 * 
 * 启动类，加载spring上下文
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
```
启动可以看见：>>>> helloWorld Bean:Hello,World 2019  

### 如何自定义接口编程式驱动模块装配
`@EnableCaching`就是一个现成的接口编程式驱动模块装配的例子。
> interface ImportSelector
            |
            |实现
            |
       class xxxImportSelector
                |
                |import 使用
                |
            annotation Enablexxx
            
例如：
定义一个 `ImportSelector`
```
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
```
在Enable中使用接口编程式驱动。
```
/**
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/20
 *
 * 可以通过 @EnableHelloWorld 激活 HelloWorld 模块，
 * 在激活过程中，可以执行 HelloWorldConfiguration 中的一系列 @Bean 等操作。
 *
 * @Import(HelloWorldImportSelector.class) 是接口（编程）驱动式装配
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}
```
启动类激活模块装配。
```
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
```
启动输出：helloWorld Bean:Hello,World 2019