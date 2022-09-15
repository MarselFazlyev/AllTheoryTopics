package example.manyToMany;

import example.manyToMany.model.Actor;
import example.manyToMany.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class).addAnnotatedClass(Actor.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            пример связывания актеров и фильма
//            Movie movie = new Movie("Pulp Fiction", 1994);
//            Actor actor1 = new Actor("Ashot", 23);
//            Actor actor2 = new Actor("Sergo", 12);
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.persist(movie);
//            session.persist(actor1);
//            session.persist(actor2);

//            вывод списка актеров для фильма
/*            Movie movie = session.get(Movie.class, 1);
            System.out.println(movie.getActors());*/

//            добавление нового фильма и связывание его с существующим актером
 /*           Movie movie2 = new Movie("Friday_13",1980);
            Actor actor = session.get(Actor.class,2);

//            так как актер в Persistant состоянии (отслеживается Hibernate), то сохранять снова его не нужно
            actor.getMovies().add(movie2);
            movie2.setActors(new ArrayList<>(Collections.singletonList(actor)));
            session.persist(movie2);*/

//            удаление фильма у существующего актера
            Actor actor =  session.get(Actor.class,1);
            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }


    }
}
