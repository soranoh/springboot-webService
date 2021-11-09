package com.sora.springboot.web;

import com.sora.springboot.service.posts.PostsService;
import com.sora.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("posts", postsService.getPostList());
        return "index";
    }

    @GetMapping("/posts/save")
    public String registPostPage() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePostPage(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = postsService.getPostById(id);
        model.addAttribute("post", responseDto);
        return "posts-update";
    }

}
