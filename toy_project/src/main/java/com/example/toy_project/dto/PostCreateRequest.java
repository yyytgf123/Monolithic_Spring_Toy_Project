package com.example.toy_project.dto;

import lombok.Getter;
import lombok.Setter;

// 해당 클래스 : 게시글 생성 요청 DTO(Controller)
// 클라이언트 -> 서버로 들어오는 요청 데이터 담는 객체
@Getter // 값을 읽는 용도
@Setter // 값을 쓰는 용도
public class PostCreateRequest {
    private String title;
    private String content;
    private String author;
}
