package com.eale.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@SpringBootApplication
public class ConcurrencyDemoApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyDemoApplication.class,args);
    }

    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor());
    }
}
