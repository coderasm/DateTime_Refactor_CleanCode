package org.jfree.date.junit;/* ========================================================================
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
 * --------------------
 * org.jfree.date.junit.SerialDateTests.java
 * --------------------
 * (C) Copyright 2001-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: org.jfree.date.junit.SerialDateTests.java,v 1.5 2005/10/18 13:15:28 mungady Exp $
 *
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jfree.date.*;

import static org.jfree.date.DayDate.Month;
import static org.jfree.date.DayDate.Day;

/**
 * Some JUnit tests for the {@link DayDate} class.
 */
public class SerialDateTests extends TestCase {

    /** Date representing November 9. */
    private DayDate nov9Y2001;

    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public SerialDateTests(final String name) {
        super(name);
    }

    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(SerialDateTests.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = DayDateFactory.makeDate(9, Month.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final DayDate jan9Y2002 = DayDate.addMonths(2, this.nov9Y2001);
        final DayDate answer = DayDateFactory.makeDate(9, Month.make(1), 2002);
        Assert.assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDateFactory.makeDate(5, Month.OCTOBER, 2003);
        final DayDate d2 = DayDate.addMonths(2, d1);
        Assert.assertEquals(d2, DayDateFactory.makeDate(5, Month.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo1Jan2003() {
        final DayDate d1 = DayDateFactory.makeDate(1, Month.JANUARY, 2003);
        final DayDate d2 = DayDate.addMonths(0, d1);
        Assert.assertEquals(d2, d1);
    }

    /**
     * Monday preceding Friday 9 November 2001 should be 5 November.
     */
    public void testMondayPrecedingFriday9Nov2001() {
        DayDate mondayBefore = DayDate.getPreviousDayOfWeek(
                Day.MONDAY, this.nov9Y2001
        );
        Assert.assertEquals(5, mondayBefore.getDayOfMonth());
    }

    /**
     * Monday following Friday 9 November 2001 should be 12 November.
     */
    public void testMondayFollowingFriday9Nov2001() {
        DayDate mondayAfter = DayDate.getFollowingDayOfWeek(
                Day.MONDAY, this.nov9Y2001
        );
        Assert.assertEquals(12, mondayAfter.getDayOfMonth());
    }

    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    public void testMondayNearestFriday9Nov2001() {
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(
                Day.MONDAY, this.nov9Y2001
        );
        Assert.assertEquals(12, mondayNearest.getDayOfMonth());
    }

    /**
     * The Monday nearest to 22nd January 1970 falls on the 19th.
     */
    public void testMondayNearest22Jan1970() {
        DayDate jan22Y1970 = DayDateFactory.makeDate(22, Month.JANUARY, 1970);
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(Day.MONDAY, jan22Y1970);
        Assert.assertEquals(19, mondayNearest.getDayOfMonth());
    }

    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this 
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {

        final String test = DayDate.weekdayCodeToString(Day.SATURDAY);
        assertEquals("Saturday", test);

    }

    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the 
     * default locale doesn't use English weekday names...devise a better test!
     */
    public void testStringToWeekday() {

        int weekday = DayDate.stringToWeekdayCode("Wednesday");
        Assert.assertEquals(Day.WEDNESDAY.index, weekday);

        weekday = DayDate.stringToWeekdayCode(" Wednesday ");
        Assert.assertEquals(Day.WEDNESDAY.index, weekday);

        weekday = DayDate.stringToWeekdayCode("Wed");
        Assert.assertEquals(Day.WEDNESDAY.index, weekday);

    }

    /**
     * Test the conversion of a string to a month.  Note that this test will fail if the default
     * locale doesn't use English month names...devise a better test!
     */
    public void testStringToMonthCode() {

        int m = DayDate.stringToMonthCode("January");
        Assert.assertEquals(Month.JANUARY, Month.make(m));

        m = DayDate.stringToMonthCode(" January ");
        Assert.assertEquals(Month.JANUARY, Month.make(m));

        m = DayDate.stringToMonthCode("Jan");
        Assert.assertEquals(Month.JANUARY, Month.make(m));

    }

    /**
     * Tests the conversion of a month code to a string.
     */
    public void testMonthCodeToStringCode() {

        final String test = DayDate.monthCodeToString(Month.DECEMBER);
        assertEquals("December", test);

    }

    /**
     * 1900 is not a leap year.
     */
    public void testIsNotLeapYear1900() {
        assertTrue(!DayDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year.
     */
    public void testIsLeapYear2000() {
        assertTrue(DayDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    public void testLeapYearCount1899() {
        Assert.assertEquals(DayDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    public void testLeapYearCount1903() {
        Assert.assertEquals(DayDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    public void testLeapYearCount1904() {
        Assert.assertEquals(DayDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    public void testLeapYearCount1999() {
        Assert.assertEquals(DayDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    public void testLeapYearCount2000() {
        Assert.assertEquals(DayDate.leapYearCount(2000), 25);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        DayDate d1 = DayDateFactory.makeDate(15, Month.make(4), 2000);
        DayDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DayDate) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        Assert.assertEquals(d1, d2);

    }
    
    /**
     * A test for bug report 1096282 (now fixed).
     */
    public void test1096282() {
        DayDate d = DayDateFactory.makeDate(29, Month.make(2), 2004);
        d = DayDate.addYears(1, d);
        DayDate expected = DayDateFactory.makeDate(28, Month.make(2), 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        DayDate d1 = DayDateFactory.makeDate(31, Month.make(5), 2004);
        
        DayDate d2 = DayDate.addMonths(1, d1);
        Assert.assertEquals(30, d2.getDayOfMonth());
        Assert.assertEquals(6, d2.getMonth());
        Assert.assertEquals(2004, d2.getYYYY());
        
        DayDate d3 = DayDate.addMonths(2, d1);
        Assert.assertEquals(31, d3.getDayOfMonth());
        Assert.assertEquals(7, d3.getMonth());
        Assert.assertEquals(2004, d3.getYYYY());
        
        DayDate d4 = DayDate.addMonths(1, DayDate.addMonths(1, d1));
        Assert.assertEquals(30, d4.getDayOfMonth());
        Assert.assertEquals(7, d4.getMonth());
        Assert.assertEquals(2004, d4.getYYYY());
    }
}
