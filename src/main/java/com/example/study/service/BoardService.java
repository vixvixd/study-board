package com.example.study.service;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService{

    // TODO: 좋아요, 블로그처럼 포스팅(ex 이미지 등), 로그인, 게시글작성 권한 나만, 검색결과에 태그도 포함 시키기(SQL OR 사용), 게스트권한은 댓글만
    //  회원가입시 자동으로 게스트권한(반대로도 연습해보기 ex 회원가입시 자동으로 주인권한)

    private final BoardRepository boardRepository;

    @Transactional
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다.")
                );
        return new BoardDto(board);
    }

    @Transactional
    public Long save(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public Board detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다")
                );
        return board;
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
    public Page<Board> getBoardList(Pageable pageable) { // 메인 페이지 정렬 및 페이징

        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Boolean getListCheck(Pageable pageable) { // 마지막 페이지 일시 버튼 비 활성화

        Page<Board> saved = getBoardList(pageable);

        Boolean check = saved.hasNext();

        return check;
    }

    @Transactional
    public Page<Board> getSearchList(String keyword, Pageable pageable) { // 검색 결과 페이지 정렬 및 페이징

        return boardRepository.findByTitleContaining(keyword, pageable);
    }

    @Transactional
    public Boolean getSearchListCheck(Pageable pageable, String keyword) { // 마지막 페이지 일시 버튼 비 활성화

        Page<Board> boardList = getSearchList(keyword, pageable);

        Boolean check = boardList.hasNext();

        return check;
    }

    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }
}
