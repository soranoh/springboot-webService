package com.sora.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Target(ElementType.PARAMETER)
 *      : 생성할 어노테이션이 생성될 수 있는 위치를 지정한다.
 *        ElementType.PARAMETER > 메소드의 파마리터로 선언된 객체에서만 사용할 수 있다.
 * @Retention(RetentionPolicy.RUNTIME)
 *      : 어느 시점까지 어노테이션의 메모리를 가져갈지 설정한다.
 *        RetentionPolicy.RUNTIME > 런타임시에까지 사용할 수 있다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
