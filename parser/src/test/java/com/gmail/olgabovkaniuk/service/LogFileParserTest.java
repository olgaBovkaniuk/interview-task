package com.gmail.olgabovkaniuk.service;

import com.gmail.olgabovkaniuk.dto.LogRowDataDto;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class LogFileParserTest {

    LogFileParser logFileParser = new LogFileParser();

    private static String FOLDER_PATH;

    static {
        try {
            FOLDER_PATH = Paths.get(LogFileParserTest.class.getResource("").toURI()).toString();
        } catch (URISyntaxException e) {
            // nope;
        }
    }

    @Test
    public void readLogFile() {
        List<LogRowDataDto> logRowDataDtos = logFileParser.readLogFile(FOLDER_PATH + "/server.log.2019-10-27");
        assertNotNull(logRowDataDtos);
        assertEquals(25, logRowDataDtos.size());
    }

    @Test
    public void mapLogRowToDto() {
    }
}