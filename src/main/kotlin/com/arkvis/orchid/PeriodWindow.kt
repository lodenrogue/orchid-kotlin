package com.arkvis.orchid

import java.time.LocalDate

class PeriodWindow(val dates: List<LocalDate>) {

    fun isEmpty(): Boolean {
        return true
    }
}