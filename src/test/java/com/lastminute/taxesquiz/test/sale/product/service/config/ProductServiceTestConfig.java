package com.lastminute.taxesquiz.test.sale.product.service.config;

import com.lastminute.taxesquiz.language.domain.service.DomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan("com.lastminute.taxesquiz.sale.product.service")
public class ProductServiceTestConfig {

    @Bean
    public DomainService domainService(){
        return mock(DomainService.class);
    }

}
