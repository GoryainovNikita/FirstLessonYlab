package org.example.entity.meter;

import org.example.aop.annotations.Loggable;
import org.example.entity.user.User;
import org.example.model.repository.MeterWaterRepository;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserMeter {

    /**
     * Метод позволяющий передать показания.
     * @param meter
     * @return
     */
    public static boolean handOverMeterWater(User user, MeterWater meter){
        if(!MeterWaterRepository.getAllMeterWaterUser(user).isEmpty()) {
            MeterWater meterWater = UserMeter.getLastMeterWater(user);
            if (Period.between(meterWater.getDate(), meter.getDate()).getMonths() < 1) {
                return false;
            } else if (meterWater.getColdWater() > meter.getColdWater() || meterWater.getHotWater() > meter.getHotWater()) {
                return false;
            }
        }
        MeterWaterRepository.addMeterWater(user, meter);
        return true;
    }


    /**
     * Метод позволяющий получить последнее сданное показание
     * @return
     */
    @Loggable
    public static MeterWater getLastMeterWater(User user){
        List<MeterWater> allMeterWaterUser = MeterWaterRepository.getAllMeterWaterUser(user);
        if(allMeterWaterUser.isEmpty()){
            throw new NoSuchElementException("Нет показаний");
        }
        return allMeterWaterUser.get(allMeterWaterUser.size() - 1);
    }

    /**
     * Метод позволяющий получить показание за определенный период
     * @param month
     * @return
     */
    @Loggable
    public static MeterWater getSpecificPeriodMeterWater(int month, User user){
        List<MeterWater> allMeterWaterUser = MeterWaterRepository.getAllMeterWaterUser(user);
        for(int i = 0; i<allMeterWaterUser.size(); i++){
            if(allMeterWaterUser.get(i).getDate().getMonthValue() == month){
                return allMeterWaterUser.get(i);
            }
        }
        return null;
    }

}
