package com.lastminute.taxesquiz.file.printer.config;

import com.lastminute.taxesquiz.file.writer.config.FileWriterConfig;
import com.lastminute.taxesquiz.language.formatter.config.FormatterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.lastminute.taxesquiz.file.printer")
@Import({FileWriterConfig.class, FormatterConfig.class})
public class PrinterConfig {
}
