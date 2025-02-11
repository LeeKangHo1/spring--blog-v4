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
            Board board = new Board(null, title, content, null);
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
