package com.arkvis

import com.arkvis.orchid.OvulationPredictor
import com.arkvis.orchid.PeriodCalendar
import com.arkvis.orchid.PeriodPredictor
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CalendarTest {

    lateinit var periodCalendar: PeriodCalendar

    @BeforeEach
    fun setUp() {
        periodCalendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
    }

    @Test
    fun should_returnDayWithNoPeriod_when_periodIsMissing() {
        val day = periodCalendar.getDay(LocalDate.now())
        assertNull(day.period)
    }

    @Test
    fun should_returnDayWithPeriod_when_periodIsPresent() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date)

        val retrievedDay = periodCalendar.getDay(date)
        assertNotNull(retrievedDay.period)
    }

    @Test
    fun should_returnMultipleDaysWithPeriod_when_multipleDaysAreAddedToCalendar() {
        val date1 = LocalDate.now()
        val date2 = date1.plusDays(1)
        val date3 = date1.plusDays(2)

        periodCalendar.addPeriod(date1)
        periodCalendar.addPeriod(date2)
        periodCalendar.addPeriod(date3)

        val day1 = periodCalendar.getDay(date1)
        val day2 = periodCalendar.getDay(date2)
        val day3 = periodCalendar.getDay(date3)

        assertNotNull(day1.period)
        assertNotNull(day2.period)
        assertNotNull(day3.period)
    }
}