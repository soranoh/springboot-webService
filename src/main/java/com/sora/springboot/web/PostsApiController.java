package com.sora.springboot.web;

import com.sora.springboot.service.posts.PostsService;
import com.sora.springboot.web.dto.PostsResponseDto;
import com.sora.springboot.web.dto.PostsSaveRequestDto;
import com.sora.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long registPost(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.registPost(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.updatePost(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto getPostById(@PathVariable Long id) {
        return postsService.getPostById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postsService.deletePost(id);
        return id;
    }
}
