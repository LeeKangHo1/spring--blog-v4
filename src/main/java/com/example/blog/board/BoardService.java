package com.example.blog.board;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
    // 책임: 비즈니스 로직

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        System.out.println("BoardService");
    }
}
