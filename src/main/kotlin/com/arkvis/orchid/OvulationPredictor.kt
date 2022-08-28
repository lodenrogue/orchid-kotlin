package com.arkvis.orchid

import java.time.LocalDate
import java.util.*

class OvulationPredictor {

    fun predictNextFertilityWindow(periodWindow: PeriodWindow): FertilityWindow {
        val nextPeriodDate = periodWindow.getStartDate()

        if (Objects.isNull(nextPeriodDate)) {
            return FertilityWindow(emptyList(), null)
        }

        val nextOvulationDate = nextPeriodDate!!.minusDays(DEFAULT_DAYS_BEFORE_PERIOD)
        val ovulationStartDay = nextOvulationDate.minusDays(DEFAULT_DAYS_BEFORE_OVULATION)

        val nextFertilityWindow: MutableList<LocalDate> = ArrayList()
        for (i in 0 until DEFAULT_DAYS_BEFORE_OVULATION + 1) {
            nextFertilityWindow.add(ovulationStartDay.plusDays(i))
        }
        return FertilityWindow(nextFertilityWindow, nextOvulationDate)
    }

    companion object {
        internal const val DEFAULT_DAYS_BEFORE_PERIOD: Long = 14
        internal const val DEFAULT_DAYS_BEFORE_OVULATION: Long = 5;
    }
}