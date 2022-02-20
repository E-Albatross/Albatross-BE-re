package com.albatross.bareungeulssi.repository;

import com.albatross.bareungeulssi.entity.Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LiteratureRepository extends JpaRepository<Literature, Long> {

    List<Literature> findByTitle(String title);
    //List<Literature> findByAuthor(String author);
    List<Literature> findAll();
    @Query("select l from Literature l where l.author like %:author% order by l.id")
    List<Literature> findByAuthor(@Param("author") String author);
}
