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
}