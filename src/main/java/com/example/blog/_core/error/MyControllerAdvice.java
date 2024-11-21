package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {
    // 이 클래스의 책임 : 에러처리

    @ResponseBody // 문자 그대로 반환
    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        System.out.println("err400");
        // 응답 헤더 설정을 안하면 text/html로 날아가서 브라우저가 해석해서 실행
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage());
        return body;
    }

    @ResponseBody // 문자 그대로 반환
    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e) {
        System.out.println("err404");
        // 응답 헤더 설정을 안하면 text/html로 날아가서 브라우저가 해석해서 실행
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage());
        return body;
    }
}
