package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            String createSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(50),"
                    + "lastName VARCHAR(50),"
                    + "age INT)";

            session.beginTransaction();
            session.createNativeQuery(createSQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Created users table");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            String dropSQL = "DROP TABLE IF EXISTS users";
            session.beginTransaction();
            session.createSQLQuery(dropSQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Dropped users table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Saved user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            System.out.println("Removed user with id " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
            System.out.println("Found " + users.size() + " users");
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Deleted users from table");
        }
    }
}
