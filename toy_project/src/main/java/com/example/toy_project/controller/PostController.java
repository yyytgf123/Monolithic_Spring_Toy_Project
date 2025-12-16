package com.example.toy_project.controller;

import com.example.toy_project.domain.Post;
import com.example.toy_project.dto.PostCreateRequest;
import com.example.toy_project.dto.PostUpdateRequest;
import com.example.toy_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 목록
    @GetMapping
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    // 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "posts/detail";
    }

    // 글쓰기 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "posts/new";
    }

    // 글쓰기 처리
    @PostMapping
    public String create(@ModelAttribute PostCreateRequest req) {
        Long id = postService.create(req);
        return "redirect:/posts/" + id;
    }

    // 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);

        PostUpdateRequest form = new PostUpdateRequest();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());

        model.addAttribute("post", post);
        model.addAttribute("postUpdateRequest", form);
        return "posts/edit";
    }

    // 수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute PostUpdateRequest req) {
        postService.update(id, req);
        return "redirect:/posts/" + id;
    }

    // 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

}
