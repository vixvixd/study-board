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
    }

}
