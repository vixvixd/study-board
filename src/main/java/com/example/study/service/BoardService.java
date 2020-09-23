package com.example.study.service;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService{

    private final BoardRepository boardRepository;

    @Transactional
    public Boolean getListCheck(Pageable pageable) {
        Page<Board> saved = getBoardList(pageable);
        Boolean check = saved.hasNext();

        return check;
    }

    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Long save(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardDto boardDto) {

        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다")
                );

        board.update(boardDto.getTitle(), boardDto.getContent());
        // 더티체킹

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다")
                );

        boardRepository.delete(board);
    }

    @Transactional
    public Page<Board> searchList(String keyword, Pageable pageable) {

        Page<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);

        return boardList;
    }

    @Transactional
    public Boolean getSearchListCheck(Pageable pageable, String keyword) {
        Page<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);

        Boolean check = boardList.hasNext();

        return check;
    }

}
