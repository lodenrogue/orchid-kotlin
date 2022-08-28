package com.arkvis.orchid

import java.time.LocalDate

class PeriodCalendar(
    private val periodPredictor: PeriodPredictor,
    private val ovulationPredictor: OvulationPredictor
) {
    fun getDay(date: LocalDate): Day {
        return Day()
    }
}