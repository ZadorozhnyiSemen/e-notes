package com.epam.spring.config;

import com.epam.spring.aspects.MethodLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public MethodLogger methodLogger() {
        return new MethodLogger();
    }
}
