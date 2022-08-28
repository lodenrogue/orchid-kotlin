package com.arkvis.orchid

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS
import java.util.*

class PeriodPredictor {

    fun predictNextPeriodWindow(allDays: Collection<Day>): PeriodWindow {
        val periodStartDate = getNextPeriodStartDate(allDays)
        if (Objects.isNull(periodStartDate)) {
            return PeriodWindow(emptyList())
        }

        val dates = ArrayList<LocalDate>()
        dates.add(periodStartDate!!)

        val averagePeriodLength = getAveragePeriodLength(allDays)
        for (i in 1 until averagePeriodLength) {
            dates.add(periodStartDate.plusDays(i.toLong()))
        }
        return PeriodWindow(dates)
    }

    private fun getAveragePeriodLength(allDays: Collection<Day>): Int {
        val cycleStartDays = getCycleStartDays(allDays)
        if (cycleStartDays.isEmpty()) return 0

        var totalPeriodDays = 0
        for (day in allDays) {
            if (Objects.nonNull(day.period)) {
                totalPeriodDays++
            }
        }

        val totalNumOfPeriods = cycleStartDays.size
        return totalPeriodDays / totalNumOfPeriods
    }

    private fun getNextPeriodStartDate(allDays: Collection<Day>): LocalDate? {
        val cycleStartDays = getCycleStartDays(allDays)
        if (cycleStartDays.isEmpty()) return null

        val lastCycleStartDay = getLastItem(cycleStartDays)
        val averageCycleLength = getAverageCycleLength(cycleStartDays)
        return lastCycleStartDay.date.plusDays(averageCycleLength)
    }

    private fun getAverageCycleLength(cycleStartDays: List<Day>): Long {
        if (cycleStartDays.size <= 1) return DEFAULT_CYCLE_LENGTH_IN_DAYS.toLong()
        val startDate = cycleStartDays[0].date

        val endDate = getLastItem(cycleStartDays).date
        val totalDays = DAYS.between(startDate, endDate) + DEFAULT_CYCLE_LENGTH_IN_DAYS
        return totalDays / cycleStartDays.size
    }

    private fun getCycleStartDays(allDays: Collection<Day>): List<Day> {
        val startDays: MutableList<Day> = ArrayList()
        var lastPeriodDate = LocalDate.MIN

        for (day in allDays) {
            val date = day.date

            if (Objects.nonNull(day.period)) {
                if (date != lastPeriodDate.plusDays(1)) {
                    startDays.add(day)
                }
                lastPeriodDate = date
            }
        }
        return startDays
    }

    private fun <T> getLastItem(list: List<T>): T {
        return list[list.size - 1]
    }

    companion object {
        internal const val DEFAULT_CYCLE_LENGTH_IN_DAYS: Long = 28
    }
}