package com.example.study.controller;

import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        List<Board> boardList = boardRepository.findAll();

        model.addAttribute("boardList", boardService.findAllDESC(boardList));

        return "index";
    }

    @GetMapping("/create") // 게시글 작성 페이지
    public String create() {
        return "create";
    }

    @GetMapping("/{id}") // 게시글 상세 페이지
    public String show(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다")
                );

        model.addAttribute("board", board);
        model.addAttribute("comments", board.getComments());

        return "show";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Board edit = boardRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다")
                );
        model.addAttribute("board", edit);

        return "edit";
    }

    @GetMapping("/board/search")
    public String search(String keyword, Long id,Model model) {

        List<Board> searchList = boardService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "search/searchPage";
    }
}
