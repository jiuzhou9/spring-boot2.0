package cn.jiuzhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Hello world!
 * @author wangjiuzhou
 */
@SpringBootApplication
@ServletComponentScan(value = "cn.jiuzhou.web.servlet")
public class SpringBootServletApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootServletApp.class, args);
    }
}
