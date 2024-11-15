package com.example.blog.board;

import lombok.Data;

public class BoardResponse {
    // 여러 개의 DTO 객체를 관리하기 위해 BoardResponse 클래스 작성

    @Data // Getter, Setter 필요
    public static class DTO {
        // BoardResponse.DTO 로 쓰기 위해 static으로 올리는 것
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
