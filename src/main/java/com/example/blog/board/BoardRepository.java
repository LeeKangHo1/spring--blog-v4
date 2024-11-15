package com.example.blog.board;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
    // 책임: DB와 상호작용


    public BoardRepository() {
        System.out.println("BoardRepository");
    }
}
