package com.albatross.bareungeulssi.web.member;

import com.albatross.bareungeulssi.domain.dto.MemberDto;
import com.albatross.bareungeulssi.domain.entity.Member;
import com.albatross.bareungeulssi.domain.member.MemberService;
import com.albatross.bareungeulssi.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@ToString
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/add") //회원가입 폼 보여주기
    public String addForm(@ModelAttribute("memberdto") MemberDto memberDto){
        return "members/addMemberForm";
    }

    @GetMapping("/user-emails/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(memberService.checkEmailDuplicate(email));
    }

    @GetMapping("/user-loginIds/{loginId}/exists")
    public ResponseEntity<Boolean> checkLoginIdDuplicate(@PathVariable String loginId){
        return ResponseEntity.ok(memberService.checkLoginIdDuplicate(loginId));
    }

    @PostMapping("/add") //회원가입 정보 넘기기
    public String save(@Validated @ModelAttribute("memberdto") MemberDto memberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }
        if(!memberService.checkPassword(memberDto.getPassword(), memberDto.getPasswordCheck())){
            return "members/addMemberForm";
        }

        Member member = new Member(memberDto.getLoginId(), memberDto.getPassword(), memberDto.getEmail());
        memberRepository.save(member); //회원 정보 저장 = 회원 가입 성공
        return "redirect:/";
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
