package com.example.study.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column
    private int view;

    @Column
    private int commentCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // cascade: Board를 persist할때 자식엔티티도 자동으로 persist 해줌
    private List<Comment> comments = new ArrayList<>();       // 사용하는곳: 게시판 (자식엔티티가 오직 하나의 부모엔티티에만 연관될때, 하나의 게시글안에는 여러개의 댓글, 이미지, 태그등이 존재)
                                                              // 사용하면 안되는곳: 자식엔티티가 하나의 부모엔티티에만 속한게 아니라 여러 엔티티에 속할경우(같이 persist 될경우 관리하기가 힘들어짐)
    @Builder
    public Board(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
