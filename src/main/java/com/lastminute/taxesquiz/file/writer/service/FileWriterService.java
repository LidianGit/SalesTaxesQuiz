package com.lastminute.taxesquiz.file.writer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class FileWriterService {

    private static final Logger logger = LoggerFactory.getLogger( FileWriterService.class );

    public void write( String filePath, String fileName, StringWriter sw ) {
        FileWriter fw = null;
        try {
            fw = new FileWriter( filePath + File.separator + fileName);
            logger.info("writing file [{}] to path [{}]...", fileName, filePath );
            fw.write(sw.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
