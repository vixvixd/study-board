package com.example.study.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice("com.example.study.api")
public class ExceptionApiController {

    @ResponseBody // 따로 뷰 페이지 없이, 반환 값을 그대로 응답
    @ResponseStatus(HttpStatus.NOT_FOUND) // http 상태 응답 설정, 404 not found
    @ExceptionHandler(IllegalArgumentException.class)
    public String notFound(Exception exception) {
        return "{}";
    }

}
