package com.albatross.bareungeulssi.repository;

import com.albatross.bareungeulssi.entity.Literature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiteratureRepositoryTest {

    @Autowired
    LiteratureRepository literatureRepository;

    @Test
    @DisplayName("작품 저장 테스트")
    void createLitTest(){
        Literature literature1 = new Literature();
        literature1.setTitle("테스트 제목1");
        literature1.setAuthor("윤동주");
        literature1.setPlot("테스트 내용1");
        Literature saved1 = literatureRepository.save(literature1);
        System.out.println(saved1.toString());
    }

    @Test
    @DisplayName("작가 이름으로 찾기 테스트")
    void findByAuthorTest(){
        Literature literature1 = new Literature();
        literature1.setTitle("테스트 제목1");
        literature1.setAuthor("윤동주");
        literature1.setPlot("테스트 내용1");
        Literature saved1 = literatureRepository.save(literature1);

        Literature literature2 = new Literature();
        literature2.setTitle("테스트 제목2");
        literature2.setAuthor("윤동주");
        literature2.setPlot("테스트 내용2");
        Literature saved2 = literatureRepository.save(literature2);

        List<Literature> literatureList = literatureRepository.findByAuthor("윤동주");
        for(Literature literature : literatureList){
            System.out.println(literature.toString());
        }



    }

}