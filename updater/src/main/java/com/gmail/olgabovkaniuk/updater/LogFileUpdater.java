package com.gmail.olgabovkaniuk.updater;

import com.gmail.olgabovkaniuk.domain.ProcessedFile;
import com.gmail.olgabovkaniuk.repository.ProcessedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LogFileUpdater {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ProcessedFileRepository processedFileRepository;

    @Autowired
    LogFileHandler logFileHandler;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveLogFile(String directoryPath) {
        List<String> consideredNewFiles = getListAllFilesFromDirectory(directoryPath);
        List<String> processedFiles = getListAllFilesFromDB();

        Set<String> unavailableFiles = new HashSet<>(processedFiles);

        consideredNewFiles.stream()
                .filter(o -> !unavailableFiles.contains(o))
                .forEach(logFileHandler::handleFile);
    }

    public List<String> getListAllFilesFromDirectory(String directoryPath) {
        List<String> allFilesFromDirectory = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get(directoryPath))) {
            allFilesFromDirectory = pathStream
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allFilesFromDirectory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String> getListAllFilesFromDB() {
        List<ProcessedFile> from_processed_file = entityManager
                .createQuery("from processed_file", ProcessedFile.class)
                .getResultList();

        List<String> listAllFilesNamesFromDB = new ArrayList<>();
        for (ProcessedFile processedFile : from_processed_file) {
            listAllFilesNamesFromDB.add(processedFile.getFileName());
        }
        return listAllFilesNamesFromDB;
    }

}
