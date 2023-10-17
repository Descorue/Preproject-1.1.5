package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;


public class Main {

    private final static UserService userServiceHiberImpl = new UserServiceImpl();

    public static void main(String[] args) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        System.out.println(sessionFactory.isClosed());


        userServiceHiberImpl.createUsersTable();


        userServiceHiberImpl.saveUser("Volodya", "Volodin", (byte) 55);
        userServiceHiberImpl.saveUser("Valera", "Valerin", (byte) 51 );
        userServiceHiberImpl.saveUser("Bogdan", "Bogdanov", (byte) 53);
        userServiceHiberImpl.saveUser("Vasiliy", "Vasiliyev", (byte) 54);

        userServiceHiberImpl.removeUserById(1);
        userServiceHiberImpl.getAllUsers();
        userServiceHiberImpl.cleanUsersTable();
        userServiceHiberImpl.dropUsersTable();
    }
}
