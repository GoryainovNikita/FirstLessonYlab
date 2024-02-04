package org.example.model.repository;

import org.example.entity.audit.Audit;
import org.example.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditRepository {


    public static List<Audit> getAudit(){
        List<Audit> audits = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from audit");
            while (resultSet.next()){
                String audit = resultSet.getString("audit");
                int userId = resultSet.getInt("user_id");
                Audit audit1 = new Audit(audit, userId);
                audits.add(audit1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return audits;
    }
    public static void addAudit(Audit audit){
        String addUser = "INSERT into audit (audit, user_id) VALUES (?, ?);";
        Connection connection = ConnectionDB.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(addUser);
            preparedStatement.setString(1, audit.getAudit());
            preparedStatement.setInt(2, audit.getUserId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }

}
