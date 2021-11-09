package com.sora.springboot.domain.posts;

import com.sora.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @After
 *      : JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정한다.
 * postsRepository.save()
 *      : 테이블 posts 에 id 값이 있다면 update, 없다면 insert 쿼리가 실행된다.
 * postsRepository.findAll()
 *      : 테이블 posts 에 있는 모든 데이터를 조회하는 메소드다.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void callRegistPost() {
        String title = "Test Title";
        String content = "Test Content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("Test Author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntityTest() {
        LocalDateTime currentTime = LocalDateTime.of(2021, 11, 9, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        List<Posts> postsList = postsRepository.findAll();
        Posts post = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createdDate = " + post.getCreatedDate() + " //////// modifiedDate = " + post.getModifiedDate());

        Assertions.assertThat(post.getCreatedDate()).isAfter(currentTime);
        Assertions.assertThat(post.getModifiedDate()).isAfter(currentTime);
    }
}
