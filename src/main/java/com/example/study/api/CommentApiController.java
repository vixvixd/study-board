package com.example.study.api;

import com.example.study.dto.CommentDto;
import com.example.study.entity.Comment;
import com.example.study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comments/{boardId}")
    public Long create(@PathVariable Long boardId, @RequestBody CommentDto commentDto) {
        Comment saved = commentService.save(boardId, commentDto);

        return saved.getId();
    }

    @PutMapping("/api/comments/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentDto commentDto) {

        Comment update = commentService.update(id, commentDto);

        return update.getId();

    }
}
