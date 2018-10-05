package com.example.microservices.Posts.service;

import com.example.microservices.Posts.domain.request.PostRequest;
import com.example.microservices.Posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void save(PostRequest postRequest) {

    }
}
