package com.example.study.controller;

import com.example.study.dto.BoardDto;
import com.example.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Long id, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("boardList", boardService.getBoardList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 버튼
        model.addAttribute("next", pageable.next().getPageNumber()); // 다음 버튼
        model.addAttribute("check", boardService.checkLastPage(pageable)); // 마지막 글 체크

        return "index";
    }

    @GetMapping("/create") // 게시글 작성 페이지
    public String create() {

        return "create";
    }

    @GetMapping("/{id}") // 게시글 상세 페이지
    public String detail(@PathVariable Long id, Model model) {

        model.addAttribute("view", boardService.updateView(id));
        model.addAttribute("board", boardService.detail(id));
        model.addAttribute("comments", boardService.detail(id).getComments());

        return "show";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {

        BoardDto boardDto = boardService.findById(id);

        model.addAttribute("board", boardDto);

        return "edit";
    }

    @GetMapping("/board/search")
    public String search(Long id, String keyword, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("view", boardService.updateView(id));
        model.addAttribute("searchList", boardService.getSearchList(keyword, pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", boardService.checkLastSearchPage(pageable, keyword)); // 마지막 글 체크
        model.addAttribute("keyword", keyword);

        return "search/searchPage";
    }
}
