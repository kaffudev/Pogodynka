package pl.kamilfurdal.models.database;

import pl.kamilfurdal.models.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector instance = new DatabaseConnector();
    public static DatabaseConnector getInstance(){
        return instance;
    }

    private Connection connection;

    private DatabaseConnector() {
       connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");  //refleksja w Javie
            connection = DriverManager.getConnection
                    (Config.MYSQL_LINK, Config.MYSQL_LOGIN, Config.MYSQL_PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("It works!");

    }

    public PreparedStatement createStatement(String SQL){ //prowadnica do zapytań
        try {

            return connection.prepareStatement(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
