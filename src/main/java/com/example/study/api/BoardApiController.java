package com.example.study.api;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @DeleteMapping("/api/board/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }

    @GetMapping("/api/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다")
                );

        return new BoardDto(entity);
    }

    @GetMapping("/api/board/boardlist")
    public Page<Board> getBoardList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> boards = boardService.getBoardList(pageable);

        return boards;
    }

    @GetMapping("/api/board/searchlist")
    public Page<Board> getSearchList(String keyword, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> boards = boardService.getSearchList(keyword, pageable);

        return boards;
    }

}
