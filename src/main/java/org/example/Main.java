package org.example;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.in.Controller;
import org.example.model.repository.ConnectionDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Вход в программу
 */
public class Main {
    public static void main(String[] args) {
        try(Connection connection = ConnectionDB.getConnection();) {
            Database correctDatabaseImplementation = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            correctDatabaseImplementation.setDefaultSchemaName("ylab");
            correctDatabaseImplementation.setLiquibaseSchemaName("public");
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(),correctDatabaseImplementation);
            liquibase.update();

        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (LiquibaseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Controller controller = new Controller();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            controller.start(bufferedReader);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}