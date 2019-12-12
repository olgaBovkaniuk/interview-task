package com.gmail.olgabovkaniuk.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.logging.Logger;

@Component
public class UpdaterAsync {

    public static final String  DIRECTORY_PATH = "/Users/mr_nikolasmirnov/projects/logmanager/files";
    private static Logger log = Logger.getLogger(UpdaterAsync.class.getName());

    @Autowired
    LogFileHandler logFileHandler;

    @Autowired
    LogFileUpdater logFileUpdater;

    private static volatile boolean isTerminated = false;

    @PreDestroy
    public void destroy() {
        log.info("Updater stopped!");
        isTerminated = true;
    }

    @Async
    public void async() {
        log.info("Updater started async!");
        while (!isTerminated) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            //save log files from folder to db
            logFileUpdater.saveLogFile(DIRECTORY_PATH);

//            Long lastProcessedLogFileId = lastProcessedLogFileUpdater.getLastProcessedLogFileId();

//            String logFileName = lastProcessedLogFileUpdater.getLogFileName(lastProcessedLogFileId).getFileName();

//             lastProcessedLogFileUpdater.getLogFileName(lastProcessedLogFileId).getFileId();

//            if (logFileName == null) {
//                continue;
//            }

//            if (logFileHandler.handleFile(logFileName)) {
//                lastProcessedLogFileUpdater.updateLastProcessedLogFile(lastProcessedLogFileId);
//            }
        }
    }
}
