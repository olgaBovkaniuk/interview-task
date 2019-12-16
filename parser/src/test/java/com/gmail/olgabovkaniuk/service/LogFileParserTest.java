package com.gmail.olgabovkaniuk.service;

import com.gmail.olgabovkaniuk.dto.LogRowDataDto;
import com.gmail.olgabovkaniuk.dto.MessageType;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void readLogFileTest() {
        List<LogRowDataDto> logRowDataDtos = logFileParser.readLogFile(FOLDER_PATH + "/server.log.2019-10-27");
        assertNotNull(logRowDataDtos);
        assertEquals(25, logRowDataDtos.size());
    }

    @Test
    public void parseRowTest() throws ParseException {
        // given
        String logRow = "2019-10-27 23:50:08,530 INFO Olga Bovkaniuk some text message : with symbols []";

        LogRowDataDto expected = new LogRowDataDto(
                new Timestamp(1572209408530L),
                MessageType.INFO,
                "Olga",
                "Bovkaniuk",
                "some text message : with symbols []"
                );

        // when
        LogRowDataDto logRowDataDto = logFileParser.parseRow(logRow);

        // then
        assertEquals(expected, logRowDataDto);
    }
}