package com.sora.springboot.service.posts;

import com.sora.springboot.domain.posts.Posts;
import com.sora.springboot.domain.posts.PostsRepository;
import com.sora.springboot.web.dto.PostsResponseDto;
import com.sora.springboot.web.dto.PostsSaveRequestDto;
import com.sora.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long registPost(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long updatePost(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. (id : " + id + ")"));

        posts.updatePost(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto getPostById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습ㄴ다. (id : " + id + ")"));

        return new PostsResponseDto(posts);
    }
}
