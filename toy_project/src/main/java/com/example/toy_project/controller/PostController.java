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

@Controller // View 반환용 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/posts") // 공통 URL
public class PostController {

    private final PostService postService;

    @GetMapping
    public String list(Model model) {

        List<Post> posts = postService.findAll();
        // model.addAttribute, Controller에서 조회한 데이터를 View에 전달하기 위함
        model.addAttribute("posts", posts);

        return "posts/list";
    }

    @GetMapping("/{id:\\d+}")
    // @PathVariable : URL 경로에 포함된 값을 메서드 파라미터로 바인딩
    // => URL 자체의 값을 의미 -> GET /posts/10 -> 10을 꺼내줌
    public String detail(@PathVariable Long id, Model model) {

        // 추출한 id를 사용해 postService.findById를 호출하고, DB에 조회한 게시글 엔티티를 post에 저장
        Post post = postService.findById(id);
        // 조회한 post 객체를 Model에 담고,
        model.addAttribute("post", post);
        // posts/detail 이라는 View 이름을 반환하여 해당 view에서 model 데이터를 사용해 화면에 렌더링
        return "posts/detail";
    }

    @GetMapping("/new")
    public String create(Model model) {

        // Controller -> View로 객체 전달
        // posts : view에서 접근할 이름
        // new PostCreateRequest() : 전달할 객체
        model.addAttribute("posts", new PostCreateRequest());

        return "posts/new";
    }

    @PostMapping
    // @ModelAttribute : 요청 파라미터를 객체에 바인딩하고, 그 객체를 자동으로 Model에 담아줌
    // => 요청 파라미터를 객체를 묶기
    // -------------
    // POST /posts
    // title=hello
    // content=world
    // -------------
    // => 해당 값을 postCreateRequest에 넣어줌, 이후 Model에 자동으로 담김
    public String save(@ModelAttribute PostCreateRequest postCreateRequest) {

            Long id = postService.create(postCreateRequest);
            return "redirect:/posts/" + id;
    }

    // GET 요청으로 기존 값을 채운 폼을 제공
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

    // POST 요청으로 사용자가 입력한 값을 다시 전달받아 해당 ID 엔티티 조회 후 상태 변경
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute PostUpdateRequest request) {

        postService.update(id, request);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public  String delete(@PathVariable Long id) {
        postService.delete(id);

        return "redirect:/posts";
    }
}
