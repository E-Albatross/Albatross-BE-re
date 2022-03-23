package com.albatross.bareungeulssi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiteratureDto {
    private Long id;
    private String title;
    private String author;
    private String plot;
    private Boolean checkNew;
    private Boolean checkBest;
}
