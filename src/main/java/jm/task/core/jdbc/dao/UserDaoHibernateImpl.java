package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            String createSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(50),"
                    + "lastName VARCHAR(50),"
                    + "age INT)";

            transaction = session.beginTransaction();
            session.createNativeQuery(createSQL).executeUpdate();
            transaction.commit();
            System.out.println("Created users table");
        } catch (Exception e) {
            doRollbackTransaction(transaction);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            String dropSQL = "DROP TABLE IF EXISTS users";
            transaction = session.beginTransaction();
            session.createSQLQuery(dropSQL).executeUpdate();
            transaction.commit();
            System.out.println("Dropped users table");
        } catch (Exception e) {
            doRollbackTransaction(transaction);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("Saved user");
        } catch (Exception e) {
            doRollbackTransaction(transaction);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
            System.out.println("Removed user with id " + id);
        } catch (Exception e) {
            doRollbackTransaction(transaction);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            System.out.println("Found " + users.size() + " users");
            return users;
        } catch (Exception e) {
            doRollbackTransaction(transaction);
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("Deleted users from table");
        } catch (Exception e) {
            doRollbackTransaction(transaction);
        }
    }

    private void doRollbackTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
