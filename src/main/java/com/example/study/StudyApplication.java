package com.example.study;

import com.example.study.entity.Board;
import com.example.study.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}


	// 자동 데이터 삽입
	@Bean
	public CommandLineRunner initData(BoardRepository boardRepository) {
		return args ->
				IntStream.rangeClosed(1, 30).forEach(i -> {
					Board board = Board.builder()
							.title("테스트 제목" + i)
							.author("테스트 작성자"+ i)
							.content("테스트 본문" + i)
							.build();

					boardRepository.save(board);
				});
	}
}
