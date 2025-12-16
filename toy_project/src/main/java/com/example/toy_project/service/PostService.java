package com.example.toy_project.service;

import com.example.toy_project.domain.Post;
import com.example.toy_project.dto.PostUpdateRequest;
import com.example.toy_project.dto.PostCreateRequest;
import com.example.toy_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id =" + id));
    }

    @Transactional
    public Long create(PostCreateRequest req) {
        Post post = Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .author(req.getAuthor())
                .build();
        return postRepository.save(post).getId();
    }

    @Transactional
    public void update(Long id, PostUpdateRequest req) {
        Post post = findById(id);
        post.update(req.getTitle(), req.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }
}
