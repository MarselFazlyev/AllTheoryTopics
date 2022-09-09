package example.hql;

import example.getObjectFromDB.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HQLquerry {
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
            // создаем hql запрос , который работает с сущностями (с классом Person, помеченным @Entity)
            List<Person> personList = session.createSelectionQuery("FROM Person where age >30 ", Person.class).getResultList();
            for (Person person: personList) {
                System.out.println(person);
            }

            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
