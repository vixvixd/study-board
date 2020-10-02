package com.example.study.dto;

import com.example.study.entity.Board;
import com.example.study.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private String author;
    private int commentCount;
    private List<Comment> comments;
    private int view;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @Builder
    public BoardDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

    }

    public BoardDto(Board entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.comments = entity.getComments();
        this.commentCount = entity.getCommentCount();
        this.view = entity.getView();
        this.createdTime = entity.getCreatedTime();
        this.updatedTime = entity.getUpdatedTime();
    }

}
