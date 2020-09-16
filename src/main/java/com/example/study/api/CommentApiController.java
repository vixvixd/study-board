package com.example.study.api;

import com.example.study.dto.CommentDto;
import com.example.study.entity.Comment;
import com.example.study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comments/{boardId}")
    public Long create(@PathVariable Long boardId, @RequestBody CommentDto commentDto) {
        return commentService.save(boardId, commentDto);
    }
}
