package com.sora.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * @SpringBootApplication
 *      : 스프링 부트의 자동 설정, 스프링 Bean 읽기 및 생성을 모두 자동으로 설정한다.
 *        해당 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트의 최상단에 위치해야 한다.
 * @EnableJpaAuditing
 *      : JPA Auditing 어노테이션들을 모두 활성화한다.
 * SpringApplication.run()
 *      : 내장 WAS 를 실행한다.(스프링 부트로 만들어진 Jar 파일로 실행하면 된다.)
 *        늘 같은 환경에서 스프링 부트를 배포할 수 있기 때문에 스프링 부트에서는 내장 WAS 사용을 권장한다.
 */
// @EnableJpaAuditing 삭제됨
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
