package com.lastminute.taxesquiz.sale.tasklet;

import com.lastminute.taxesquiz.sale.service.SaleService;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleTasklet implements Tasklet{

    @Autowired
    private SaleService saleService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        String inputPath = jobParameters.getString("inputPath");
        String outputPath = jobParameters.getString("outputPath");
        String outputFileName = "receipt.txt";
        saleService.processSale(inputPath, outputPath, outputFileName);
        return RepeatStatus.FINISHED;
    }

}
