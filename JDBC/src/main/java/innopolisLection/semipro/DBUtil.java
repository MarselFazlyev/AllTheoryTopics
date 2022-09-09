package innopolisLection.semipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Connection connection;

    private final String BY_ID = "SELECT * FROM ? where id = ?";
    private final String ALL_ELEMENTS = "SELECT * FROM ? ";

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

    public ResultSet getById(Class clazz, int id) throws SQLException {
        String temp = BY_ID.replaceFirst("\\?", clazz.getSimpleName().toLowerCase())
                .replaceFirst("\\?", String.valueOf(id));
        PreparedStatement preparedStatement = connection.prepareStatement(temp);
        return preparedStatement.executeQuery();
    }

    public ResultSet getAllElements(Class clazz) throws SQLException {
        String temp = ALL_ELEMENTS.replaceFirst("\\?", clazz.getSimpleName().toLowerCase());
        PreparedStatement preparedStatement = connection.prepareStatement(temp);

        return preparedStatement.executeQuery();
    }
}
