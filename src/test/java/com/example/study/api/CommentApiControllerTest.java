package com.example.study.api;

import com.example.study.dto.CommentDto;
import com.example.study.entity.Board;
import com.example.study.entity.Comment;
import com.example.study.repository.BoardRepository;
import com.example.study.repository.CommentRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentApiControllerTest {

    @LocalServerPort
    private Long port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void clean() {
        commentRepository.deleteAll();
    }

    @Test
    public void 댓글_저장() throws Exception{
        //given
        Board saved = boardRepository.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long boardId = saved.getId();

        String content = "comment";
        String author = "익명";

        CommentDto comment = CommentDto.builder()
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:"+port+"/api/comments/"+boardId;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, comment, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.get(0).getContent()).isEqualTo(content);
        assertThat(comments.get(0).getAuthor()).isEqualTo(author);
    }

    @Test
    public void 댓글_수정() throws Exception {
        //given
        Board boardSaved = boardRepository.save(Board.builder()  // 게시글 저장
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long boardId = boardSaved.getId();

        Comment commentSaved = commentRepository.save(Comment.builder() // 댓글 저장
                .author("익명")
                .content("content")
                .build());

        String updatedAuthor = "익명1";
        String updatedContent = "게시글1";

        CommentDto commentDto = CommentDto.builder() // 댓글 수정
                .content(updatedContent)
                .author(updatedAuthor)
                .build();

        String url = "http://localhost:"+port+"/api/comments/"+boardId;

        HttpEntity<CommentDto> requestEntity = new HttpEntity<>(commentDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Comment> updated = commentRepository.findAll();
        List<Board> board = boardRepository.findAll();
        assertThat(updated.get(0).getContent()).isEqualTo(updatedContent); // 댓글 수정 확인
        assertThat(board.get(0).getTitle()).isEqualTo("title"); // 게시글 확인
    }
}