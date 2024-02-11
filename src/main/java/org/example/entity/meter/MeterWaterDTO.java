package org.example.entity.meter;

import java.time.LocalDate;
import java.util.Objects;

public final class MeterWaterDTO {

    private final int coldWater;
    private final int hotWater;
    private final LocalDate date;

    public MeterWaterDTO (int coldWater, int hotWater, LocalDate date) {
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterWaterDTO that = (MeterWaterDTO) o;
        return coldWater == that.coldWater && hotWater == that.hotWater && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coldWater, hotWater, date);
    }

    @Override
    public String toString() {
        return "Показания воды за " + date + ":\n" + "Горячая вода - " + hotWater + "\nХолодная вода - " + coldWater + "\n";
    }
}
