package com.lastminute.taxesquiz.file.reader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FileReaderService {

    private static final Logger logger = LoggerFactory.getLogger( FileReaderService.class );

    public List<String> lines(String fullPath ) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            logger.info( "Reading input lines from file [{}]" , fullPath);
            reader = new BufferedReader(new FileReader(fullPath));
            String line;
            do{
                line = reader.readLine();
                if( line != null){
                    logger.info( "Read line - [{}]" , line);
                    lines.add(line);
                }
            }while(line!=null);
        } catch (IOException e) {
            logger.error( "Error reading input lines from file [" + fullPath + "]" , e);
        }finally {
            try {
                if (reader!=null) reader.close();
            } catch (Exception e) {
                logger.error( "Error closing file reader", e);
            }
        }
        return lines;
    }


    public List<String> lines(String filePath, String fileName ) {
        return lines( filePath + File.separator + fileName );
    }

}
