package com.example.blog.board;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            this.createdAt = getDateFormat(board);
        }

        // 2024-11-18 10:27:40.425744 -> 2024.11.18 (년, 월, 일)만 출력하도록 변경
        private static String getDateFormat(Board board) {
            // DateTimeFormatter를 사용하여 년-월-일 형식으로 포맷
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Timestamp를 LocalDateTime으로 변환
            Timestamp timestamp = board.getCreatedAt();
            LocalDateTime dateTime = timestamp.toLocalDateTime();

            // 포맷 적용 후 문자열로 변환
            String date = dateTime.format(formatter);
            return date;
        }
    }

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
