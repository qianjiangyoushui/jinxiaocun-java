package com.ecfund.base.action;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ApiTest {
    @Test
    public void testCalendar()throws Exception{
        Calendar today = Calendar.getInstance();
        Calendar otherDay = Calendar.getInstance();
        String dateStr = "2019-3-23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = sdf.parse(dateStr);
        otherDay.setTime(todayDate);
        int day = today.get(Calendar.DAY_OF_YEAR);
        System.out.println(day);
        int year = today.get(Calendar.YEAR);
        System.out.println(year);
        System.out.println(today.compareTo(otherDay));
        GregorianCalendar gtoday =new  GregorianCalendar();
        System.out.println(gtoday.isLeapYear(2016));
    }
}
