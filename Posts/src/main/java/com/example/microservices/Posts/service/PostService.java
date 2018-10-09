package com.example.microservices.Posts.service;

import com.example.microservices.Posts.domain.Post;
import com.example.microservices.Posts.domain.request.PostRequest;
import com.example.microservices.Posts.domain.response.PostResponse;
import com.example.microservices.Posts.mapper.PostMapper;
import com.example.microservices.Posts.repository.PostRepository;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Long save(PostRequest postRequest) {
        Post post = postMapper.requestToPost(postRequest);
        return postRepository.save(post)
                .getId();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return postMapper.postToResponse(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> findAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return postMapper.postsToResponses(posts);
    }

    public void delete(Long id) {
        Post post = new Post();
        post.setId(id);
        postRepository.delete(post);
    }
}
