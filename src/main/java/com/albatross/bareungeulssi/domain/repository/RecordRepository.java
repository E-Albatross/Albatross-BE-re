package com.albatross.bareungeulssi.domain.repository;

import com.albatross.bareungeulssi.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
