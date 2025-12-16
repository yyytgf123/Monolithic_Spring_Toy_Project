package com.example.toy_project.repository;

import com.example.toy_project.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속하여 Post 엔티티에 대한 CRUD 및 데이터 접근 기능을 선언한 인터페이스
// JPA는 JpaRepository를 상속한 인터페이스를 자동으로 repo로 인식
public interface PostRepository extends JpaRepository<Post, Long> {
}
