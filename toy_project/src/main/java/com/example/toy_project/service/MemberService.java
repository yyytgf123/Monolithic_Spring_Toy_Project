package com.example.toy_project.service;

import com.example.toy_project.domain.Member;
import com.example.toy_project.dto.MemberLoginRequest;
import com.example.toy_project.dto.MemberSignupRequest;
import com.example.toy_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public void signup(MemberSignupRequest request) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalIdentifierException("Email already exists");
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .build();

        memberRepository.save(member);
    }

    // 로그인
    public Member login(MemberLoginRequest request) {
        // getEmail 사용 가능한 이유 : lombok의 getter를 통해 dto에 정의된
        //                           email이 getEmail이 됌
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalIdentifierException("Email Not Found"));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new IllegalIdentifierException("Password Mismatch");
        }

        return member;
    }
}
