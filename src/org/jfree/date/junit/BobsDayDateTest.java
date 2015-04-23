package org.jfree.date.junit;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.jfree.date.*;

import java.util.GregorianCalendar;

import static org.jfree.date.DayDate.*;

public class BobsDayDateTest extends TestCase {

    public void testIsValidWeekdayCode() throws Exception {
        for (int day = 1; day <= 7; day++)
            Assert.assertTrue(DayDate.isValidWeekdayCode(day));
        Assert.assertFalse(DayDate.isValidWeekdayCode(0));
        Assert.assertFalse(DayDate.isValidWeekdayCode(8));
    }

    public void testStringToWeekdayCode() throws Exception {

        Assert.assertEquals(-1, stringToWeekdayCode("Hello"));
        Assert.assertEquals(DayDate.MONDAY, stringToWeekdayCode("Monday"));
        Assert.assertEquals(DayDate.MONDAY, stringToWeekdayCode("Mon"));
        assertEquals(MONDAY, stringToWeekdayCode("monday"));
        assertEquals(MONDAY, stringToWeekdayCode("MONDAY"));
        assertEquals(MONDAY, stringToWeekdayCode("mon"));

        Assert.assertEquals(DayDate.TUESDAY, stringToWeekdayCode("Tuesday"));
        Assert.assertEquals(DayDate.TUESDAY, stringToWeekdayCode("Tue"));
        assertEquals(TUESDAY, stringToWeekdayCode("tuesday"));
        assertEquals(TUESDAY, stringToWeekdayCode("TUESDAY"));
        assertEquals(TUESDAY, stringToWeekdayCode("tue"));
        //assertEquals(TUESDAY, stringToWeekdayCode("tues"));

        Assert.assertEquals(DayDate.WEDNESDAY, stringToWeekdayCode("Wednesday"));
        Assert.assertEquals(DayDate.WEDNESDAY, stringToWeekdayCode("Wed"));
        assertEquals(WEDNESDAY, stringToWeekdayCode("wednesday"));
        assertEquals(WEDNESDAY, stringToWeekdayCode("WEDNESDAY"));
        assertEquals(WEDNESDAY, stringToWeekdayCode("wed"));

        Assert.assertEquals(DayDate.THURSDAY, stringToWeekdayCode("Thursday"));
        Assert.assertEquals(DayDate.THURSDAY, stringToWeekdayCode("Thu"));
        assertEquals(THURSDAY, stringToWeekdayCode("thursday"));
        assertEquals(THURSDAY, stringToWeekdayCode("THURSDAY"));
        assertEquals(THURSDAY, stringToWeekdayCode("thu"));
        //assertEquals(THURSDAY, stringToWeekdayCode("thurs"));

        Assert.assertEquals(DayDate.FRIDAY, stringToWeekdayCode("Friday"));
        Assert.assertEquals(DayDate.FRIDAY, stringToWeekdayCode("Fri"));
        assertEquals(FRIDAY, stringToWeekdayCode("friday"));
        assertEquals(FRIDAY, stringToWeekdayCode("FRIDAY"));
        assertEquals(FRIDAY, stringToWeekdayCode("fri"));

        Assert.assertEquals(DayDate.SATURDAY, stringToWeekdayCode("Saturday"));
        Assert.assertEquals(DayDate.SATURDAY, stringToWeekdayCode("Sat"));
        assertEquals(SATURDAY, stringToWeekdayCode("saturday"));
        assertEquals(SATURDAY, stringToWeekdayCode("SATURDAY"));
        assertEquals(SATURDAY, stringToWeekdayCode("sat"));

        Assert.assertEquals(DayDate.SUNDAY, stringToWeekdayCode("Sunday"));
        Assert.assertEquals(DayDate.SUNDAY, stringToWeekdayCode("Sun"));
        assertEquals(SUNDAY, stringToWeekdayCode("sunday"));
        assertEquals(SUNDAY, stringToWeekdayCode("SUNDAY"));
        assertEquals(SUNDAY, stringToWeekdayCode("sun"));
    }

    public void testWeekdayCodeToString() throws Exception {
        Assert.assertEquals("Sunday", DayDate.weekdayCodeToString(DayDate.SUNDAY));
        Assert.assertEquals("Monday", DayDate.weekdayCodeToString(DayDate.MONDAY));
        Assert.assertEquals("Tuesday", DayDate.weekdayCodeToString(DayDate.TUESDAY));
        Assert.assertEquals("Wednesday", DayDate.weekdayCodeToString(DayDate.WEDNESDAY));
        Assert.assertEquals("Thursday", DayDate.weekdayCodeToString(DayDate.THURSDAY));
        Assert.assertEquals("Friday", DayDate.weekdayCodeToString(DayDate.FRIDAY));
        Assert.assertEquals("Saturday", DayDate.weekdayCodeToString(DayDate.SATURDAY));
    }

    public void testMonthToQuarter() throws Exception {
        Assert.assertEquals(1, DayDate.monthCodeToQuarter(Month.make(1)));
        Assert.assertEquals(1, DayDate.monthCodeToQuarter(Month.make(2)));
        Assert.assertEquals(1, DayDate.monthCodeToQuarter(Month.make(3)));
        Assert.assertEquals(2, DayDate.monthCodeToQuarter(Month.make(4)));
        Assert.assertEquals(2, DayDate.monthCodeToQuarter(Month.make(5)));
        Assert.assertEquals(2, DayDate.monthCodeToQuarter(Month.make(6)));
        Assert.assertEquals(3, DayDate.monthCodeToQuarter(Month.make(7)));
        Assert.assertEquals(3, DayDate.monthCodeToQuarter(Month.make(8)));
        Assert.assertEquals(3, DayDate.monthCodeToQuarter(Month.make(9)));
        Assert.assertEquals(4, DayDate.monthCodeToQuarter(Month.make(10)));
        Assert.assertEquals(4, DayDate.monthCodeToQuarter(Month.make(11)));
        Assert.assertEquals(4, DayDate.monthCodeToQuarter(Month.make(12)));

        try {
            DayDate.monthCodeToQuarter(Month.make(-1));
        } catch (IllegalArgumentException e) {
        }
    }

    public void testMonthCodeToString() throws Exception {
        Assert.assertEquals("January", DayDate.monthCodeToString(Month.JANUARY));
        Assert.assertEquals("February", DayDate.monthCodeToString(Month.FEBRUARY));
        Assert.assertEquals("March", DayDate.monthCodeToString(Month.MARCH));
        Assert.assertEquals("April", DayDate.monthCodeToString(Month.APRIL));
        Assert.assertEquals("May", DayDate.monthCodeToString(Month.MAY));
        Assert.assertEquals("June", DayDate.monthCodeToString(Month.JUNE));
        Assert.assertEquals("July", DayDate.monthCodeToString(Month.JULY));
        Assert.assertEquals("August", DayDate.monthCodeToString(Month.AUGUST));
        Assert.assertEquals("September", DayDate.monthCodeToString(Month.SEPTEMBER));
        Assert.assertEquals("October", DayDate.monthCodeToString(Month.OCTOBER));
        Assert.assertEquals("November", DayDate.monthCodeToString(Month.NOVEMBER));
        Assert.assertEquals("December", DayDate.monthCodeToString(Month.DECEMBER));

        Assert.assertEquals("Jan", DayDate.monthCodeToString(Month.JANUARY, true));
        Assert.assertEquals("Feb", DayDate.monthCodeToString(Month.FEBRUARY, true));
        Assert.assertEquals("Mar", DayDate.monthCodeToString(Month.MARCH, true));
        Assert.assertEquals("Apr", DayDate.monthCodeToString(Month.APRIL, true));
        Assert.assertEquals("May", DayDate.monthCodeToString(Month.MAY, true));
        Assert.assertEquals("Jun", DayDate.monthCodeToString(Month.JUNE, true));
        Assert.assertEquals("Jul", DayDate.monthCodeToString(Month.JULY, true));
        Assert.assertEquals("Aug", DayDate.monthCodeToString(Month.AUGUST, true));
        Assert.assertEquals("Sep", DayDate.monthCodeToString(Month.SEPTEMBER, true));
        Assert.assertEquals("Oct", DayDate.monthCodeToString(Month.OCTOBER, true));
        Assert.assertEquals("Nov", DayDate.monthCodeToString(Month.NOVEMBER, true));
        Assert.assertEquals("Dec", DayDate.monthCodeToString(Month.DECEMBER, true));

        try {
            DayDate.monthCodeToString(Month.make(-1));
        } catch (IllegalArgumentException e) {
        }

    }

    public void testStringToMonthCode() throws Exception {
        Assert.assertEquals(Month.JANUARY.index, DayDate.stringToMonthCode("1"));
        Assert.assertEquals(Month.FEBRUARY.index, DayDate.stringToMonthCode("2"));
        Assert.assertEquals(Month.MARCH.index, DayDate.stringToMonthCode("3"));
        Assert.assertEquals(Month.APRIL.index, DayDate.stringToMonthCode("4"));
        Assert.assertEquals(Month.MAY.index, DayDate.stringToMonthCode("5"));
        Assert.assertEquals(Month.JUNE.index, DayDate.stringToMonthCode("6"));
        Assert.assertEquals(Month.JULY.index, DayDate.stringToMonthCode("7"));
        Assert.assertEquals(Month.AUGUST.index, DayDate.stringToMonthCode("8"));
        Assert.assertEquals(Month.SEPTEMBER.index, DayDate.stringToMonthCode("9"));
        Assert.assertEquals(Month.OCTOBER.index, DayDate.stringToMonthCode("10"));
        Assert.assertEquals(Month.NOVEMBER.index, DayDate.stringToMonthCode("11"));
        Assert.assertEquals(Month.DECEMBER.index, DayDate.stringToMonthCode("12"));

        assertEquals(-1, stringToMonthCode("0"));
        assertEquals(-1, stringToMonthCode("13"));

        Assert.assertEquals(-1, DayDate.stringToMonthCode("Hello"));

        for (int m = 1; m <= 12; m++) {
            Assert.assertEquals(m, DayDate.stringToMonthCode(DayDate.monthCodeToString(Month.make(m), false)));
            Assert.assertEquals(m, DayDate.stringToMonthCode(DayDate.monthCodeToString(Month.make(m), true)));
        }

        assertEquals(1, stringToMonthCode("jan"));
        assertEquals(2, stringToMonthCode("feb"));
        assertEquals(3, stringToMonthCode("mar"));
        assertEquals(4, stringToMonthCode("apr"));
        assertEquals(5, stringToMonthCode("may"));
        assertEquals(6, stringToMonthCode("jun"));
        assertEquals(7, stringToMonthCode("jul"));
        assertEquals(8, stringToMonthCode("aug"));
        assertEquals(9, stringToMonthCode("sep"));
        assertEquals(10, stringToMonthCode("oct"));
        assertEquals(11, stringToMonthCode("nov"));
        assertEquals(12, stringToMonthCode("dec"));

        assertEquals(1, stringToMonthCode("JAN"));
        assertEquals(2, stringToMonthCode("FEB"));
        assertEquals(3, stringToMonthCode("MAR"));
        assertEquals(4, stringToMonthCode("APR"));
        assertEquals(5, stringToMonthCode("MAY"));
        assertEquals(6, stringToMonthCode("JUN"));
        assertEquals(7, stringToMonthCode("JUL"));
        assertEquals(8, stringToMonthCode("AUG"));
        assertEquals(9, stringToMonthCode("SEP"));
        assertEquals(10, stringToMonthCode("OCT"));
        assertEquals(11, stringToMonthCode("NOV"));
        assertEquals(12, stringToMonthCode("DEC"));

        assertEquals(1, stringToMonthCode("january"));
        assertEquals(2, stringToMonthCode("february"));
        assertEquals(3, stringToMonthCode("march"));
        assertEquals(4, stringToMonthCode("april"));
        assertEquals(5, stringToMonthCode("may"));
        assertEquals(6, stringToMonthCode("june"));
        assertEquals(7, stringToMonthCode("july"));
        assertEquals(8, stringToMonthCode("august"));
        assertEquals(9, stringToMonthCode("september"));
        assertEquals(10, stringToMonthCode("october"));
        assertEquals(11, stringToMonthCode("november"));
        assertEquals(12, stringToMonthCode("december"));

        assertEquals(1, stringToMonthCode("JANUARY"));
        assertEquals(2, stringToMonthCode("FEBRUARY"));
        assertEquals(3, stringToMonthCode("MAR"));
        assertEquals(4, stringToMonthCode("APRIL"));
        assertEquals(5, stringToMonthCode("MAY"));
        assertEquals(6, stringToMonthCode("JUNE"));
        assertEquals(7, stringToMonthCode("JULY"));
        assertEquals(8, stringToMonthCode("AUGUST"));
        assertEquals(9, stringToMonthCode("SEPTEMBER"));
        assertEquals(10, stringToMonthCode("OCTOBER"));
        assertEquals(11, stringToMonthCode("NOVEMBER"));
        assertEquals(12, stringToMonthCode("DECEMBER"));
    }

    public void testIsValidWeekInMonthCode() throws Exception {
        for (int w = 0; w <= 4; w++) {
            Assert.assertTrue(DayDate.isValidWeekInMonthCode(w));
        }
        Assert.assertFalse(DayDate.isValidWeekInMonthCode(5));
    }

    public void testIsLeapYear() throws Exception {
        Assert.assertFalse(DayDate.isLeapYear(1900));
        Assert.assertFalse(DayDate.isLeapYear(1901));
        Assert.assertFalse(DayDate.isLeapYear(1902));
        Assert.assertFalse(DayDate.isLeapYear(1903));
        Assert.assertTrue(DayDate.isLeapYear(1904));
        Assert.assertTrue(DayDate.isLeapYear(1908));
        Assert.assertFalse(DayDate.isLeapYear(1955));
        Assert.assertTrue(DayDate.isLeapYear(1964));
        Assert.assertTrue(DayDate.isLeapYear(1980));
        Assert.assertTrue(DayDate.isLeapYear(2000));
        Assert.assertFalse(DayDate.isLeapYear(2001));
        Assert.assertFalse(DayDate.isLeapYear(2100));
    }

    public void testLeapYearCount() throws Exception {
        Assert.assertEquals(0, DayDate.leapYearCount(1900));
        Assert.assertEquals(0, DayDate.leapYearCount(1901));
        Assert.assertEquals(0, DayDate.leapYearCount(1902));
        Assert.assertEquals(0, DayDate.leapYearCount(1903));
        Assert.assertEquals(1, DayDate.leapYearCount(1904));
        Assert.assertEquals(1, DayDate.leapYearCount(1905));
        Assert.assertEquals(1, DayDate.leapYearCount(1906));
        Assert.assertEquals(1, DayDate.leapYearCount(1907));
        Assert.assertEquals(2, DayDate.leapYearCount(1908));
        Assert.assertEquals(24, DayDate.leapYearCount(1999));
        Assert.assertEquals(25, DayDate.leapYearCount(2001));
        Assert.assertEquals(49, DayDate.leapYearCount(2101));
        Assert.assertEquals(73, DayDate.leapYearCount(2201));
        Assert.assertEquals(97, DayDate.leapYearCount(2301));
        Assert.assertEquals(122, DayDate.leapYearCount(2401));
    }

    public void testLastDayOfMonth() throws Exception {
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.JANUARY, 1901));
        Assert.assertEquals(28, DayDate.lastDayOfMonth(Month.FEBRUARY, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.MARCH, 1901));
        Assert.assertEquals(30, DayDate.lastDayOfMonth(Month.APRIL, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.MAY, 1901));
        Assert.assertEquals(30, DayDate.lastDayOfMonth(Month.JUNE, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.JULY, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.AUGUST, 1901));
        Assert.assertEquals(30, DayDate.lastDayOfMonth(Month.SEPTEMBER, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.OCTOBER, 1901));
        Assert.assertEquals(30, DayDate.lastDayOfMonth(Month.NOVEMBER, 1901));
        Assert.assertEquals(31, DayDate.lastDayOfMonth(Month.DECEMBER, 1901));
        Assert.assertEquals(29, DayDate.lastDayOfMonth(Month.FEBRUARY, 1904));
    }

    public void testAddDays() throws Exception {
        DayDate newYears = d(1, Month.JANUARY, 1900);
        Assert.assertEquals(d(2, Month.JANUARY, 1900), DayDate.addDays(1, newYears));
        Assert.assertEquals(d(1, Month.FEBRUARY, 1900), DayDate.addDays(31, newYears));
        Assert.assertEquals(d(1, Month.JANUARY, 1901), DayDate.addDays(365, newYears));
        Assert.assertEquals(d(31, Month.DECEMBER, 1904), DayDate.addDays(5 * 365, newYears));
    }

    private static SpreadsheetDate d(int day, Month month, int year) {
        return new
                SpreadsheetDate(day, month, year);
    }

    public void testAddMonths() throws Exception {
        Assert.assertEquals(d(1, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(1, Month.JANUARY, 1900)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(31, Month.JANUARY, 1900)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(30, Month.JANUARY, 1900)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(29, Month.JANUARY, 1900)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(28, Month.JANUARY, 1900)));
        Assert.assertEquals(d(27, Month.FEBRUARY, 1900), DayDate.addMonths(1, d(27, Month.JANUARY, 1900)));

        Assert.assertEquals(d(30, Month.JUNE, 1900), DayDate.addMonths(5, d(31, Month.JANUARY, 1900)));
        Assert.assertEquals(d(30, Month.JUNE, 1901), DayDate.addMonths(17, d(31, Month.JANUARY, 1900)));

        Assert.assertEquals(d(29, Month.FEBRUARY, 1904), DayDate.addMonths(49, d(31, Month.JANUARY, 1900)));

    }

    public void testAddYears() throws Exception {
        Assert.assertEquals(d(1, Month.JANUARY, 1901), DayDate.addYears(1, d(1, Month.JANUARY, 1900)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1905), DayDate.addYears(1, d(29, Month.FEBRUARY, 1904)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1905), DayDate.addYears(1, d(28, Month.FEBRUARY, 1904)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 1904), DayDate.addYears(1, d(28, Month.FEBRUARY, 1903)));
    }

    public void testGetPreviousDayOfWeek() throws Exception {
        Assert.assertEquals(d(24, Month.FEBRUARY, 2006), DayDate.getPreviousDayOfWeek(DayDate.FRIDAY, d(1, Month.MARCH, 2006)));
        Assert.assertEquals(d(22, Month.FEBRUARY, 2006), DayDate.getPreviousDayOfWeek(DayDate.WEDNESDAY, d(1, Month.MARCH, 2006)));
        Assert.assertEquals(d(29, Month.FEBRUARY, 2004), DayDate.getPreviousDayOfWeek(DayDate.SUNDAY, d(3, Month.MARCH, 2004)));
        Assert.assertEquals(d(29, Month.DECEMBER, 2004), DayDate.getPreviousDayOfWeek(DayDate.WEDNESDAY, d(5, Month.JANUARY, 2005)));

        try {
            DayDate.getPreviousDayOfWeek(-1, d(1, Month.JANUARY, 2006));
            Assert.fail("Invalid day of week code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testGetFollowingDayOfWeek() throws Exception {
        assertEquals(d(1, Month.JANUARY, 2005), getFollowingDayOfWeek(SATURDAY, d(25, Month.DECEMBER, 2004)));
        Assert.assertEquals(d(1, Month.JANUARY, 2005), DayDate.getFollowingDayOfWeek(DayDate.SATURDAY, d(26, Month.DECEMBER, 2004)));
        Assert.assertEquals(d(3, Month.MARCH, 2004), DayDate.getFollowingDayOfWeek(DayDate.WEDNESDAY, d(28, Month.FEBRUARY, 2004)));

        try {
            DayDate.getFollowingDayOfWeek(-1, d(1, Month.JANUARY, 2006));
            Assert.fail("Invalid day of week code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testGetNearestDayOfWeek() throws Exception {
        Assert.assertEquals(d(16, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(16, Month.APRIL, 2006)));
        Assert.assertEquals(d(16, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(17, Month.APRIL, 2006)));
        Assert.assertEquals(d(16, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(18, Month.APRIL, 2006)));
        Assert.assertEquals(d(16, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(19, Month.APRIL, 2006)));
        Assert.assertEquals(d(23, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(23, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(23, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SUNDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(MONDAY, d(16, Month.APRIL, 2006)));
        Assert.assertEquals(d(17, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(17, Month.APRIL, 2006)));
        Assert.assertEquals(d(17, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(18, Month.APRIL, 2006)));
        Assert.assertEquals(d(17, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(19, Month.APRIL, 2006)));
        Assert.assertEquals(d(17, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(24, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(24, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.MONDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(17, Month.APRIL, 2006)));
        Assert.assertEquals(d(18, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.TUESDAY, d(18, Month.APRIL, 2006)));
        Assert.assertEquals(d(18, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.TUESDAY, d(19, Month.APRIL, 2006)));
        Assert.assertEquals(d(18, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.TUESDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(18, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.TUESDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(25, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.TUESDAY, d(22, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(18, Month.APRIL, 2006)));
        Assert.assertEquals(d(19, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.WEDNESDAY, d(19, Month.APRIL, 2006)));
        Assert.assertEquals(d(19, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.WEDNESDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(19, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.WEDNESDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(19, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.WEDNESDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(13, Month.APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(19, Month.APRIL, 2006)));
        Assert.assertEquals(d(20, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.THURSDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(20, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.THURSDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(20, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.THURSDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(14, Month.APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(14, Month.APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(20, Month.APRIL, 2006)));
        Assert.assertEquals(d(21, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.FRIDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(21, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.FRIDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(21, Month.APRIL, 2006)));
        Assert.assertEquals(d(22, Month.APRIL, 2006), DayDate.getNearestDayOfWeek(DayDate.SATURDAY, d(22, Month.APRIL, 2006)));

        try {
            DayDate.getNearestDayOfWeek(-1, d(1, Month.JANUARY, 2006));
            Assert.fail("Invalid day of week code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }


    public void testEndOfCurrentMonth() throws Exception {
        DayDate d = DayDate.createInstance(2);
        Assert.assertEquals(d(31, Month.JANUARY, 2006), d.getEndOfCurrentMonth(d(1, Month.JANUARY, 2006)));
        Assert.assertEquals(d(28, Month.FEBRUARY, 2006), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY, 2006)));
        Assert.assertEquals(d(31, Month.MARCH, 2006), d.getEndOfCurrentMonth(d(1, Month.MARCH, 2006)));
        Assert.assertEquals(d(30, Month.APRIL, 2006), d.getEndOfCurrentMonth(d(1, Month.APRIL, 2006)));
        Assert.assertEquals(d(31, Month.MAY, 2006), d.getEndOfCurrentMonth(d(1, Month.MAY, 2006)));
        Assert.assertEquals(d(30, Month.JUNE, 2006), d.getEndOfCurrentMonth(d(1, Month.JUNE, 2006)));
        Assert.assertEquals(d(31, Month.JULY, 2006), d.getEndOfCurrentMonth(d(1, Month.JULY, 2006)));
        Assert.assertEquals(d(31, Month.AUGUST, 2006), d.getEndOfCurrentMonth(d(1, Month.AUGUST, 2006)));
        Assert.assertEquals(d(30, Month.SEPTEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.SEPTEMBER, 2006)));
        Assert.assertEquals(d(31, Month.OCTOBER, 2006), d.getEndOfCurrentMonth(d(1, Month.OCTOBER, 2006)));
        Assert.assertEquals(d(30, Month.NOVEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.NOVEMBER, 2006)));
        Assert.assertEquals(d(31, Month.DECEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.DECEMBER, 2006)));
        Assert.assertEquals(d(29, Month.FEBRUARY, 2008), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY, 2008)));
    }

    public void testWeekInMonthToString() throws Exception {
        Assert.assertEquals("First", DayDate.weekInMonthToString(DayDate.FIRST_WEEK_IN_MONTH));
        Assert.assertEquals("Second", DayDate.weekInMonthToString(DayDate.SECOND_WEEK_IN_MONTH));
        Assert.assertEquals("Third", DayDate.weekInMonthToString(DayDate.THIRD_WEEK_IN_MONTH));
        Assert.assertEquals("Fourth", DayDate.weekInMonthToString(DayDate.FOURTH_WEEK_IN_MONTH));
        Assert.assertEquals("Last", DayDate.weekInMonthToString(DayDate.LAST_WEEK_IN_MONTH));

        try {
            weekInMonthToString(-1);
            fail("Invalid week code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testRelativeToString() throws Exception {
        Assert.assertEquals("Preceding", DayDate.relativeToString(DayDate.PRECEDING));
        Assert.assertEquals("Nearest", DayDate.relativeToString(DayDate.NEAREST));
        Assert.assertEquals("Following", DayDate.relativeToString(DayDate.FOLLOWING));

        try {
            relativeToString(-1000);
            fail("Invalid relative code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateInstanceFromDDMMYYY() throws Exception {
        DayDate date = DayDate.createInstance(1, Month.JANUARY, 1900);
        Assert.assertEquals(1, date.getDayOfMonth());
        Assert.assertEquals(Month.JANUARY, Month.make(date.getMonth()));
        Assert.assertEquals(1900, date.getYYYY());
        Assert.assertEquals(2, date.toSerial());
    }

    public void testCreateInstanceFromSerial() throws Exception {
        Assert.assertEquals(d(1, Month.JANUARY, 1900), DayDate.createInstance(2));
        Assert.assertEquals(d(1, Month.JANUARY, 1901), DayDate.createInstance(367));
    }

    public void testCreateInstanceFromJavaDate() throws Exception {
        Assert.assertEquals(d(1, Month.JANUARY, 1900),
                DayDate.createInstance(new GregorianCalendar(1900, 0, 1).getTime()));
        Assert.assertEquals(d(1, Month.JANUARY, 2006),
                DayDate.createInstance(new GregorianCalendar(2006, 0, 1).getTime()));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BobsDayDateTest.class);
    }
}