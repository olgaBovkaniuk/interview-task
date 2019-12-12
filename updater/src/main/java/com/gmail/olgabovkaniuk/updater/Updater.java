package com.gmail.olgabovkaniuk.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class Updater {

    private static Logger log = Logger.getLogger(Updater.class.getName());

    private static volatile boolean isStarted = false;

    @Autowired
    private UpdaterAsync updaterAsync;

    @PostConstruct
    public void updateLastProcessedLogFileId() throws IOException, InterruptedException {
        if (isStarted) {
            log.info("updateLastProcessedLogFileId(): Updater already started. Return...");
            return;
        }
        isStarted = true;

        updaterAsync.async();
        log.info("updateLastProcessedLogFileId(): Updater started!");
    }
}