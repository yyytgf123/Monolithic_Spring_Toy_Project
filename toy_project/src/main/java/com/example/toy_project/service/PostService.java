package com.example.toy_project.service;

import com.example.toy_project.domain.Post;
import com.example.toy_project.dto.PostCreateRequest;
import com.example.toy_project.dto.PostUpdateRequest;
import com.example.toy_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 계층 Bean 등록
@RequiredArgsConstructor // final 필드 생성자 자동 생성
@Transactional(readOnly = true) // 읽기 전요 트랜잭션으로 메서드를 실행
public class PostService {

    private final PostRepository postRepository;

    // 전체 게시글 조회
    public List<Post> findAll() { // Post 엔티티 여러 개를 순서 있게 조회
        return postRepository.findAll();
    }

    // 게시물 단건 조회
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("게시글이 없습니다."));
    }

    // 게시글 생성
    @Transactional // 메서드 실행을 하나의 트랜잭션으로 묶어 작업이 모두 성공하면 커밋, 실패 시 롤백
    public Long create(PostCreateRequest request) {

        Post post = Post.builder() // Lombok이 @Builder를 보고 자동으로 만든 메서드
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .build();

        return postRepository.save(post).getId();
    }

    // 게시글 수정
    @Transactional
    public void update(Long id, PostUpdateRequest request) {

        Post post = findById(id);

        post.update(
                request.getTitle(),
                request.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }
}
