package com.albatross.bareungeulssi.domain.member;

import com.albatross.bareungeulssi.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService { //회원가입 시, id 중복여부, email 중복여부, 비밀번호와 비밀번호확인 칸의 문자열이 같은지 체크

    private final MemberRepository memberRepository;

    //id 중복여부
    public boolean checkLoginIdDuplicate(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    //email 중복여부
    public boolean checkEmailDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }

    //비밀번호, 비밀번호 확인 같은지 체크
    public boolean checkPassword(String password, String passwordCheck){
        return password.equals(passwordCheck);
    }

}
