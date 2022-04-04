package com.albatross.bareungeulssi.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="member")
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

//    @Column(name="loginId", nullable = false)
//    private String loginId; //사용자의 id
//
//    @Column(name="password", nullable = false)
//    private String password; //사용자 비밀번호

    @Column(name="email", nullable = false)
    private String email; //사용자 이메일 주소

    public Member(String loginId, String password, String email){
//        this.loginId = loginId;
//        this.password = password;
        this.email = email;
    }

}
