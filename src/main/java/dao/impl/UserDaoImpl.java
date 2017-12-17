package dao.impl;

import dao.BaseDao;
import dao.UserDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    protected void createTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            String sql = "CREATE TABLE IF NOT EXISTS user (" +
                    "id INT PRIMARY KEY," +
                    "firstName VARCHAR(255)," +
                    "lastName VARCHAR(255)," +
                    "login VARCHAR(255)," +
                    "password VARCHAR(255))";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user (id, firstName, lastName, login, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editUser(User user) {
        String sql = "UPDATE user SET firstName = ?, lastName = ?, login = ?, password = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM user";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUser(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                User user = new User(id, login, password, firstName, lastName);
                return user;
            }
            throw new RuntimeException("The user is not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getUserList() {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(id, login, password, firstName, lastName);
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
}
