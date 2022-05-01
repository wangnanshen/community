package com.wang.nowcoderCom.community.config;

import com.wang.nowcoderCom.community.controller.Intercepter.AlphaIntercepter;
import com.wang.nowcoderCom.community.controller.Intercepter.LoginRequiredInterceptor;
import com.wang.nowcoderCom.community.controller.Intercepter.LoginTicketInterceptor;
import com.wang.nowcoderCom.community.controller.Intercepter.MessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AlphaIntercepter alphaIntercepter = new AlphaIntercepter();
    @Autowired
    private LoginTicketInterceptor loginTicketInterception = new LoginTicketInterceptor();
    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor = new LoginRequiredInterceptor();
    @Autowired
    private MessageInterceptor messageInterceptor = new MessageInterceptor();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(alphaIntercepter)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.css")
                .addPathPatterns("/register", "/login");

        registry.addInterceptor(loginTicketInterception)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.css");

        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.css");

        registry.addInterceptor(messageInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.css");
    }
}
