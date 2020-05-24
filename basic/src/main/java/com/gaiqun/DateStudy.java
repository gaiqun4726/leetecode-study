package com.gaiqun;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author gaiqun
 * @date 2020/4/22
 */
public class DateStudy {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        // 利用Instant作为中介转换Date和LocalDate。注意需要指定时区。
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime);
        System.out.println(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }
}
