package com.lastminute.taxesquiz.test.sale.checkout.service.config;

import com.lastminute.taxesquiz.sale.tax.service.TaxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class CheckoutServiceTestConfig {

    @Bean
    public TaxService taxService(){
        return mock(TaxService.class);
    }

}
