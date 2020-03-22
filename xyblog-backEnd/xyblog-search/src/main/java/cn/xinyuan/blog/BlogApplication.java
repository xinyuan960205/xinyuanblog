package cn.xinyuan.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: BlogApplication
 * @Description: 项目启动类
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 10:22
 */
@SpringBootApplication
@MapperScan("cn.xinyuan.blog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
