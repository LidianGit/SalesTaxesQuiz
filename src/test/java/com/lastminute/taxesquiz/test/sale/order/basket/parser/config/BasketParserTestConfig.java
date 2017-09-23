package com.lastminute.taxesquiz.test.sale.order.basket.parser.config;

import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class BasketParserTestConfig {

    @Bean
    public FileReaderService fileReaderService(){
        return mock(FileReaderService.class);
    }

}
