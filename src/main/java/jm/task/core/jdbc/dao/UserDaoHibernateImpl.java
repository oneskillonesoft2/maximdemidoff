package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "Id SERIAL PRIMARY KEY NOT NULL," +
                "Name CHARACTER VARYING(30)," +
                "LastName CHARACTER VARYING(30)," +
                "Age INTEGER);";

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User>query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users";

        Sql(sql);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users(name, lastname, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";

        Sql(sql);
    }

    private void Sql(String sql) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }catch (QueryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE id = " + id;

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User>query = session.createNativeQuery(sql);
            session.createSQLQuery("DELETE FROM users WHERE id = " + id);
            query.executeUpdate();
            transaction.commit();
            if (id > 0) {
                System.out.println("User с id " + id + " удалён из базы данных");
            } else {
                System.out.println("Ошибка! id должен быть больше нуля.");
            }
        }catch (QueryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User>users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "DELETE FROM Users";

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }catch (QueryException e) {
            e.printStackTrace();
        }
    }
}