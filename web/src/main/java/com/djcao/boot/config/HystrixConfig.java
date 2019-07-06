package com.djcao.boot.config;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-06-30
 */
@Configuration
public class HystrixConfig {

    @Bean
    public HystrixCommandAspect generate(){
        return new HystrixCommandAspect();
    }
}
