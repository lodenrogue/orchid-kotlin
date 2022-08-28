package com.arkvis.orchid

import java.time.LocalDate

class Day(val date: LocalDate) {

    var period: Period? = null
        private set

    var temperature: Temperature? = null
        private set

    fun addPeriod() {
        period = Period()
    }

    fun addPeriod(flow: Flow) {
        addPeriod()
        period?.flow = flow
    }

    fun addTemperature(temperature: Temperature) {
        this.temperature = temperature
    }
}