package com.arkvis.orchid

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PeriodPredictionTest {

    private lateinit var periodCalendar: PeriodCalendar

    @BeforeEach
    fun setUp() {
        periodCalendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
    }

    @Test
    fun should_returnEmptyPeriodWindow_when_gettingNextPeriodWindowOnEmptyCalendar() {
        val periodWindow = periodCalendar.getNextPeriodWindow()
        assertTrue(periodWindow.isEmpty())
    }

    @Test
    fun should_returnCorrectPeriodWindow_when_gettingNextPeriodWindowOnCalendarWithSinglePeriod() {
        val lastPeriod = LocalDate.now()
        periodCalendar.addPeriod(lastPeriod)

        val expectedDate = lastPeriod.plusDays(PeriodPredictor.DEFAULT_CYCLE_LENGTH_IN_DAYS)
        val dates = periodCalendar.getNextPeriodWindow().dates

        assertEquals(1, dates.size)
        assertCorrectDates(expectedDate, dates)
    }

    @Test
    fun should_returnCorrectPeriodWindow_when_gettingNextPeriodWindowOnCalendarWithSingleCycle() {
        val firstPeriod = LocalDate.now()
        periodCalendar.addPeriod(firstPeriod)
        periodCalendar.addPeriod(firstPeriod.plusDays(1))
        periodCalendar.addPeriod(firstPeriod.plusDays(2))
        periodCalendar.addPeriod(firstPeriod.plusDays(3))

        val dates = periodCalendar.getNextPeriodWindow().dates
        assertEquals(4, dates.size)

        val firstExpectedDate = firstPeriod.plusDays(PeriodPredictor.DEFAULT_CYCLE_LENGTH_IN_DAYS)
        assertCorrectDates(firstExpectedDate, dates)
    }

    @Test
    fun should_returnCorrectPeriodWindow_when_gettingNextPeriodPeriodWindowOnCalendarWithTwoCycles() {
        val firstCycleDate = LocalDate.now()
        periodCalendar.addPeriod(firstCycleDate)
        periodCalendar.addPeriod(firstCycleDate.plusDays(1))
        periodCalendar.addPeriod(firstCycleDate.plusDays(2))
        periodCalendar.addPeriod(firstCycleDate.plusDays(3))

        val firstCycleLengthInDay = 30

        val secondCycleDate = firstCycleDate.plusDays(firstCycleLengthInDay.toLong())
        periodCalendar.addPeriod(secondCycleDate)
        periodCalendar.addPeriod(secondCycleDate.plusDays(1))
        periodCalendar.addPeriod(secondCycleDate.plusDays(2))
        periodCalendar.addPeriod(secondCycleDate.plusDays(3))
        periodCalendar.addPeriod(secondCycleDate.plusDays(4))
        periodCalendar.addPeriod(secondCycleDate.plusDays(5))

        val dates = periodCalendar.getNextPeriodWindow().dates
        assertEquals(5, dates.size)

        val averageCycleLength = ((PeriodPredictor.DEFAULT_CYCLE_LENGTH_IN_DAYS + firstCycleLengthInDay) / 2)
        val firstExpectedDate = secondCycleDate.plusDays(averageCycleLength)
        assertCorrectDates(firstExpectedDate, dates)
    }

    @Test
    fun should_returnCorrectDate_when_gettingNextPeriodDateOnCalendarWithMultipleCycles() {
        val firstCycleDate = LocalDate.now()
        periodCalendar.addPeriod(firstCycleDate)
        periodCalendar.addPeriod(firstCycleDate.plusDays(1))
        periodCalendar.addPeriod(firstCycleDate.plusDays(2))
        periodCalendar.addPeriod(firstCycleDate.plusDays(3))

        val firstCycleLengthInDay = 30

        val secondCycleDate = firstCycleDate.plusDays(firstCycleLengthInDay.toLong())
        periodCalendar.addPeriod(secondCycleDate)
        periodCalendar.addPeriod(secondCycleDate.plusDays(1))
        periodCalendar.addPeriod(secondCycleDate.plusDays(2))
        periodCalendar.addPeriod(secondCycleDate.plusDays(3))
        periodCalendar.addPeriod(secondCycleDate.plusDays(4))
        periodCalendar.addPeriod(secondCycleDate.plusDays(5))

        val secondCycleLengthInDays = 23

        val thirdCycleDate = secondCycleDate.plusDays(secondCycleLengthInDays.toLong())
        periodCalendar.addPeriod(thirdCycleDate)
        periodCalendar.addPeriod(thirdCycleDate.plusDays(1))
        periodCalendar.addPeriod(thirdCycleDate.plusDays(2))
        periodCalendar.addPeriod(thirdCycleDate.plusDays(3))

        val dates = periodCalendar.getNextPeriodWindow().dates
        assertEquals(4, dates.size)

        val averageCycleLength =
            ((PeriodPredictor.DEFAULT_CYCLE_LENGTH_IN_DAYS + firstCycleLengthInDay + secondCycleLengthInDays) / 3)

        val firstExpectedDate = thirdCycleDate.plusDays(averageCycleLength)
        assertCorrectDates(firstExpectedDate, dates)
    }

    private fun assertCorrectDates(firstDate: LocalDate, dates: List<LocalDate>) {
        for (index in dates.indices) {
            assertEquals(firstDate.plusDays(index.toLong()), dates[index])
        }
    }

}