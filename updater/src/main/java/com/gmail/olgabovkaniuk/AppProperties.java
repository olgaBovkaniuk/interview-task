package com.gmail.olgabovkaniuk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app.properties")
public class AppProperties {

    @Value("${log.files.dir.path}")
    private String logFilesDirPath;

    public String getLogFilesDirPath() {
        return logFilesDirPath;
    }
}
