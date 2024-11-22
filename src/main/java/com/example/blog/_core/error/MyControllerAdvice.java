package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import com.example.blog._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {
    // 이 클래스의 책임 : 에러처리

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> err400(Exception400 e) {
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST); // 400
        return rn;
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> err404(Exception404 e) {
        // ResponseEntity로 404로 바꾸지 않을 경우 200이라 문제가 생긴다.
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND); // 404
        return rn;
    }
}
