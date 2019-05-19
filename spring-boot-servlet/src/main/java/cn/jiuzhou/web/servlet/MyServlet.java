package cn.jiuzhou.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
