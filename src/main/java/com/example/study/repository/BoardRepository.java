package com.example.study.repository;

import com.example.study.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

    @Modifying
    @Query("update Board p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

}
