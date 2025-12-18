package com.example.toy_project.repository;

import com.example.toy_project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Member 엔티티를 DB에 저장, 조회, 수정, 삭제하기 위한 기본 Repo
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Spring Data JPA에서 메서드 이름 기반 쿼리 생성 기능을 이용한 조회 메서드
    // 별도의 구현 없이도 자동으로 DB와 통신한다.
    //
    // [동작 조건]
    // 1. Repository 인터페이스가 JpaRepository를 상속해야 한다.
    // 2. 메서드 이름이 Spring Data JPA에서 정의한 예약된 키워드 문법을 따라야 한다.
    //    (findBy, existsBy, countBy, deleteBy 등)
    // 3. 조건으로 사용하는 필드명(email)이 엔티티(Member)에 실제로 존재해야 한다.
    //
    // 위 조건을 만족하면 Spring Data JPA가
    // 메서드 이름을 분석하여 JPQL/SQL을 자동 생성해 실행한다.
    //
    // 반환 타입을 Optional로 지정한 이유는
    // 조회 결과가 없을 수도 있기 때문에 null 대신 Optional을 사용하여
    // NullPointerException을 방지하고 안전하게 처리하기 위함이다.
    Optional<Member> findByEmail(String email);

    // 이메일 존재 여부
    boolean existsByEmail(String email);
}
