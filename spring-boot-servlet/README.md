## web
* 基于springBoot 2.0.2.RELEASE版本

* servlet 注册  
    `@WebServlet`  
    `@ServletComponentScan`  
    例如：
    ```
    在springBoot项目中添加web模块依赖
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    定义一个class继承HttpServlet，并声明名字，设置URL映射。
    @WebServlet(name = "myServlet", urlPatterns = "/my/servlet")
    public class MyServlet extends HttpServlet

    在启动类上进行注册，并设置目标servlet所在的package路径。basePackages或者value都可以。
    @SpringBootApplication
    @ServletComponentScan(value = "cn.jiuzhou.web.servlet")
    public class DiveInSpringBoot
    ```

* servlet异步  
    `asyncSupported`  
    `AsyncContext`  
    例如：
    ```
    /**
     * @author wangjiuzhou (835540436@qq.com)
     * @date 2019/05/19
     *
     * 自定义servlet需要继承HttpServlet，并进行注解声明
     * 如果servlet是异步的，那么需要设置异步（asyncSupported）值为true，默认为false
     */
    @WebServlet(name = "myServlet", urlPatterns = "/my/servlet", asyncSupported = true)
    public class MyServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //开启异步
            AsyncContext asyncContext = req.startAsync();
            asyncContext.start(()->{
                try {
                    //执行异步逻辑
                    resp.getWriter().print("hello world!");
                    //声明逻辑结束
                    asyncContext.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    ```

* 替换server  
   1.如何将tomcat替换成jetty？  
   在springBoot中，tomcat的启动优先级要高于jetty所以需要将tomcat exclusion掉，并将jetty加入到依赖中。  
   例如：
   ```
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
     <exclusions>
       <exclusion>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-tomcat</artifactId>
       </exclusion>
     </exclusions>
   </dependency>

   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-jetty</artifactId>
   </dependency>
   ```
   
   2.如何替换成reactive web Server?  
   例如：
   ```
   <!--reactive web Server-->
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-webflux</artifactId>
   </dependency>
   ```
   
