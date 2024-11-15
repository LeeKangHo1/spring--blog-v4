package com.example.blog.board;

import org.springframework.stereotype.Controller;

@Controller // IOC에 집어 넣는다.
public class BoardController {
    // 책임: 외부 클라이언트에게 요청을 받으면 응답을 해주는 객체
    // 유일하게 클라이언트에게 노출된 클래스 -> API / service, repository는 노출 x


    private final BoardService boardService;

    // DI : 의존성주입
    public BoardController(BoardService boardService) {
        System.out.println("BoardController");
        this.boardService = boardService;
    }
}
