package com.albatross.bareungeulssi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="record")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Record { //쓴 거 저장
    //레코드 아이디, 회원id, 작품id, 이미지 아이디(주소), 날짜, 점수
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="loginId", nullable = false)
    private String loginId;

    @Column(name="literatureId", nullable = false)
    private String literatureId;

    @Column(name = "imageId")
    private String imageId;

    @Column(name="date")
    private Date date;

    @Column(name="score")
    private Integer score;
}
