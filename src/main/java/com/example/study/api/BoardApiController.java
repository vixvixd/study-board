package com.example.study.api;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("/api/board")
    public Long save(@RequestBody BoardDto boardDto) {
        return boardService.save(boardDto);
    }

    @PutMapping("/api/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        return boardService.update(id, boardDto);
    }

    @GetMapping("/api/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다")
                );
        return new BoardDto(entity);
    }
}
