package example.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
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
            Person person = session.get(Person.class, 1);
            System.out.println("Hello from Hibernate!!!" + person.getName());
            System.out.println(person.getAge());

            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
