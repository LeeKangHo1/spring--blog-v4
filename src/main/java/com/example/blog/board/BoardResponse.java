package com.example.blog.board;

import lombok.Data;

public class BoardResponse {
    // DTO 여러 개 관리하기 위해 클래스를 만들어서 만드는 것

    @Data // 이건 Getter, Setter 필요
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
