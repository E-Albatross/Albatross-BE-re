package com.albatross.bareungeulssi.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="member", uniqueConstraints = {@UniqueConstraint(name="loginId_UNIQUE", columnNames = {"loginId"})})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //회원 번호

    @Column(name="loginId", nullable = false)
    private String loginId; //사용자의 id

    @Column(name="password")
    private String password; //사용자 비밀번호

    @Column(name="email")
    private String email; //사용자 이메일 주소, 이메일 인증할 때 사용

}
