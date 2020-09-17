package com.example.study.service;

import com.example.study.dto.CommentDto;
import com.example.study.entity.Board;
import com.example.study.entity.Comment;
import com.example.study.repository.BoardRepository;
import com.example.study.repository.CommentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment save(Long boardId, CommentDto commentDto) {

        // 댓글 확인
        Comment comment = commentDto.toEntity();

        // 댓글이 달릴 게시글을 가져옴
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        ()-> new IllegalArgumentException("댓글을 작성할 게시글이 없습니다")
                );

        // 댓글 엔티티에 게시글 엔티티 등록
        comment.stickTo(board);

        Comment saved = commentRepository.save(comment);

        return saved;
    }

    @Transactional
    public Comment update(Long id, CommentDto commentDto) {

        Comment comment = commentDto.toEntity();

        // DB에서 기존댓글을 가져옴
        Comment target = commentRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당 댓글이 없습니다")
                );

        // 기존 댓글을 수정
        target.update(comment.getContent());

        return commentRepository.save(target);
    }
}
