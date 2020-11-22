package dev.fvames.datetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class NewDateDemo {
    public static void main(String[] args) {
        wrong();
        right();
        better();
    }

    private static void wrong() {
        System.out.println(">>>>> wrong >>>>>>");
        Date date = new Date(2020, 11,22, 23, 49, 10);
        System.out.println(date);
    }

    private static void right() {
        System.out.println(">>>>> right >>>>>>");
        Date date = new Date(2020 - 1900, 11,22, 23, 49, 10);
        System.out.println(date);
    }

    private static void better() {
        System.out.println(">>>>> better >>>>>>");
        LocalDateTime localDateTime = LocalDateTime.of(2020, Month.DECEMBER, 22, 23, 52, 34);
        System.out.println("localDateTime: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("America/New_York"));
        System.out.println("zonedDateTime: " + zonedDateTime);
    }

}
