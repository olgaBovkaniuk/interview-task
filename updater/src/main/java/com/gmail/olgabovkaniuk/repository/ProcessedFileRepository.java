package com.gmail.olgabovkaniuk.repository;

import com.gmail.olgabovkaniuk.domain.ProcessedFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedFileRepository extends CrudRepository<ProcessedFile, Long> {
}
