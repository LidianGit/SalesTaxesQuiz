package com.lastminute.sales.taxes.quiz.file.reader;

import com.lastminute.sales.taxes.quiz.file.reader.config.FileReaderConfig;
import com.lastminute.sales.taxes.quiz.file.reader.service.FileReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

/**
 * Created by dmigliore on 18/09/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {FileReaderConfig.class} )
public class FileReaderServiceTest {

    @Autowired
    FileReaderService fileReaderService;

    @Test
    public void read(){
        String testStr = "fileName=DML_20170918_dmigliore_AMSAZIMUT-18798_afb_HSBC_configurazione_precompila_rimborsi;agencyCode=00028;pdfName=HSBC AFB.pdf;code=template.pdf.sottoscrizione.afb.hsbc";
        Stream<String> lines = fileReaderService.lines(  "src/test/resources/input", "input.txt" );
        lines.forEach( line -> testStr.equals(line));
    }

}
