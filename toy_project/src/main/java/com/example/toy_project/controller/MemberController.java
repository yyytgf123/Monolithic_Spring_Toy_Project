package com.example.toy_project.controller;

import com.example.toy_project.domain.Member;
import com.example.toy_project.dto.MemberLoginRequest;
import com.example.toy_project.dto.MemberSignupRequest;
import com.example.toy_project.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberSignRequest", new MemberSignupRequest());
        return "members/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String loginForm(@ModelAttribute MemberSignupRequest request) {
        memberService.signup(request);

        return "redirect:/members/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        return "members/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginRequest request, HttpSession session) {
        Member member = memberService.login(request);

        session.setAttribute("memberLoginRequest", member);

        return "redirect:/posts";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
