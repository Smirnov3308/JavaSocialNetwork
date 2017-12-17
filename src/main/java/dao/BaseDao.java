package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {
    private final static String DRIVER_CLASS_NAME = "org.h2.Driver";

    public BaseDao() {
        createTable();
    }

    protected abstract void createTable();

    public Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS_NAME);

            Connection connection = DriverManager.getConnection("jdbc:h2:~/DBtest",
                    "Vadim", "1230");
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
