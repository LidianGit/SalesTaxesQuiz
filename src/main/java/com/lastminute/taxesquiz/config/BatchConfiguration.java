package com.lastminute.taxesquiz.config;

import com.lastminute.taxesquiz.file.reader.config.FileReaderConfig;
import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import com.lastminute.taxesquiz.file.writer.service.FileWriterService;
import com.lastminute.taxesquiz.file.writer.config.FileWriterConfig;
import com.lastminute.taxesquiz.sale.tasklet.SaleTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

/**
 * Created by dmigliore on 18/09/2017.
 */
@Configuration
@Import( { FileWriterConfig.class, FileReaderConfig.class } )
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SaleTasklet saleTasklet;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(saleTasklet).build();
    }

    @Bean
    public Job createFileJob() throws Exception {
        return jobBuilderFactory.get("createFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

}
