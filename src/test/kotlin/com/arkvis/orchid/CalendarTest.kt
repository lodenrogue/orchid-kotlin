package com.arkvis.orchid

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class CalendarTest {

    private lateinit var periodCalendar: PeriodCalendar

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

    @Test
    fun should_returnNoFlow_when_periodAddedWithNoFlow() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date)

        val retrievedDay = periodCalendar.getDay(date)
        val period = retrievedDay.period
        assertNull(period?.flow)
    }

    @Test
    fun should_returnCorrectFlow_when_periodAddedWithLightFlow() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date, Flow.LIGHT)

        val retrievedDay = periodCalendar.getDay(date)
        val retrievedPeriod = retrievedDay.period

        assertEquals(Flow.LIGHT, retrievedPeriod?.flow)
    }

    @Test
    fun should_returnCorrectFlow_when_periodAddedWithMediumFlow() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date, Flow.MEDIUM)

        val retrievedDay = periodCalendar.getDay(date)
        val retrievedPeriod = retrievedDay.period

        assertEquals(Flow.MEDIUM, retrievedPeriod?.flow)
    }

    @Test
    fun should_returnCorrectFlow_when_periodAddedWithHeavyFlow() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date, Flow.HEAVY)

        val retrievedDay = periodCalendar.getDay(date)
        val retrievedPeriod = retrievedDay.period

        assertEquals(Flow.HEAVY, retrievedPeriod?.flow)
    }

    @Test
    fun should_returnCorrectFlow_when_periodAddedWithSpottingFlow() {
        val date = LocalDate.now()
        periodCalendar.addPeriod(date, Flow.SPOTTING)

        val retrievedDay = periodCalendar.getDay(date)
        val retrievedPeriod = retrievedDay.period

        assertEquals(Flow.SPOTTING, retrievedPeriod?.flow)
    }

    @Test
    fun should_returnNoTemperature_when_noTemperatureAddedToDay() {
        val date = LocalDate.now()
        val retrievedDay = periodCalendar.getDay(date)
        assertNull(retrievedDay.temperature)
    }

    @Test
    fun should_returnTemperature_when_temperatureAddedToDay() {
        val date = LocalDate.now()
        val temperature = Temperature(BigDecimal("100"), Metric.FAHRENHEIT)
        periodCalendar.addTemperature(date, temperature)

        val retrievedDay = periodCalendar.getDay(date)
        val retrievedTemp = retrievedDay.temperature

        assertEquals(temperature.value, retrievedTemp?.value)
        assertEquals(temperature.metric, retrievedTemp?.metric)
    }
}