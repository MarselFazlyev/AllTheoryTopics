package example.updateAndDeleteObjectInDB;

import example.getObjectFromDB.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class updateAndDeletePerson {
    public static void main(String[] args) {
        //пример получения данных класса Person с помощью Hibernate из БД hibernate_demo_db с помоью Hibernate

        //передаем Hibernate класс,являющиймся сущностью.
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        //получаем SessionFactory, чтобы из него получить сессию
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            // запускаем транзакцию
            session.beginTransaction();

            //получаем данные из таблицы
            Person person = session.get(Person.class, 6);
            System.out.println("Hello from Hibernate!!!" + person.getName());
            //обновляем данные в таблице (аналог  SQL запроса update )
            person.setName("New name");

            //удаление данных из таблицы с помощью Hibernate
            session.remove(person);
            //объекта person с id = 6 больше нет в таблице


            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
