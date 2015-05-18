package org.jfree.date;

import java.util.Calendar;

/**
 * Created by pkrueger on 5/17/2015.
 */

public enum Day {
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);


    public int index;

    Day(int index) {
        this.index = index;
    }

    public static Day make(int index) throws IllegalArgumentException {
        for (Day d : Day.values())
            if(d.index == index)
                return d;
        throw new IllegalArgumentException("Invalid day index " + index);
    }

    public static Day parse(String s) {
        String[] shortWeekdayNames = DATE_FORMAT_SYMBOLS.getShortWeekdays();
        String[] weekDayNames = DATE_FORMAT_SYMBOLS.getWeekdays();

        s = s.trim();
        for (Day day : Day.values()) {
            if (s.equalsIgnoreCase(shortWeekdayNames[day.index]) || s.equalsIgnoreCase(weekDayNames[day.index]))
                return day;
        }
        throw new IllegalArgumentException("String " + s + " is not recognized as a day of the week.");
    }

    public static String toString(Day weekday) {
        String[] weekdays = DATE_FORMAT_SYMBOLS.getWeekdays();
        return weekdays[weekday.index];
    }
}