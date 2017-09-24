package com.lastminute.taxesquiz.file.reader.service;

import com.lastminute.taxesquiz.file.reader.service.exception.FileReaderServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileReaderService {

    private static final Logger logger = LoggerFactory.getLogger( FileReaderService.class );

    public List<String> lines(String fullPath) throws FileReaderServiceException {
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
            throw new FileReaderServiceException("Error reading input lines from file [" + fullPath + "]", e);
        }finally {
            try {
                if (reader!=null) reader.close();
            } catch (Exception e) {
                logger.error( "Error closing file reader", e);
            }
        }
        return lines;
    }

    public List<String> lines(String filePath, String fileName ) throws FileReaderServiceException {
        return lines( filePath + File.separator + fileName );
    }

}
