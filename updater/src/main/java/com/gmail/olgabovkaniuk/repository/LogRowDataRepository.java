package com.gmail.olgabovkaniuk.repository;

import com.gmail.olgabovkaniuk.domain.LogRowData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRowDataRepository extends CrudRepository<LogRowData, Long> {
}
