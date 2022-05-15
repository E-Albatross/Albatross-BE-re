package com.albatross.bareungeulssi.web.score;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@Setter
public class Score {

    //점수 계산할 때 필요한 항목
    //imageName, fontPath, literatureId, textBookAnalysis, UserAnalysis, score
    //fontSize는 나중에 유동적인 걸로 바꾸기

    private String fontPath;
    private Long literatureId;
    private List<List<Integer>> textBookSyllableAnalysis;
    private List<List<List<Integer>>> textBookJamoAnalysis;
    private List<List<Integer>> userSyllableAnalysis;
    private List<List<List<Integer>>> userJamoAnalysis;
    private int score;

    public Score(){

    }

    @Builder
    public Score(String fontPath, Long literatureId, List<List<Integer>> textBookSyllableAnalysis, List<List<List<Integer>>> textBookJamoAnalysis, List<List<Integer>> userSyllableAnalysis, List<List<List<Integer>>> userJamoAnalysis, int score){
        this.fontPath = fontPath;
        this.literatureId = literatureId;
        this.textBookSyllableAnalysis = textBookSyllableAnalysis;
        this.userSyllableAnalysis = userSyllableAnalysis;
        this.userJamoAnalysis = userJamoAnalysis;
        this.score = score;
    }

}
