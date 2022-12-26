import models.Gender;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(User.class)
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(new User("vasya", "pupkin"));
        session.save(new User("peyua", "kkosov"));
        session.save(new User("taras"));
        session.save(new User("ananas"));
        session.save(new User("kokos", "vita", Gender.FEMALE, Arrays.asList("c++", "js", "c#")));
        session.save(new User("max", "golov"));
        session.save(new User("max2", "golov2", Gender.MALE, Arrays.asList("java", "js", "sql")));
        session.getTransaction().commit();

        List<User> list =
//                session.createNativeQuery("select * from user_table", User.class).list();
                session.createQuery("select u.name from User u", User.class).list();
        System.out.println(list);

        User user = session.find(User.class, 1);
        System.out.println(user);

        session.close();
        sessionFactory.close();
    }
}
