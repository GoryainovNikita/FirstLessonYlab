package org.example.model.meter;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserMeter {

    private List<MeterWater> meterWaterList;

    public UserMeter() {
        this.meterWaterList = new ArrayList<>();
    }

    /**
     * Метод позволяющий передать показания.
     * @param meter
     * @return
     */
    public boolean handOverMeterWater(MeterWater meter){
        if(!meterWaterList.isEmpty()) {
            MeterWater meterWater = getLastMeterWater();
            if (Period.between(meterWater.getDate(), meter.getDate()).getMonths() < 1) {
                return false;
            } else if (meterWater.getColdWater() > meter.getColdWater() || meterWater.getHotWater() > meter.getHotWater()) {
                return false;
            }
        }
        meterWaterList.add(meter);
        return true;
    }

    /**
     * Метод позволяющий получить последнее сданное показание
     * @return
     */
    public MeterWater getLastMeterWater(){
        if(meterWaterList.isEmpty()){
            throw new NoSuchElementException("Нет показаний");
        }
        return meterWaterList.get(meterWaterList.size() - 1);
    }

    /**
     * Метод позволяющий получить показание за определенный период
     * @param month
     * @return
     */
    public MeterWater getSpecificPeriodMeterWater(int month){
        for(int i = 0; i<meterWaterList.size(); i++){
            if(meterWaterList.get(i).getDate().getMonthValue() == month){
                return meterWaterList.get(i);
            }
        }
        return null;
    }

    public List<MeterWater> getMeterList() {
        return new ArrayList<>(meterWaterList);
    }
}
