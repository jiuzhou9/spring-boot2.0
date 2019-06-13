## springApplication
如何理解springApplication
* springApplication的Bean的源配置。
* springApplication的web推断。
* springApplication的引导类推断。
* springApplication的上下文初始化加载器。
* springApplication的事件监听器加载。

### springApplication的Bean的源配置
这个源可以是多个，可以是xml、Java。
如下：
pom依赖
```aidl
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.2.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
  </dependencies>
```
springApplication源：
```aidl
public class SpringApplicationBootStrap
{
    public static void main( String[] args )
    {
//        SpringApplication.run(ApplicationConfiguration.class, args);

        Set<String> sources = new HashSet();
        // 配置Class 名称
        sources.add(ApplicationConfiguration.class.getName());
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(sources);
        springApplication.run(args);
    }

    @SpringBootApplication
    public static class ApplicationConfiguration {

    }
}
```
### springApplication的web推断
springApplication会在初始化之前，会进行web类型的推断。主要针对的是三种
web类型的推断：（可参见WebApplicationType）
> NONE，SERVLET，REACTIVE

### springApplication的web推断
```aidl
private WebApplicationType deduceWebApplicationType() {
		if (ClassUtils.isPresent(REACTIVE_WEB_ENVIRONMENT_CLASS, null)
				&& !ClassUtils.isPresent(MVC_WEB_ENVIRONMENT_CLASS, null)) {
			return WebApplicationType.REACTIVE;
		}
		for (String className : WEB_ENVIRONMENT_CLASSES) {
			if (!ClassUtils.isPresent(className, null)) {
				return WebApplicationType.NONE;
			}
		}
		return WebApplicationType.SERVLET;
}
```

### springApplication的引导类推断
```aidl
private Class<?> deduceMainApplicationClass() {
		try {
			StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				if ("main".equals(stackTraceElement.getMethodName())) {
					return Class.forName(stackTraceElement.getClassName());
				}
			}
		}
		catch (ClassNotFoundException ex) {
			// Swallow and continue
		}
		return null;
}
```
### springApplication的上下文初始化加载器
springApplication的上下文初始化加载过程主要是使用工厂加载方式完成的，
在springboot的spring.factories中可以找到初始化信息。我们可以模仿这种方式
进行上下文初始化。如下配置：
```aidl
# Application Context Initializers
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
org.springframework.boot.context.ContextIdApplicationContextInitializer,\
org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer
```
下面进行自定义魔方实现：
创建HelloApplicationContextInitializer，优先级最高。
```aidl
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C> {

    @Override
    public void initialize(C applicationContext) {
        System.out.println("applicationContext.id:" + applicationContext.getId());
    }
}

```
再创建一个优先级最低的初始化加载器AfterHelloApplicationContextInitializer
```aidl
@Order(Ordered.LOWEST_PRECEDENCE)
public class AfterHelloApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("applicationContext.getId:" + applicationContext.getId());
    }
}
```
在自定一个spring.factories
```aidl
# Application Context Initializers
org.springframework.context.ApplicationContextInitializer=\
cn.jiuzhou.context.HelloApplicationContextInitializer,\
cn.jiuzhou.context.AfterHelloApplicationContextInitializer
```
启动后可以根据，打印信息的先后顺序判断出，哪个Initializer先后加载。

### springApplication的事件监听器加载
springApplication进行初始化后，会随即加载事件监听器，对于事件监听器的定义，
也是有先后顺序的，由ordered进行控制。
springboot包中的spring.factories中配置如下：
```aidl
# Application Listeners
org.springframework.context.ApplicationListener=\
cn.jiuzhou.listener.HelloApplicationListener,\
cn.jiuzhou.listener.AfterHelloApplicationListener
```
接下来我们自定义两个监听器示例：（基于时间刷新接口定义的）
```aidl
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("hello listener application id:" + event.getApplicationContext().getId());
    }
}
```
实例二：
```aidl
@Order(Ordered.LOWEST_PRECEDENCE)
public class AfterHelloApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("after hello listener application id:" + event.getApplicationContext().getId());
    }
}
```
自定义spring.factories监听器配置：
```aidl
# Application Listeners
org.springframework.context.ApplicationListener=\
cn.jiuzhou.listener.HelloApplicationListener,\
cn.jiuzhou.listener.AfterHelloApplicationListener
```