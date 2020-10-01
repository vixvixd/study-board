package com.example.study.service;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.entity.Comment;
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

    // TODO: 블로그처럼 포스팅(ex 이미지 등), 로그인, 게시글작성 권한 나만, 검색결과에 태그도 포함 시키기(SQL OR 사용), 게스트권한은 댓글만
    //  회원가입시 자동으로 게스트권한(반대로도 연습해보기 ex 회원가입시 자동으로 주인권한), 정렬기능(ex 조회수 오름내림, 최신순 오름내림, 댓글순 오름내림 등등),
    //  서버 배포

    // TODO: 공부할것들 - 좋아요(한번 누르면 클릭 안되게), 댓글갯수(컬럼만 만들엇음, 기능구현x), 좋아요 갯수

    private final BoardRepository boardRepository;

    @Transactional // API update
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
    public BoardDto detail(Long id) { // 타입 기존 entity -> dto로 변경
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 게시글이 없습니다")
                );
        return new BoardDto(board);
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
    public Boolean checkLastPage(Pageable pageable) { // 마지막 페이지 일시 버튼 비 활성화

        Page<Board> saved = getBoardList(pageable);

        Boolean check = saved.hasNext();

        return check;
    }

    @Transactional
    public Page<Board> getSearchList(String keyword, Pageable pageable) { // 검색 결과 페이지 정렬 및 페이징

        return boardRepository.findByTitleContaining(keyword, pageable);
    }

    @Transactional
    public Boolean checkLastSearchPage(Pageable pageable, String keyword) { // 마지막 페이지 일시 버튼 비 활성화

        Page<Board> boardList = getSearchList(keyword, pageable);

        Boolean check = boardList.hasNext();

        return check;

    }

    @Transactional
    public int updateView(Long id) {

        return boardRepository.updateView(id);
    }
}
