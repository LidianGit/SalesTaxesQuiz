package com.lastminute.sales.taxes.quiz.file.reader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class FileReaderService {

    private static final Logger logger = LoggerFactory.getLogger( FileReaderService.class );

    public Stream<String> lines( String fullPath ) {
        try {
            logger.info( "Reading input lines from file [{}]" , fullPath);
            BufferedReader fileReader = new BufferedReader( new FileReader(fullPath) );
            return fileReader.lines();
        } catch (IOException e) {
            logger.error( "Error reading input lines from file [" + fullPath + "]" , e);
        }
        return null;
    }


    public Stream<String> lines(String filePath, String fileName ) {
        return lines( filePath + File.separator + fileName );
    }

}
