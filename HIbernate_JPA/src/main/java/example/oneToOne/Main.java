package example.oneToOne;

import example.oneToOne.model.Passport;
import example.oneToOne.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        //        Здесь начинается код Hibernate
        session.beginTransaction();

    /*      Person person = new Person("Marsel",37);
          Passport passport = new Passport(person,112345);
          person.setPassport(passport);
          session.persist(person);*/


//        назначили новый паспорт человеку
   /*     session.get(Person.class, 1)
                .getPassport()
                .setPassport_number(222333);*/

//        Person person2 = new Person("Sergey",25);
//        Passport passport2 = new Passport(person2,444444);
//        person2.setPassport(passport2);
//        session.persist(person2);

//      каскадирование удаления passport2 прописано на стороне БД при создании таблицы passport,
//      поэтому он гарантированно удалится вместе с person2
//        проблема!!! Не удается удалить паспорт вместе с person без аннотации org.hibernate.annotations.CascadeType.ALL
        session.remove(session.get(Person.class,3));

        session.getTransaction().commit();
        //        Здесь закончился код Hibernate

    }
}
