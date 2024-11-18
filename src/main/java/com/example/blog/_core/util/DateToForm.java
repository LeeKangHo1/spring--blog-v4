package com.example.blog._core.util;

import com.example.blog.board.Board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToForm {

//    2024-11-18 10:27:40.425744 -> 2024.11.18 (년, 월, 일)만 출력하도록 변경
    public static String dateToFrom (Board board) {
        Date createdAt = board.getCreatedAt();
        // 대문자 MM인 이유 -> 분(minute)랑 겹치지 않게
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(createdAt);
    }
}
