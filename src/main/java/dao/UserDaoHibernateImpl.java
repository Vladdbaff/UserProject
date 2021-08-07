package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao{
    private static SessionFactory sessionFactory;
    private Transaction transaction = null;

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM USERS";
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно отчищенна!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void createUserTable() {
        try(Session session = getSessionFactory().openSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "NAME VARCHAR(50) NOT NULL,LASTNAME VARCHAR(50) NOT NULL, " +
                    "AGE TINYINT NOT NULL)";
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана!");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUserTable() {
        try (Session session = getSessionFactory().openSession()) {
            String sql = "DROP TABLE IF EXISTS USERS";
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена!");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        String sql = "FROM User";
        try(Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery(sql, User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public User getUser(long id) {
        User user = null;
        try(Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public void removeUserById(long id) {
        User user = getUser(id);
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
