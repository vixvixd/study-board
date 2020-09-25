package com.example.study.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Table(name = "board")
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
    private int commentNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Comment> comments;

    @Builder
    public Board(Long id, String title, String content, String author, List<Comment> comments, int view, int commentNumber) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.author = author;
        this.view = view;
        this.commentNumber = commentNumber;
    }

    public void update(String title, String content, int view, List<Comment> comments , int commentNumber) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.comments = comments;
        this.commentNumber = commentNumber;
    }
}
