package com.arkvis.orchid

import java.time.LocalDate

class PeriodWindow(val dates: List<LocalDate>) {

    fun isEmpty(): Boolean {
        return dates.isEmpty()
    }

    fun getStartDate(): LocalDate? {
        return if (isEmpty()) null else dates[0]
    }
}