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

    fun addPeriod(date: LocalDate, flow: Flow) {
        val day = dayMap.getOrDefault(date, Day(date))
        day.addPeriod(flow)
        dayMap[date] = day
    }

    fun addTemperature(date: LocalDate, temperature: Temperature) {
        val day = dayMap.getOrDefault(date, Day(date))
        day.addTemperature(temperature)
        dayMap[date] = day
    }

    fun getNextPeriodWindow(): PeriodWindow {
        return periodPredictor.predictNextPeriodWindow(dayMap.values)
    }

    fun getNextFertilityWindow(): FertilityWindow {
        return ovulationPredictor.predictNextFertilityWindow(getNextPeriodWindow())
    }
}