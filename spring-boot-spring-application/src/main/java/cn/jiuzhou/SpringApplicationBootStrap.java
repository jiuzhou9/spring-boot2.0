package cn.jiuzhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 * springApplication 配置Bean源，这个源可以是多个，可以是xml的，也可以是Java的
 */
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
