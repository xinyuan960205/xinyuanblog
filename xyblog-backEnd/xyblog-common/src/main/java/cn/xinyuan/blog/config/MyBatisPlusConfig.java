package cn.xinyuan.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyBatisPlusConfig
 * @Description: 声明mybatis-plus的分页插件
 * @Author: xinyuan
 * @CreateDate: 2020/2/9 21:28
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
