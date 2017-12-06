package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
public abstract class BaseDao {
    private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/SocialNetwork";
    private final String USER = "root";
    private final String PASSWORD = "pass";

    public BaseDao() { createTable(); }

    protected abstract void createTable();

    public Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
public abstract class BaseDao {
    private final static String DRIVER_CLASS_NAME = "org.h2.Driver";

    public BaseDao() {
        createTable();
    }

    protected abstract void createTable();

    public Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS_NAME);

            Connection connection = DriverManager.getConnection("jdbc:h2:~/DB",
                    "sa", "");
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
