package com.example.microservices.Posts.controller;

import com.example.microservices.Posts.domain.request.PostRequest;
import com.example.microservices.Posts.domain.response.PostResponse;
import com.example.microservices.Posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public PostResponse findById(Long id) {
        return postService.findById(id);
    }

    @GetMapping
    public Page<PostResponse> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping
    public HttpEntity<Void> save(@RequestBody PostRequest postRequest) {
        Long id = postService.save(postRequest);
        return ResponseEntity
                .created(URI.create("/posts/" + id))
                .build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Void> delete(Long id) {
        postService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
