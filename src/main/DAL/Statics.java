package DAL;

import java.sql.*;

public class Statics {
    public static class DB {
        public static String host = "primary.folkmann.it";
        public static String port = "3306";
        public static String username = "CDIO";
        public static String password = "y1NzaOYI08FrdqzX";
        public static String database = "/DTU_CDIOFinal";

        //Do not edit these variables
        public static String driver = "com.mysql.cj.jdbc.Driver";
        public static String url = "jdbc:mysql://" + host + ":" + port + database + "?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        public DB() {}

        public static ResultSet get(String query) throws SQLException, ClassNotFoundException {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }

        public static void update(String query) throws SQLException, ClassNotFoundException {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            connection.close();
        }
    }
}
