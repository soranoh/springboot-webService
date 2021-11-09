package com.sora.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sora.springboot.domain.posts.Posts;
import com.sora.springboot.domain.posts.PostsRepository;
import com.sora.springboot.web.dto.PostsSaveRequestDto;
import com.sora.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void cleanUp() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void registPost() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        /*
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
         */
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles="USER")
    public void updatePost() throws Exception {

        String updateTitle = "update title";
        String updateContent = "update content";
        Posts defaultPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long id = defaultPosts.getId();

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + id;

        /*
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

         */
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Posts> all = postsRepository.findAll();

        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(updateContent);
    }
}
