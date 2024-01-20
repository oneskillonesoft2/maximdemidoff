package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static final String url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String userName = "postgres";
    public static final String password = "root";
    private static final String DRIVER = "org.postgresql.Driver";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Соединениние успешно...");
        } catch (SQLException e) {
            System.out.println("Соединениние не успешно...");
            throw new RuntimeException(e);

        }

        return conn;
    }

    private static SessionFactory sessionFactory;

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", url);
        properties.setProperty("hibernate.connection.username", userName);
        properties.setProperty("hibernate.connection.password", password);
        properties.setProperty("hibernate.connection.driver_class", DRIVER);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "utf8");
        properties.setProperty("hibernate.current_session_context_class", "thread");

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.setProperties(getProperties());
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}

