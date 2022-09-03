package innopolisLection.semipro;

import java.sql.*;

public class Main {
    // в файле db.properties необходимо писать значенмя  сразу (без пробелов!!!) после после оператора присваивания "=", иначе выкинется
    // FileNotFindException - не найден путь до файла!!!
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from car where  id = ? or  id = ?");
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 4);

        ResultSet resultSet = preparedStatement.executeQuery();

  /*    Проверка работы метода getById()
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.getById(4);*/

      /*  Проверка работы метода getAllElements()
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = dbUtil.getAllElements();*/

        while (resultSet.next()) {
            System.out.println("id " + resultSet.getInt("id") +
                    "|" + "brand " + resultSet.getString("brand") +
                    "|" + "model " + resultSet.getString("model") +
                    "|" + "color " + resultSet.getString("color") +
                    "|" + "driver_id " + resultSet.getInt("driver_id"));
        }
    }
}
