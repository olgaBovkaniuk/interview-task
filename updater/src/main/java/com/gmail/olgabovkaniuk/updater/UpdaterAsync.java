package com.gmail.olgabovkaniuk.updater;

import com.gmail.olgabovkaniuk.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.logging.Logger;

@Component
public class UpdaterAsync {

    private static Logger log = Logger.getLogger(UpdaterAsync.class.getName());

    @Autowired
    LogFileHandler logFileHandler;

    @Autowired
    LogFileUpdater logFileUpdater;

    @Autowired
    AppProperties appProperties;

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
            logFileUpdater.saveLogFile(appProperties.getLogFilesDirPath());
            try {
                Thread.sleep(60 * 1000 * 10);
            } catch (InterruptedException e) {}
        }
    }
}
