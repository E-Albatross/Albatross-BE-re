package com.albatross.bareungeulssi.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="literature")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Literature {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //작품 번호

    @Column(name="title", nullable = false, length = 45)
    private String title;

    @Column(name="author", nullable = false, length = 45)
    private String author;

    @Lob
    @Column(name="plot", nullable = false)
    private String plot;

    @Column(name="checkNew")
    private Boolean checkNew; //new인지

    @Column(name="checkBest")
    private Boolean checkBest; //best인지

}
