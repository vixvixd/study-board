package com.example.study.api;

import com.example.study.dto.BoardDto;
import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import org.junit.After;
import org.junit.Test;
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
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void clean() {
        boardRepository.deleteAll();
    }

    @Test
    public void 게시글_저장() {
        //given 준비
        String title = "테스트 제목";
        String content = "테스트 본문";
        String author = "테스트 계정";

        BoardDto boardDto = BoardDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:"+port+"/api/board";

        //when 실행
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, boardDto, Long.class);

        //then 검증
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> saved = boardRepository.findAll();
        assertThat(saved.get(0).getTitle()).isEqualTo(title);
        assertThat(saved.get(0).getContent()).isEqualTo(content);
        assertThat(saved.get(0).getAuthor()).isEqualTo(author);

    }
}