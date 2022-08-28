package com.arkvis.orchid

import java.time.LocalDate

class FertilityWindow(
    val dates: List<LocalDate>,
    val ovulationDate: LocalDate?
) {
    fun isEmpty(): Boolean {
        return dates.isEmpty()
    }

}