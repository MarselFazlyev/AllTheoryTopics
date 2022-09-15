package example.fetchTypeLazy;



import example.fetchTypeLazy.model.Item;
import example.fetchTypeLazy.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FetchTypeLazy {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class,1);
//            Item item = session.get(Item.class,3);


            session.getTransaction().commit();
            System.out.println(person.getItems());


        }


    }
}
