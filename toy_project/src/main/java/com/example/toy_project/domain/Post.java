package com.example.toy_project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity // 해당 클래스가 JPA 엔티티(= DB Table)임을 의미
@Getter // 모든 필드에 getter 자동 생성
@Table(name = "posts") // 매핑될 테이블 이름
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자를 요구함
public class Post {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

    @Builder // 객체 생성 전용 빌더 패턴
    private Post(Long id, String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
    }
}
