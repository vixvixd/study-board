package com.example.study.service;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    @Transactional
    public Long save(BoardDto boardDto) {
//        Board board = boardDto.toEntity();
//        Board saved = boardRepository.save(board);
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardDto boardDto) {

        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다")
                );

        board.update(boardDto.getTitle(), boardDto.getContent());
        // 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
        // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        // 그래서 별도로 update쿼리를 날릴 필요가 없다
        // 이 개념을 더티 체킹 이라 함

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

//    @Transactional
//    public List<Board> findAllDESC(List<Board> boardList) {
//
//        return boardRepository.findAllDESC();
//    }

    @Transactional
    public List<Board> search(String keyword, Pageable pageable) {

        List<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);

        return boardList;
    }

}
