package com.sora.springboot.web;

import com.sora.springboot.config.auth.LoginUser;
import com.sora.springboot.config.auth.dto.SessionUser;
import com.sora.springboot.service.posts.PostsService;
import com.sora.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String mainPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.getPostList());

        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String registPostPage(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePostPage(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto responseDto = postsService.getPostById(id);
        model.addAttribute("post", responseDto);

        if(user != null) {
            model.addAttribute("loginUserName", user.getName());
        }

        return "posts-update";
    }

}
