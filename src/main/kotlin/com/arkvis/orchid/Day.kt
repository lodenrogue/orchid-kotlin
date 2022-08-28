package com.arkvis.orchid

import java.time.LocalDate

class Day(val date: LocalDate) {

    var period: Period? = null

    fun addPeriod() {
        period = Period()
    }
}