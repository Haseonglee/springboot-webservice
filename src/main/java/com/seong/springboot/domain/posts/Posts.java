package com.seong.springboot.domain.posts;


import com.seong.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내의 모든 필드의 getter 메소드를 자동생성
@NoArgsConstructor
@Entity //테이블과 링크될 클래스임을 나타낸다.

public class Posts extends BaseTimeEntity {  //Posts 클래스가 BaseTimeEntity를 상속받도록 변경

    @Id //해당 테이블의 pk필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk의 생성규칙을 나타낸다.
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타냄
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌드 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}