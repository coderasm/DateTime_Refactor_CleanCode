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
 * -------------------------
 * org.jfree.date.junit.SpreadsheetDateTests.java
 * -------------------------
 * (C) Copyright 2001-2003, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: org.jfree.date.junit.SpreadsheetDateTests.java,v 1.3 2005/10/18 13:15:28 mungady Exp $
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 24-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

import java.io.*;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jfree.date.*;

import static org.jfree.date.DayDate.Month;


/**
 * Tests for the org.jfree.date.SpreadsheetDate class.
 *
 */
public class SpreadsheetDateTests extends TestCase {

    /** Date representing 1 January 1900. */
    private DayDate jan1Y1900;

    /** Date representing serial day number 2. */
    private DayDate s2;

    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return the test suite.
     */
    public static Test suite() {
        return new TestSuite(SpreadsheetDateTests.class);
    }

    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public SpreadsheetDateTests(final String name) {
        super(name);
    }

    /**
     * Setup.
     */
    protected void setUp() {
        this.jan1Y1900 = new SpreadsheetDate(1, Month.JANUARY, 1900);
        this.s2 = new SpreadsheetDate(2);
    }

    /**
     * 1 January 1900 is a Thursday.
     */
    public void test1Jan1900GetDayOfWeek() {
        final int dayOfWeek = this.jan1Y1900.getDayOfWeek();
        Assert.assertEquals(DayDate.MONDAY, dayOfWeek);
    }

    /**
     * 12 November 2001 is a Monday.
     */
    public void test12Nov2001GetDayOfWeek() {
        final DayDate nov12Y2001 = new SpreadsheetDate(12, Month.NOVEMBER, 2001);
        final int dayOfWeek = nov12Y2001.getDayOfWeek();
        Assert.assertEquals(DayDate.MONDAY, dayOfWeek);
    }

    /**
     * Day 2 is the first of the month.
     */
    public void testS2GetDayOfMonth() {
        final int dayOfMonth = this.s2.getDayOfMonth();
        assertEquals(1, dayOfMonth);
    }

    /**
     * Day 2 is in January.
     */
    public void testS2GetMonth() {
        final int month = this.s2.getMonth();
        Assert.assertEquals(Month.JANUARY, Month.make(month));
    }

    /**
     * Day 2 is in 1900.
     */
    public void testS2GetYYYY() {
        final int year = this.s2.getYYYY();
        assertEquals(1900, year);
    }

    /**
     * Day 37986 is 31 Dec 2003.
     */
    public void test37986() {
        final SpreadsheetDate d = new SpreadsheetDate(37986);
        Assert.assertEquals(31, d.getDayOfMonth());
        Assert.assertEquals(Month.DECEMBER, Month.make(d.getMonth()));
        Assert.assertEquals(2003, d.getYYYY());
    }

    /**
     * Day 37987 is 1 Jan 2004.
     */
    public void test37987() {
        final SpreadsheetDate d = new SpreadsheetDate(37987);
        Assert.assertEquals(1, d.getDayOfMonth());
        Assert.assertEquals(Month.JANUARY, Month.make(d.getMonth()));
        Assert.assertEquals(2004, d.getYYYY());
    }

    /**
     * Day 38352 is 31 Dec 2004.
     */
    public void test38352() {
        final SpreadsheetDate d = new SpreadsheetDate(38352);
        Assert.assertEquals(31, d.getDayOfMonth());
        Assert.assertEquals(Month.DECEMBER, Month.make(d.getMonth()));
        Assert.assertEquals(2004, d.getYYYY());
    }

    /**
     * Day 38353 is 1 Jan 2005.
     */
    public void test38353() {
        final SpreadsheetDate d = new SpreadsheetDate(38353);
        Assert.assertEquals(1, d.getDayOfMonth());
        Assert.assertEquals(Month.JANUARY, Month.make(d.getMonth()));
        Assert.assertEquals(2005, d.getYYYY());
    }

    /**
     * Create a date for serial number 36584: it should be 28-Feb-2000.
     */
    public void test36584() {
        final SpreadsheetDate d = new SpreadsheetDate(36584);
        Assert.assertEquals(28, d.getDayOfMonth());
        Assert.assertEquals(Month.FEBRUARY, Month.make(d.getMonth()));
        Assert.assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for serial number 36585: it should be 29-Feb-2000.
     */
    public void test36585() {
        final SpreadsheetDate d = new SpreadsheetDate(36585);
        Assert.assertEquals(29, d.getDayOfMonth());
        Assert.assertEquals(Month.FEBRUARY, Month.make(d.getMonth()));
        Assert.assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for serial number 36586: it should be 1-Mar-2000.
     */
    public void test36586() {
        final SpreadsheetDate d = new SpreadsheetDate(36586);
        Assert.assertEquals(1, d.getDayOfMonth());
        Assert.assertEquals(Month.MARCH, Month.make(d.getMonth()));
        Assert.assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for 01-Jan-1900: the serial number should be 2.
     */
    public void test01Jan1900ToSerial() {
        final int serial = this.jan1Y1900.toSerial();
        assertEquals(2, serial);
    }

    /**
     * Create a date for 28-Feb-1900: the serial number should be 60.
     */
    public void test28Feb1900ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(28, Month.FEBRUARY, 1900);
        Assert.assertEquals(60, d.toSerial());
    }

    /**
     * Create a date for 01-Mar-1900: the serial number should be 61.
     */
    public void test01Mar1900ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.MARCH, 1900);
        Assert.assertEquals(61, d.toSerial());
    }

    /**
     * Create a date for 31-Dec-1999: the serial number should be 36525.
     */
    public void test31Dec1999ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(31, Month.DECEMBER, 1999);
        Assert.assertEquals(36525, d.toSerial());
    }

    /**
     * Create a date for 1-Jan-2000: the serial number should be 36526.
     */
    public void test01Jan2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.JANUARY, 2000);
        Assert.assertEquals(36526, d.toSerial());
    }

    /**
     * Create a date for 31-Jan-2000: the serial number should be 36556.
     */
    public void test31Jan2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(31, Month.JANUARY, 2000);
        Assert.assertEquals(36556, d.toSerial());
    }

    /**
     * Create a date for 01-Feb-2000: the serial number should be 36557.
     */
    public void test01Feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.FEBRUARY, 2000);
        Assert.assertEquals(36557, d.toSerial());
    }

    /**
     * Create a date for 28-Feb-2000: the serial number should be 36584.
     */
    public void test28Feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(28, Month.FEBRUARY, 2000);
        Assert.assertEquals(36584, d.toSerial());
    }

    /**
     * Create a date for 29-Feb-2000: the serial number should be 36585.
     */
    public void test29feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(29, Month.FEBRUARY, 2000);
        Assert.assertEquals(36585, d.toSerial());
    }

    /**
     * Create a date for 1-Mar-2000: the serial number should be 36586.
     */
    public void test1mar2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.MARCH, 2000);
        Assert.assertEquals(36586, d.toSerial());
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        final SpreadsheetDate d1 = new SpreadsheetDate(15, Month.make(4), 2000);
        SpreadsheetDate d2 = null;

        try {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            final ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (SpreadsheetDate) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        Assert.assertEquals(d1, d2);

    }

}