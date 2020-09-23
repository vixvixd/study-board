package com.example.study.controller;

import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("boardList", boardService.getBoardList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", boardService.getListCheck(pageable));

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
    public String search(String keyword, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("searchList", boardService.searchList(keyword, pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("check", boardService.getSearchListCheck(pageable, keyword));

        return "search/searchPage";
    }
}
