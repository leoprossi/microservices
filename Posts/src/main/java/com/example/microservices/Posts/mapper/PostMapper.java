package com.example.microservices.Posts.mapper;

import com.example.microservices.Posts.domain.Post;
import com.example.microservices.Posts.domain.request.PostRequest;
import com.example.microservices.Posts.domain.response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Post requestToPost(PostRequest postRequest);

    PostResponse postToResponse(Post post);

    List<PostResponse> postsToResponses(Iterable<Post> posts);

    default Page<PostResponse> postsToResponses(Page<Post> posts) {
        List<PostResponse> postResponses = postsToResponses(posts.getContent());
        return new PageImpl<>(postResponses, posts.getPageable(), posts.getTotalElements());
    }
}
