package com.example.study.dto;

import com.example.study.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long id;

    private String content;

    private String author;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .content(content)
                .author(author)
                .build();
    }

    @Builder
    public CommentDto(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
