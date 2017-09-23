package com.lastminute.taxesquiz.tasklet;

import com.lastminute.taxesquiz.file.reader.config.FileReaderConfig;
import com.lastminute.taxesquiz.file.writer.config.FileWriterConfig;
import com.lastminute.taxesquiz.sale.checkout.config.CheckoutConfig;
import com.lastminute.taxesquiz.sale.language.formatter.config.FormatterConfig;
import com.lastminute.taxesquiz.sale.order.basket.config.BasketConfig;
import com.lastminute.taxesquiz.tasklet.config.TaxesTaskletConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {TaxesTaskletConfig.class, BasketConfig.class, CheckoutConfig.class,
        FileReaderConfig.class, FormatterConfig.class, FileWriterConfig.class, })
@Configuration
public class TaxesQuizTaskletTest {

    @Autowired
    private TaxesQuizTasklet taxesQuizTasklet;

    @Test
    public void execute(){
//        StepContribution stepContribution = mock(StepContribution.class);
//        ChunkContext chunkContext = mock(ChunkContext.class);
//        try {
//            taxesQuizTasklet.execute(stepContribution, chunkContext );
//        } catch (Exception e) {
//            fail();
//        }
    }

}
