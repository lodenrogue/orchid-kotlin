package com.arkvis.orchid

import java.time.LocalDate

class PeriodCalendar(
    private val periodPredictor: PeriodPredictor,
    private val ovulationPredictor: OvulationPredictor
) {
    private val dayMap: MutableMap<LocalDate, Day> = mutableMapOf()

    fun getDay(date: LocalDate): Day {
        return dayMap.getOrDefault(date, Day(date))
    }

    fun addPeriod(date: LocalDate) {
        val day = dayMap.getOrDefault(date, Day(date))
        day.addPeriod()
        dayMap[date] = day
    }
}