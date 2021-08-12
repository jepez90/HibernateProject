/*
 * this file defines the EntityManagerFactory with the persitance unit and adds
 * the user and password for the database
 * 
 */
package com.jp.hibernate_project.connection;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * creates the EntityManagerFactory class to get EntityManager instances
 * @author Jerson Perez
 */
public class EMUtil {

    private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        
        //inserts  the user and password properties
        Properties customProperties = new Properties();
        customProperties.put("javax.persistence.jdbc.user", "user_test");
        customProperties.put("javax.persistence.jdbc.password", "admin");

        return Persistence.createEntityManagerFactory("com.jp_HibernateProject_jar_1.0PU", customProperties);

    }
    
    /**
     * 
     * @return the EntityManagerFactory created for buildEntityManagerFactory
     */
    public static EntityManagerFactory getFactory() {
        return entityManagerFactory;
    }

}
