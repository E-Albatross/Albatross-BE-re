package com.albatross.bareungeulssi.domain.repository;

import com.albatross.bareungeulssi.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<List<Record>> findByLoginId(String loginId);
}
