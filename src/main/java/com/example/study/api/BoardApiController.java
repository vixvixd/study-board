package com.example.study.api;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public Long save(@RequestBody BoardDto boardDto) {
        return boardService.save(boardDto);

    }
}
