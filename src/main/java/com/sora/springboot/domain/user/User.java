package com.sora.springboot.domain.user;

import com.sora.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @Enumerated(EnumType.STRING)
 *      : JPA 로 DB로 저장할 때 Enum 값을 어떤 형태로 저장하는지 결정한다.
 *        기본적으로는 int 로 저장되어 무슨 코드를 의미하는지 알 수 없으므로 해당 클래스에서는 문자열로 저장한다.
 */
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
