package com.example.study.dto;

import com.example.study.entity.Board;
import com.example.study.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private String author;
    private List<Comment> comments;
    private int view;
    private int commentNumber;

    @Builder
    public BoardDto(String title, String content, String author, List<Comment> comments, int view, int commentNumber) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
        this.view = view;
        this.commentNumber = commentNumber;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .comments(comments)
                .view(view)
                .commentNumber(commentNumber)
                .build();

    }

    public BoardDto(Board entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.comments = entity.getComments();
        this.view = entity.getView();
        this.commentNumber = entity.getCommentNumber();
    }

}
