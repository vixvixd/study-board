package com.example.study.dto;

import com.example.study.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private String author;
    private int view;
    private int commentNumber;

    @Builder
    public BoardDto(String title, String content, String author, int view, int commentNumber) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.view = view;
        this.commentNumber = commentNumber;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .view(view)
                .commentNumber(commentNumber)
                .build();

    }

    public BoardDto(Board entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.view = entity.getView();
        this.commentNumber = entity.getCommentNumber();
    }

}
