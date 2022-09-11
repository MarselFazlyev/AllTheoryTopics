package example.cascadirovanie;

import example.cascadirovanie.model.Item;
import example.cascadirovanie.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cascadirovanie {
    public static void main(String[] args) {
        //пример каскадирования

        //передаем Hibernate классы,являющиеся сущностью.
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        //получаем SessionFactory, чтобы из него получить сессию
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            // запускаем транзакцию
            session.beginTransaction();
            Person person = new Person("Test cascading", 34);
            Item item = new Item(person, "Test cascading item");
            person.setItems(new ArrayList<>(Collections.singleton(item)));
            session.persist(person);


            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
