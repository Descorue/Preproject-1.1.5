package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.SQLException;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    private final static UserService userServiceH = new UserServiceImpl();

    public static void main(String[] args) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        System.out.println(sessionFactory.isClosed());


        userServiceH.createUsersTable();


        userServiceH.saveUser("Volodya", "Volodin", (byte) 55);
        userServiceH.saveUser("Valera", "Valerin", (byte) 51 );
        userServiceH.saveUser("Bogdan", "Bogdanov", (byte) 53);
        userServiceH.saveUser("Vasiliy", "Vasiliyev", (byte) 54);

        userServiceH.removeUserById(1);
        userServiceH.getAllUsers();
        userServiceH.cleanUsersTable();
        userServiceH.dropUsersTable();
    }
}
