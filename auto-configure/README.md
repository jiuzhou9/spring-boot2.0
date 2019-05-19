## auto-configure
> 基于springBoot 2.0.2.RELEASE版本  
spring boot 的自动装配


* 元注解（释） 
* Stereotype Annotations（模式注解） 
* @Component  
    * "派生性"    
    * 层次性  
    
### 元注解（释） 
https://github.com/spring-projects/spring-framework/wiki/Spring-Annotation-Programming-Model#meta-annotations  

    A meta-annotation is an annotation that is declared on another annotation. 
    An annotation is therefore meta-annotated if it is annotated with another annotation. 
    For example, any annotation that is declared to be documented is meta-annotated with 
    @Documented from the java.lang.annotation package.
    
### Stereotype Annotations（模式注解）  
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

### @Component的"派生性"
Java中没有"派生性"的概念因此打引号。通过自定义注解 + 手动装配的方式，说明"派生性"。（见下面示例）

### @Component的层次性"
例如：
定义自定义注解，从形式上来看更像是属于 `@Component`派生出来的。
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
```$xslt
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
```$xslt
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