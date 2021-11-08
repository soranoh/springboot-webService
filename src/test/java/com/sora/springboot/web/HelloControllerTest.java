package com.sora.springboot.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*
 * @RunWith(SpringRunner.class)
 *      : 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행시킨다.(= SpringRunner)
 *        스프링 부트 테스트와 JUnit 사이의 연결자 역할을 한다.
 * @WebMvcTest
 *      : Web(Spring MVC) 에 집중할 수 있는 어노테이션.
 *        @Controller, @ControllerAdvice 등을 사용할 수 있다.
 *        @Service, @Component, @Repository 등은 사용할 수 없다.
 * MockMvc
 *      : 웹 API 테스트할 때 사용한다.
 *        스프링 MVC 테스트의 시작점이다.
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(amount)));
    }
}
