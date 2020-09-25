package com.example.study.controller;

import com.example.study.entity.Board;
import com.example.study.entity.Comment;
import com.example.study.repository.BoardRepository;
import com.example.study.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private BoardService boardService;

    @Test
    public void 메인페이지로딩() {
        //when
        String body = this.testRestTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("안녕");
    }

    @Test
    public void 조회수_테스트() {
        //given

        //when
        int view = boardService.updateView(1L);

        //then
        assertThat(view).isEqualTo(1);
    }
}