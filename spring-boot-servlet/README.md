## servlet
* servlet 注册  
    `@WebServlet`  
    `@ServletComponentScan`  

    例如：
    ```
    定义一个class继承HttpServlet，并声明名字，设置URL映射。
    @WebServlet(name = "myServlet", urlPatterns = "/my/servlet")
    public class MyServlet extends HttpServlet

    在启动类上进行注册，并设置目标servlet所在的package路径。
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
     * @author wangjiuzhou (jiuzhou@shanshu.ai)
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
