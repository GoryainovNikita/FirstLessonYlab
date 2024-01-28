package org.example.model.meter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс отвечающий за показания воды.
 */

public class MeterWater {

    private final int coldWater;
    private final int hotWater;

    /**
     * Когда показания были сданы
     */
    private final LocalDate date;

    public MeterWater(int coldWater, int hotWater, LocalDate date) {
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.date = date;
    }

    public int getColdWater() {
        return coldWater;
    }

    public int getHotWater() {
        return hotWater;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Показания воды за " + date + ":\n" + "Горячая вода - " + hotWater + "\nХолодная вода - " + coldWater + "\n";
    }
}
