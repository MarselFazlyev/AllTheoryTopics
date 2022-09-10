package example.oneToMany;


import example.oneToMany.model.Item;
import example.oneToMany.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OneToMany {
    public static void main(String[] args) {
        //пример отнощения "OneToMany"
        //создаем в БД hibernate_demo_db две таблицы : Person1 (отличаеися от таюлицы Person) и Item

        //передаем Hibernate класс,являющиймся сущностью.
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        //получаем SessionFactory, чтобы из него получить сессию
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            // запускаем транзакцию
            session.beginTransaction();
            Person person = session.get(Person.class,3);
            System.out.println(person);
            List<Item> items =  person.getItems();
            System.out.println(items);
            Item item = new Item(person,"New item from Hibernate");
            session.persist(item);




            //завершаем транзакцию
            session.getTransaction().commit();
        } finally {
            //закоываем подключение
            sessionFactory.close();
        }


    }
}
