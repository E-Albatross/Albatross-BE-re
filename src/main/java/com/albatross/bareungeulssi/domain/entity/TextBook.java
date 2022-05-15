package com.albatross.bareungeulssi.domain.entity;

import com.albatross.bareungeulssi.domain.converter.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name="textbook")
@Table(name="textbook")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TextBook {

    //교본 분석 결과 저장할 테이블

    @Id
    @Column(name = "textbook_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long textbookId;

    @Column(name = "literature_id", nullable = false)
    private Long literatureId;

    @Column(name = "font_path", nullable = false)
    private String fontPath;

    //교본 분석 결과 저장할 곳
    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "json", name = "textbook_analysis")
    private List<String> textBookAnalysis;

}
