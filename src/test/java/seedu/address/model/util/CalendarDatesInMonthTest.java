package seedu.address.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalendarDatesInMonthTest {

    @Test
    public void isValidCalendarDate() {

        //invalid months
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("11/13/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("11/00/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("11/23/2019"));

        //invalid days
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("40/12/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("32/12/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("00/12/2019"));

        //test 31 on months with less than 31 days
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("31/02/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("31/04/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("31/06/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("31/09/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("31/11/2019"));

        //testing 29th February on Leap Years
        assertTrue(CalendarDatesInMonth.isValidDayInMonth("29/02/2016"));
        assertTrue(CalendarDatesInMonth.isValidDayInMonth("29/02/2020"));
        assertTrue(CalendarDatesInMonth.isValidDayInMonth("29/02/2024"));

        //testing 29th February on Non-Leap Years
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("29/02/2019"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("29/02/2022"));
        assertFalse(CalendarDatesInMonth.isValidDayInMonth("29/02/2035"));

        //testing invalid days in months using alternate methods
        assertFalse(CalendarDatesInMonth.doesMonthContainDay("this", 31));
        assertFalse(CalendarDatesInMonth.doesMonthContainDay("this", 0));
        assertFalse(CalendarDatesInMonth.doesMonthContainDay("next", 0));
        assertFalse(CalendarDatesInMonth.doesMonthContainDay("last", 32));

        //testing valid days in month using alternate methods
        assertTrue(CalendarDatesInMonth.doesMonthContainDay("this", 30));
        assertTrue(CalendarDatesInMonth.doesMonthContainDay("last", 30));
        assertTrue(CalendarDatesInMonth.doesMonthContainDay("next", 30));
    }
}