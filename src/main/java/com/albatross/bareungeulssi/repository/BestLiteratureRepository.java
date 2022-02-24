package com.albatross.bareungeulssi.repository;

import com.albatross.bareungeulssi.entity.Best_Literature;
import com.albatross.bareungeulssi.entity.New_Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BestLiteratureRepository extends JpaRepository<Best_Literature, Long> {
    List<Best_Literature> findAll();
    Optional<Best_Literature> findById(@Param("id") Long lid);

}
