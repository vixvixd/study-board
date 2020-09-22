package com.example.study.repository;

import com.example.study.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query("SELECT p FROM Board p ORDER BY p.id DESC")
//    List<Board> findAllDESC();

    List<Board> findByTitleContaining(String keyword, Pageable pageable);

}
