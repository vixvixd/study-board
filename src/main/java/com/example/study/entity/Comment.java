package com.example.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 사용 권장 (ToOne은 디폴트 값이 EAGER, ToMany는 디폴드 값이 LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String author, String content, Board board) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.board = board;
    }

    public void stickTo(Board board) {
        this.board = board;
    }

    public void update(String content) {
        this.content = content;
    }
}
