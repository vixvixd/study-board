package com.example.study.controller;

import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
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

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id).orElse(null);

        model.addAttribute("board", board);

        return "show";

    }
}
