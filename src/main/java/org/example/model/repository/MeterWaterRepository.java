package org.example.model.repository;

import org.example.entity.meter.MeterWater;
import org.example.entity.user.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MeterWaterRepository {


    public static void addMeterWater(User user, MeterWater meterWater){
        String addMeterWater = "INSERT into meter (cold_water, hot_water, date) VALUES (?, ?, ?)";
        String meterUser = "INSERT into user_meter (user_id, meter_id) values (?, ?)";
        try(Connection connection = ConnectionDB.getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(addMeterWater);
            preparedStatement.setInt(1, meterWater.getColdWater());
            preparedStatement.setInt(2, meterWater.getHotWater());
            preparedStatement.setDate(3, Date.valueOf(meterWater.getDate()));
            preparedStatement.executeUpdate();

            MeterWater meterWater1 = getMeterWater("SELECT * FROM meter ORDER BY id DESC LIMIT 1;");

            PreparedStatement preparedStatement1 = connection.prepareStatement(meterUser);
            preparedStatement1.setInt(1, user.getId());
            preparedStatement1.setInt(2, meterWater1.getId());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<MeterWater> getAllMeterWaterUser(User user){
        List<MeterWater> meterWaters = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT meter_id from user_meter where user_id = " + user.getId());
            List<Integer> listId = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("meter_id");
                listId.add(id);
            }
            for(var elem : listId){
                MeterWater meterWaterById = getMeterWaterById(elem);
                meterWaters.add(meterWaterById);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return meterWaters;
    }

    public static MeterWater getMeterWaterById(int id){
     return getMeterWater("SELECT * from meter where id = " + id);
    }

    private static MeterWater getMeterWater(String str) {

        try(Connection connection = ConnectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                int idMeter = resultSet.getInt("id");
                int coldWater = resultSet.getInt("cold_water");
                int hotWater = resultSet.getInt("hot_water");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                MeterWater meterWater = new MeterWater(idMeter,coldWater, hotWater, date);
                connection.close();
                return meterWater;
            }
        } catch (SQLException e) {
            throw new NoSuchElementException();
        }

        //вернуть Optional
        return null;
    }


}
