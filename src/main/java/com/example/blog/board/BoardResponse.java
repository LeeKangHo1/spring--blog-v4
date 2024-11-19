package com.example.blog.board;

import com.example.blog._core.util.DateToForm;
import lombok.Data;

public class BoardResponse {
    // 여러 개의 DTO 객체를 관리하기 위해 BoardResponse 클래스 작성

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt; // Timestamp인데 string으로 해도 된다.

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = DateToForm.dateToFrom(board);
        }
    }

    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt; // Timestamp인데 string으로 해도 된다.

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = DateToForm.dateToFrom(board);
        }
    }

    @Data // Getter, Setter 필요
    public static class ReadDTO {
        // BoardResponse.DTO 로 쓰기 위해 static으로 올리는 것
        private int id;
        private String title;

        public ReadDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
