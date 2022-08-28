package com.arkvis.orchid

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OvulationPredictionTest {

    @Test
    fun should_returnEmptyFertilityWindow_when_gettingNextFertilityWindowOnEmptyCalendar() {
        val calendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
        val fertilityWindow = calendar.getNextFertilityWindow()
        assertTrue(fertilityWindow.isEmpty())
    }

    @Test
    fun should_returnCorrectWindow_when_gettingNextFertilityWindowOnCalendarThatHasPeriod() {
        val calendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
        calendar.addPeriod(LocalDate.now())

        val nextPeriodDate = calendar.getNextPeriodWindow().getStartDate()!!
        val nextOvulationDate = nextPeriodDate.minusDays(OvulationPredictor.DEFAULT_DAYS_BEFORE_PERIOD)
        val ovulationStartDay = nextOvulationDate.minusDays(5)
        val ovulationWindow = ArrayList<LocalDate>()

        for (i in 0 until OvulationPredictor.DEFAULT_DAYS_BEFORE_OVULATION + 1) {
            ovulationWindow.add(ovulationStartDay.plusDays(i))
        }

        val nextFertilityWindow = calendar.getNextFertilityWindow()
        assertEquals(ovulationWindow, nextFertilityWindow.dates)
        assertEquals(nextOvulationDate, nextFertilityWindow.ovulationDate)
    }
}