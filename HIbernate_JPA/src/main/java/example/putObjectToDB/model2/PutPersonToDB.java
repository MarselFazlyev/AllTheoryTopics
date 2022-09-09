package example.putObjectToDB.model2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PutPersonToDB {
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

            //загружаем  данные в таблицу
            Person person1 = new Person("Test1", 10);
            Person person2 = new Person("Test2",30);
            Person person3 = new Person("Test3",40);

            //загружаем объекты в БД
            session.persist(person1);
            session.persist(person2);
            session.persist(person3);


            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
