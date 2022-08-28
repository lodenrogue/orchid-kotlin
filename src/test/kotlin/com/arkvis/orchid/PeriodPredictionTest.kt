package com.arkvis.orchid

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PeriodPredictionTest {

    lateinit var periodCalendar: PeriodCalendar

    @BeforeEach
    fun setUp() {
        periodCalendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
    }

    @Test
    fun should_returnEmptyPeriodWindow_when_gettingNextPeriodWindowOnEmptyCalendar() {
        val periodWindow = periodCalendar.getNextPeriodWindow()
        assertTrue(periodWindow.isEmpty())
    }

}