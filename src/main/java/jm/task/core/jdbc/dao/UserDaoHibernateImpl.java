package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, last_Name VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(create).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(drop).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null){
                session.remove(user);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "DELETE from User users";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(clean).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
        }
    }
}