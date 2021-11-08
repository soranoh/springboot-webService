package com.sora.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * @RequiredArgsConstructor
 *      : 선언된 모든 final 필드가 포함된 생성자를 생성해준다.
 *        final 이 없는 필드는 생성자에 포함되지 않는다.
 */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
