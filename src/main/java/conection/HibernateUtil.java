/*
 * Define the engin of database
 */
package conection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jerson Perez
 */
public class HibernateUtil {

    private static SessionFactory factory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            SessionFactory factory = configuration.buildSessionFactory(service);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return factory;
    }
}
