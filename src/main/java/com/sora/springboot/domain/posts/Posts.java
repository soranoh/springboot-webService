package com.sora.springboot.domain.posts;

import com.sora.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @NoArgsConstructor
 *      : 파라미터가 없는 기본 생성자를 생성한다.
 * @Entity
 *      : 테이블과 연결될 클래스임을 명시한다.
 * @Id
 *      : 해당 테이블의 PK 필드임을 명시한다.
 * @GeneratedValue
 *      : PK 의 생성 규칙을 나타낸다.
 * @Column
 *      : 테이블의 컬럼임을 명시하며, 추가되는 옵션이 없을 경우는 사용하지 않아도 된다.
 * @Builder
 *      : 해당 클래스의 빌더 패턴 클래스를 생성한다.
 *        생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함된다.
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
