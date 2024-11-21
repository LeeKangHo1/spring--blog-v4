package com.example.blog.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        @Size(min = 2, max = 50)
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        public Board toEntity() {
            // Board allargu 생성자 필요. noargu는 다른 곳에 쓰이니까 삭제 x
            // null을 매개변수로 주기 위해 int -> Integer
            Board board = new Board(null, title, content, null);
            // id가 null이니까 insert로 인식. AUTO INCREMENT로 id 자동생성
            // 날짜가 null이니까 @CreationTimestamp 어노테이션으로 자동 생성
            return board;
        }
    }

    @Data
    public static class UpdateDTO {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }
}
