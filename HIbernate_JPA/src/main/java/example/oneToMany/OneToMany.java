package example.oneToMany;

import example.getObjectFromDB.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToMany {
    public static void main(String[] args) {
        //пример отнощения "OneToMany"
        //создаем в БД hibernate_demo_db две таблицы : Person1 (отличаеися от таюлицы Person) и Item

        //передаем Hibernate класс,являющиймся сущностью.
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        //получаем SessionFactory, чтобы из него получить сессию
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            // запускаем транзакцию
            session.beginTransaction();




            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
