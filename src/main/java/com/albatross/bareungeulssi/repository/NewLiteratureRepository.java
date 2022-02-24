package com.albatross.bareungeulssi.repository;

import com.albatross.bareungeulssi.entity.Literature;
import com.albatross.bareungeulssi.entity.New_Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewLiteratureRepository extends JpaRepository<New_Literature, Long> {
    List<New_Literature> findAll();
    Optional<New_Literature> findById(@Param("id") Long lid);
}
