package com.lastminute.taxesquiz.test.file.writer.service;

import com.lastminute.taxesquiz.file.writer.config.FileWriterConfig;
import com.lastminute.taxesquiz.file.writer.service.FileWriterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by dmigliore on 18/09/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {FileWriterConfig.class} )
public class FileWriterServiceTest {

    @Autowired
    public FileWriterService fileWriterService;

    @Test
    public void write(){
        StringWriter sw = new StringWriter();
        sw.write("Hello World");
        fileWriterService.write( "./target", "test.txt", sw);
        File file = new File("./target/test.txt");
        assertTrue( file.exists() );
        try {
            BufferedReader fileReader = new BufferedReader( new FileReader(file) );
            assertEquals("Hello World" , fileReader.readLine());
        } catch (IOException e) {
            fail();
        }
    }


}
