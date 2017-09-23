package com.lastminute.taxesquiz.tasklet;

import com.lastminute.taxesquiz.file.writer.service.FileWriterService;
import com.lastminute.taxesquiz.sale.checkout.model.Receipt;
import com.lastminute.taxesquiz.sale.checkout.service.CheckoutService;
import com.lastminute.taxesquiz.sale.language.formatter.service.FormatterService;
import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import com.lastminute.taxesquiz.sale.order.basket.parser.BasketParser;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class TaxesQuizTasklet implements Tasklet{

    @Autowired
    private BasketParser basketParser;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private FormatterService formatterService;

    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String inputPath = "/src/test/resources/input/input_1/basket.csv";
        String outputFileName = "receipt_1";

        Basket basket = basketParser.parse(inputPath);
        Receipt receipt = checkoutService.issueReceipt(basket);

        String formattedReceipt = formatterService.format(receipt);
        StringWriter sw = new StringWriter();
        sw.write(formattedReceipt);

        fileWriterService.write("./target", outputFileName,sw);
        return RepeatStatus.FINISHED;
    }

}
