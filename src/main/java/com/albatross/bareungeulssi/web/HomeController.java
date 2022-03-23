package com.albatross.bareungeulssi.web;

import com.albatross.bareungeulssi.domain.entity.Member;
import com.albatross.bareungeulssi.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    //@GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String homeLoginArgumentResolver(@Login Member loginMember, Model model){

        //로그인
        //실패 (세션에 회원 데이터가 없으면)
        if(loginMember==null){
            return "home";
        }

        //성공 (세션이 유지되면 로그인으로 이동)
        model.addAttribute("member", loginMember);
        return "loginHome";

    }
}


