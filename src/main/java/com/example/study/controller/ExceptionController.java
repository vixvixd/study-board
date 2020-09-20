package com.example.study.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.example.study.controller") // 패키지 내 모든 에러 처리
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class) // 예외 발생 시 수행
    public String notFound(Exception exception, Model model) {

        model.addAttribute("exception", exception);

        return "error/404-error";
    }

}
