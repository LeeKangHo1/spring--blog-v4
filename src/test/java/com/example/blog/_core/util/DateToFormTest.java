package com.example.blog._core.util;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateToFormTest {
    // @DataJpaTest 안 붙여도 된다. db 관련 자원 안 쓰니까

    @Test
    public void dateToForm_test() {
        // given
        Date now = new Date();

        // when
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        // eye
        System.out.println(sdf.format(now));
    }
}
