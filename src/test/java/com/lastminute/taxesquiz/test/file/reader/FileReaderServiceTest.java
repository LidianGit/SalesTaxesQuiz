package com.lastminute.taxesquiz.test.file.reader;

import com.lastminute.taxesquiz.file.reader.config.FileReaderConfig;
import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import com.lastminute.taxesquiz.file.reader.service.exception.FileReaderServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {FileReaderConfig.class} )
public class FileReaderServiceTest {

    @Autowired
    FileReaderService fileReaderService;

    @Test
    public void read(){
        List<String> expectedLines = Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85");
        List<String> lines = null;
        try {
            lines = fileReaderService.lines("src/test/resources/input//basket_1.txt");
        } catch (FileReaderServiceException e) {
            fail();
        }
        assertEquals(expectedLines, lines);
    }

}
