# Orchid

### 100% offline period tracker. You own the data. It can't ever be sold, shared, or divulged because it never leaves your device.

### Usage

Create a calendar

``` kotlin
val calendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
```

Add a period

``` kotlin
val periodDate = LocalDate.now()
calendar.addPeriod(periodDate)
```

Add a period with flow

``` kotlin
val periodDate = LocalDate.now()
calendar.addPeriod(periodDate, Flow.LIGHT)
```

Get a period

``` kotlin
val day = calendar.getDay(periodDate)
val period = day.period
```

Get a period with flow

``` kotlin
val day = calendar.getDay(periodDate)
val period = day.period
val flow = period?.flow
```

Predict next period window

``` kotlin
val periodWindow = calendar.getNextPeriodWindow()
val isEmpty = periodWindow.isEmpty()

val startDate = periodWindow.getStartDate()
val dates = periodWindow.dates
```

Predict next fertility window

``` kotlin
val fertilityWindow = calendar.getNextFertilityWindow()
val isEmpty = fertilityWindow.isEmpty()

val fertilityDates = fertilityWindow.dates
val ovulationDate = fertilityWindow.ovulationDate
```

Add temperature

``` kotlin
val temperature = Temperature(BigDecimal("98.6"), Metric.FAHRENHEIT)
calendar.addTemperature(date, temperature)

val retrievedTemperature = calendar.getDay(date).temperature
```
