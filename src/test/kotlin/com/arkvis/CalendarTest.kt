package com.arkvis

import com.arkvis.orchid.OvulationPredictor
import com.arkvis.orchid.PeriodCalendar
import com.arkvis.orchid.PeriodPredictor
import org.junit.jupiter.api.Assertions
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
}