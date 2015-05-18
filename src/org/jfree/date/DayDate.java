package org.jfree.date;/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ---------------
 * org.jfree.date.DayDate.java
 * ---------------
 * (C) Copyright 2001-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: org.jfree.date.DayDate.java,v 1.7 2005/11/03 09:25:17 mungady Exp $
 */

import java.io.Serializable;
import java.text.*;
import java.util.*;

/**
 * An abstract class that defines our requirements for manipulating dates,
 * without tying down a particular implementation.
 * <p>
 * Requirement 1 : match at least what Excel does for dates;
 * Requirement 2 : class is immutable;
 * <p>
 * Why not just use java.util.Date?  We will, when it makes sense.  At times,
 * java.util.Date can be *too* precise - it represents an instant in time,
 * accurate to 1/1000th of a second (with the date itself depending on the
 * time-zone).  Sometimes we just want to represent a particular day (e.g. 21
 * January 2015) without concerning ourselves about the time of day, or the
 * time-zone, or anything else.  That's what we've defined org.jfree.date.DayDate for.
 * <p>
 * You can call getInstance() to get a concrete subclass of org.jfree.date.DayDate,
 * without worrying about the exact implementation.
 *
 * @author David Gilbert
 */
public abstract class DayDate implements Comparable,
        Serializable {

    public static DateFormatSymbols
            DATE_FORMAT_SYMBOLS = new SimpleDateFormat().getDateFormatSymbols();

    /**
     * The lowest year value supported by this date format.
     */
    public static int MINIMUM_YEAR_SUPPORTED = 1900;

    /**
     * The highest year value supported by this date format.
     */
    public static int MAXIMUM_YEAR_SUPPORTED = 9999;

    private static int[] LAST_DAY_OF_MONTH =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public enum WeekInMonth {
        FIRST(1),
        SECOND(2),
        THIRD(3),
        FOURTH(4),
        LAST(0);

        WeekInMonth(int index) {
            this.index = index;
        }

        public static WeekInMonth make(int weekInMonthIndex) {
            for (WeekInMonth wim : WeekInMonth.values()) {
                if(wim.index == weekInMonthIndex)
                    return wim;
            }
            throw new IllegalArgumentException("Invalid weekinmonth index " + weekInMonthIndex);
        }
        public int index;
    }

    public enum DateInterval {
        CLOSED(3),
        CLOSED_LEFT(1),
        CLOSED_RIGHT(2),
        OPEN(0);

        DateInterval(int index) {
            this.index = index;
        }

        public static DateInterval make(int dateIntervalIndex) {
            for (DateInterval di : DateInterval.values()) {
                if(di.index == dateIntervalIndex)
                    return di;
            }
            throw new IllegalArgumentException("Invalid dateinterval index " + dateIntervalIndex);
        }
        public int index;
    }

    public enum WeekdayRange {
        LAST(-1),
        NEXT(1),
        NEAREST(0);

        WeekdayRange(int index) {
            this.index = index;
        }

        public static WeekdayRange make(int weekdayRangeIndex) {
            for (WeekdayRange wri : WeekdayRange.values()) {
                if(wri.index == weekdayRangeIndex)
                    return wri;
            }
            throw new IllegalArgumentException("Invalid weekdayrange index " + weekdayRangeIndex);
        }
        public int index;
    }

    /**
     * Returns an array of month names.
     *
     * @return an array of month names.
     */
    public static String[] getMonthNames() {
        return DATE_FORMAT_SYMBOLS.getMonths();
    }

    public static boolean isLeapYear(int year) {
        boolean fourth = year % 4 == 0;
        boolean hundreth = year % 100 == 0;
        boolean fourhundreth = year % 400 == 0;
        return fourth && (!hundreth || fourhundreth);
    }

    /**
     * Returns the number of the last day of the month, taking into account
     * leap years.
     *
     * @param month the month.
     * @param yyyy  the year (in the range 1900 to 9999).
     * @return the number of the last day of the month.
     */
    public static int lastDayOfMonth(Month month, int yyyy) {

        int result = LAST_DAY_OF_MONTH[month.index];
        if (month != Month.FEBRUARY) {
            return result;
        } else if (isLeapYear(yyyy)) {
            return result + 1;
        } else {
            return result;
        }

    }

    /**
     * Creates a new date by adding the specified number of days to the base
     * date.
     *
     * @param days the number of days to add (can be negative).
     * @param base the base date.
     * @return a new date.
     */
    public static DayDate addDays(int days, DayDate base) {

        int serialDayNumber = base.toSerial() + days;
        return DayDateFactory.makeDate(serialDayNumber);

    }

    /**
     * Creates a new date by adding the specified number of months to the base
     * date.
     * <p>
     * If the base date is close to the end of the month, the day on the result
     * may be adjusted slightly:  31 May + 1 month = 30 June.
     *
     * @param months the number of months to add (can be negative).
     * @param base   the base date.
     * @return a new date.
     */
    public static DayDate addMonths(int months,
                                    DayDate base) {

        int yy = (12 * base.getYYYY() + base.getMonth() + months - 1)
                / 12;
        int mm = (12 * base.getYYYY() + base.getMonth() + months - 1)
                % 12 + 1;
        int dd = Math.min(
                base.getDayOfMonth(), DayDate.lastDayOfMonth(Month.make(mm), yy)
        );
        return DayDateFactory.makeDate(dd, Month.make(mm), yy);

    }

    /**
     * Creates a new date by adding the specified number of years to the base
     * date.
     *
     * @param years the number of years to add (can be negative).
     * @param base  the base date.
     * @return A new date.
     */
    public static DayDate addYears(int years, DayDate base) {

        int baseY = base.getYYYY();
        int baseM = base.getMonth();
        int baseD = base.getDayOfMonth();

        int targetY = baseY + years;
        int targetD = Math.min(
                baseD, DayDate.lastDayOfMonth(Month.make(baseM), targetY)
        );

        return DayDateFactory.makeDate(targetD, Month.make(baseM), targetY);

    }

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE the base date.
     *
     * @param targetWeekday a code for the target day-of-the-week.
     * @param base          the base date.
     * @return the latest date that falls on the specified day-of-the-week and
     * is BEFORE the base date.
     */
    public static DayDate getPreviousDayOfWeek(Day targetWeekday,
                                               DayDate base) {

        // find the date...
        int adjust;
        int baseDOW = base.getDayOfWeek();
        if (baseDOW > targetWeekday.index) {
            adjust = Math.min(0, targetWeekday.index - baseDOW);
        } else {
            adjust = -7 + Math.max(0, targetWeekday.index - baseDOW);
        }

        return DayDate.addDays(adjust, base);

    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER the base date.
     *
     * @param targetWeekday a code for the target day-of-the-week.
     * @param base          the base date.
     * @return the earliest date that falls on the specified day-of-the-week
     * and is AFTER the base date.
     */
    public static DayDate getFollowingDayOfWeek(Day targetWeekday,
                                                DayDate base) {

        // find the date...
        int adjust;
        int baseDOW = base.getDayOfWeek();
        if (baseDOW >= targetWeekday.index) {
            adjust = 7 + Math.min(0, targetWeekday.index - baseDOW);
        } else {
            adjust = Math.max(0, targetWeekday.index - baseDOW);
        }

        return DayDate.addDays(adjust, base);
    }

    /**
     * Returns the date that falls on the specified day-of-the-week and is
     * CLOSEST to the base date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @param base      the base date.
     * @return the date that falls on the specified day-of-the-week and is
     * CLOSEST to the base date.
     */
    public static DayDate getNearestDayOfWeek(Day targetDOW,
                                              DayDate base) {

        // find the date...
        int delta = targetDOW.index - base.getDayOfWeek();
        int positivedelta = delta + 7;
        int adjust = positivedelta % 7;
        if (adjust > 3) {
            adjust -= 7;
        }
        return DayDate.addDays(adjust, base);

    }

    /**
     * Rolls the date forward to the last day of the month.
     *
     * @param base the base date.
     * @return a new serial date.
     */
    public DayDate getEndOfCurrentMonth(DayDate base) {
        int last = DayDate.lastDayOfMonth(
                Month.make(base.getMonth()), base.getYYYY()
        );
        return DayDateFactory.makeDate(last, Month.make(base.getMonth()), base.getYYYY());
    }

    /**
     * Returns a string corresponding to the week-in-the-month code.
     * <p>
     * Need to find a better approach.
     *
     * @param count an integer code representing the week-in-the-month.
     * @return a string corresponding to the week-in-the-month code.
     */
    public static String weekInMonthToString(int count) throws IllegalArgumentException {

        switch (count) {
            case DayDate.FIRST_WEEK_IN_MONTH:
                return "First";
            case DayDate.SECOND_WEEK_IN_MONTH:
                return "Second";
            case DayDate.THIRD_WEEK_IN_MONTH:
                return "Third";
            case DayDate.FOURTH_WEEK_IN_MONTH:
                return "Fourth";
            case DayDate.LAST_WEEK_IN_MONTH:
                return "Last";
            default:
                throw new IllegalArgumentException();
        }

    }

    /**
     * Returns a string representing the supplied 'relative'.
     * <p>
     * Need to find a better approach.
     *
     * @param relative a constant representing the 'relative'.
     * @return a string representing the supplied 'relative'.
     */
    public static String relativeToString(int relative) throws IllegalArgumentException {

        switch (relative) {
            case DayDate.PRECEDING:
                return "Preceding";
            case DayDate.NEAREST:
                return "Nearest";
            case DayDate.FOLLOWING:
                return "Following";
            default:
                throw new IllegalArgumentException();
        }

    }

    /**
     * Factory method that returns an instance of some concrete subclass of
     * {@link DayDate}.
     *
     * @param day   the day (1-31).
     * @param month the month (1-12).
     * @param yyyy  the year (in the range 1900 to 9999).
     * @return An instance of {@link DayDate}.
     */

    /**
     * Returns the serial number for the date, where 1 January 1900 = 2 (this
     * corresponds, almost, to the numbering system used in Microsoft Excel for
     * Windows and Lotus 1-2-3).
     *
     * @return the serial number for the date.
     */
    public abstract int toSerial();

    /**
     * Returns a java.util.Date.  Since java.util.Date has more precision than
     * org.jfree.date.DayDate, we need to define a convention for the 'time of day'.
     *
     * @return this as <code>java.util.Date</code>.
     */
    public abstract java.util.Date toDate();

    /**
     * Converts the date to a string.
     *
     * @return a string representation of the date.
     */
    public String toString() {
        return getDayOfMonth() + "-" + DayDate.monthCodeToString(Month.make(getMonth()))
                + "-" + getYYYY();
    }

    /**
     * Returns the year (assume a valid range of 1900 to 9999).
     *
     * @return the year.
     */
    public abstract int getYYYY();

    /**
     * Returns the month (January = 1, February = 2, March = 3).
     *
     * @return the month of the year.
     */
    public abstract int getMonth();

    /**
     * Returns the day of the month.
     *
     * @return the day of the month.
     */
    public abstract int getDayOfMonth();

    /**
     * Returns the day of the week.
     *
     * @return the day of the week.
     */
    public abstract int getDayOfWeek();

    /**
     * Returns the difference (in days) between this date and the specified
     * 'other' date.
     * <p>
     * The result is positive if this date is after the 'other' date and
     * negative if it is before the 'other' date.
     *
     * @param other the date being compared to.
     * @return the difference between this and the other date.
     */
    public abstract int compare(DayDate other);

    /**
     * Returns true if this org.jfree.date.DayDate represents the same date as the
     * specified org.jfree.date.DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this org.jfree.date.DayDate represents the same date as
     * the specified org.jfree.date.DayDate.
     */
    public abstract boolean isOn(DayDate other);

    /**
     * Returns true if this org.jfree.date.DayDate represents an earlier date compared to
     * the specified org.jfree.date.DayDate.
     *
     * @param other The date being compared to.
     * @return <code>true</code> if this org.jfree.date.DayDate represents an earlier date
     * compared to the specified org.jfree.date.DayDate.
     */
    public abstract boolean isBefore(DayDate other);

    /**
     * Returns true if this org.jfree.date.DayDate represents the same date as the
     * specified org.jfree.date.DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true<code> if this org.jfree.date.DayDate represents the same date
     * as the specified org.jfree.date.DayDate.
     */
    public abstract boolean isOnOrBefore(DayDate other);

    /**
     * Returns true if this org.jfree.date.DayDate represents the same date as the
     * specified org.jfree.date.DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this org.jfree.date.DayDate represents the same date
     * as the specified org.jfree.date.DayDate.
     */
    public abstract boolean isAfter(DayDate other);

    /**
     * Returns true if this org.jfree.date.DayDate represents the same date as the
     * specified org.jfree.date.DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this org.jfree.date.DayDate represents the same date
     * as the specified org.jfree.date.DayDate.
     */
    public abstract boolean isOnOrAfter(DayDate other);

    /**
     * Returns <code>true</code> if this {@link DayDate} is within the
     * specified range (INCLUSIVE).  The date order of d1 and d2 is not
     * important.
     *
     * @param d1 a boundary date for the range.
     * @param d2 the other boundary date for the range.
     * @return A boolean.
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2);

    /**
     * Returns <code>true</code> if this {@link DayDate} is within the
     * specified range (caller specifies whether or not the end-points are
     * included).  The date order of d1 and d2 is not important.
     *
     * @param d1      a boundary date for the range.
     * @param d2      the other boundary date for the range.
     * @param include a code that controls whether or not the start and end
     *                dates are included in the range.
     * @return A boolean.
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2,
                                      int include);

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE this date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the latest date that falls on the specified day-of-the-week and
     * is BEFORE this date.
     */
    public DayDate getPreviousDayOfWeek(Day targetDOW) {
        return getPreviousDayOfWeek(targetDOW, this);
    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER this date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the earliest date that falls on the specified day-of-the-week
     * and is AFTER this date.
     */
    public DayDate getFollowingDayOfWeek(Day targetDOW) {
        return getFollowingDayOfWeek(targetDOW, this);
    }

    /**
     * Returns the nearest date that falls on the specified day-of-the-week.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the nearest date that falls on the specified day-of-the-week.
     */
    public DayDate getNearestDayOfWeek(Day targetDOW) {
        return getNearestDayOfWeek(targetDOW, this);
    }

}
