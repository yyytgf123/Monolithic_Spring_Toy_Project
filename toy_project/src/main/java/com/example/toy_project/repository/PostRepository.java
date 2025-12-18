package com.example.toy_project.repository;

import com.example.toy_project.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    // save, fineById, findAll, delete 자동 제공
}

