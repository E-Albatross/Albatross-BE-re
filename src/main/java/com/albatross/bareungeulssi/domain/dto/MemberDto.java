package com.albatross.bareungeulssi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto {
    //private Long id; //회원 번호

    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId; //사용자의 id

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password; //사용자 비밀번호 //비밀번호 규칙 명시 필요

    @NotBlank(message = "비밀번호를 다시 입력해주세요.")
    private String passwordCheck; //사용자 비밀번호 확인

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email; //사용자 이메일 주소, 이메일 인증할 때 사용

}
