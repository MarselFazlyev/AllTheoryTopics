package innopolisLection.semipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Connection connection;

    private final String BY_ID = "SELECT * FROM car where id = ?";
    private final String ALL_ELEMENTS = "SELECT * FROM car ";

    public DBUtil() throws SQLException {
        connection = createConnection();
    }

    public static Connection createConnection() throws SQLException {
        if (connection == null) {
            Properties properties = new Properties();
            // так как ищем файл в модуле JDBC (многомодульная структура проекта) , то путь отличается от лекционного
            try (FileInputStream in = new FileInputStream("JDBC/src/main/resources/db.properties")) {
                //загрузка настроек в  properties
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Connection connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

            return connection;
        }
        return connection;

    }

    public ResultSet getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(BY_ID);
        // в статическом поле  BY_ID в запросе стоит знак ?, поэтому ставим индекс 1, так как только один параметр
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public ResultSet getAllElements() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_ELEMENTS);
        return preparedStatement.executeQuery();
    }
}
