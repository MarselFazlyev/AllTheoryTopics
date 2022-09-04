package innopolisLection.semipro;

import java.sql.*;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // в файле db.properties необходимо писать значенмя  сразу (без пробелов!!!) после после оператора присваивания "=", иначе выкинется
    // FileNotFindException - не найден путь до файла!!!
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from car where  id = ? or  id = ?");
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 4);

//        ResultSet resultSet = preparedStatement.executeQuery();

//      Проверка работы метода getById()
        DBUtil dbUtil = new DBUtil();
        // на примере класса Car;
//        ResultSet resultSet = dbUtil.getById(Car.class,2);
//         на примере класса Driver
//        ResultSet resultSet = dbUtil.getById(Driver.class,1);

//        Проверка работы метода getAllElements()
//        DBUtil dbUtil = new DBUtil();
//       на примере класса Car;
        ResultSet resultSet = dbUtil.getAllElements(Car.class);
        // на примере класса Driver
//        ResultSet resultSet = dbUtil.getAllElements(Driver.class);

//       вывод в консоль результата запроса по классу Car
//        while (resultSet.next()) {
//            System.out.println("id " + resultSet.getInt("id") +
//                    "|" + "brand " + resultSet.getString("brand") +
//                    "|" + "model " + resultSet.getString("model") +
//                    "|" + "color " + resultSet.getString("color") +
//                    "|" + "driver_id " + resultSet.getInt("driver_id"));

//         вывод в консоль результата запроса по классу Driver
//        while (resultSet.next()){
//            System.out.println("id "+ resultSet.getInt("id")+
//                    "|"+"name "+resultSet.getString("name")+
//                    "|"+"last_name"+resultSet.getString("last_name"));
//        }

        // работа со списком элементов  из таблицы driver
//        List<innopolisLection.semipro.Driver> drivers = new ArrayList<>();
//        while (resultSet.next()){
//            drivers.add(new innopolisLection.semipro.Driver(
//                    resultSet.getInt("id"),
//                    resultSet.getString("name"),
//                    resultSet.getString("last_name")));
//        }
//        System.out.println(drivers);

        // работа со списком элементов  из таблицы car
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()){
            cars.add(new Car(
                    resultSet.getInt("id"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getString("color"),
                    resultSet.getInt("driver_id")));
        }
        System.out.println(cars);
    }
}
