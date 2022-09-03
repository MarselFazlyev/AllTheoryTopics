package innopolisLection.simple;

import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/MyFirstBase";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    public static void main(String[] args) throws SQLException {
        // нужно подключиться к БД
        Connection connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PASSWORD);
        // isClosed() проверяем соединение, если false, то соединение открыто (нам нужно открытое соединение)
        System.out.println(connection.isClosed());

        // Statement, Prepared statement, Callable statement
        // Statement - выражение,через него мы будем отправлять запросы к базе данных
        Statement statement = connection.createStatement();

        //ResultSet хранит результат запроса в базу данных
        ResultSet resultSet = statement.executeQuery("select * from car");

        // запрос можно обойти через итератор
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") +
                    "|" + resultSet.getString("brand") +
                    "|" + resultSet.getString("model") +
                    "|" + resultSet.getString("color") +
                    "|" + resultSet.getInt("driver_id"));
        }
    }


}
