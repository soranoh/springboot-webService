package com.sora.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/*
 * Assertions.assertThat()
 *      : assertj 의 테스트 검증 라이브러리의 검증 메소드이다.
 *        검증하고 싶은 대상을 메소드 인자로 받아 검증한다.
 *        Junit 의 assertThat 과 달리 추가적으로 라이브러리가 필요하지 않으며, 자동 완성이 좀 더 확실하게 지원된다.
 */
public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
