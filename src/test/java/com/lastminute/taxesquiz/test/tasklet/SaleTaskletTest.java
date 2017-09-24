package com.lastminute.taxesquiz.test.tasklet;

import com.lastminute.taxesquiz.file.printer.config.PrinterConfig;
import com.lastminute.taxesquiz.file.reader.config.FileReaderConfig;
import com.lastminute.taxesquiz.language.domain.config.DomainConfig;
import com.lastminute.taxesquiz.sale.config.SaleConfig;
import com.lastminute.taxesquiz.sale.tasklet.SaleTasklet;
import com.lastminute.taxesquiz.test.common.PropertiesTestConfig;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {
        PropertiesTestConfig.class,
        DomainConfig.class,
        SaleConfig.class,
        FileReaderConfig.class,
        PrinterConfig.class,
})
@Configuration
public class SaleTaskletTest {

    @Autowired
    private SaleTasklet saleTasklet;

    @After
    public void cleanOutput(){
        try{
            File file = new File("src/test/resources/output/receipt_1.txt");
            file.delete();
            file = new File("src/test/resources/output/receipt_2.txt");
            file.delete();
            file = new File("src/test/resources/output/receipt_3.txt");
            file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void execute(){
        StepContribution stepContribution = mock(StepContribution.class);
        ChunkContext chunkContext = mock(ChunkContext.class);
        StepContext stepContext = mock(StepContext.class);
        StepExecution stepExecution = mock(StepExecution.class);
        JobParameters jobParameters = mock(JobParameters.class);

        when(jobParameters.getString("inputPath")).thenReturn(
                "src/test/resources/input/"
        );
        when(jobParameters.getString("outputPath")).thenReturn(
                "src/test/resources/output/"
        );
        when(stepExecution.getJobParameters()).thenReturn(
                jobParameters
        );
        when(stepContext.getStepExecution()).thenReturn(
                stepExecution
        );
        when(chunkContext.getStepContext()).thenReturn(
                stepContext
        );
        try {
            saleTasklet.execute(stepContribution, chunkContext);
            assertEquals(FileUtils.readLines(new File("src/test/resources/output/expected_receipt_1.txt"), "UTF-8"),
                         FileUtils.readLines(new File("src/test/resources/output/receipt_1.txt"), "UTF-8"));
            assertEquals(FileUtils.readLines(new File("src/test/resources/output/expected_receipt_2.txt"), "UTF-8"),
                         FileUtils.readLines(new File("src/test/resources/output/receipt_2.txt"), "UTF-8"));
            assertEquals(FileUtils.readLines(new File("src/test/resources/output/expected_receipt_3.txt"), "UTF-8"),
                         FileUtils.readLines(new File("src/test/resources/output/receipt_3.txt"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
