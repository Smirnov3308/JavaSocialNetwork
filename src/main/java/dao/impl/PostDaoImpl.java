package dao.impl;

import dao.BaseDao;
import dao.PostDao;
import model.Post;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl extends BaseDao implements PostDao {
    @Override
    protected void createTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            String sql = "CREATE TABLE IF NOT EXISTS post (" +
                    "id INT not null AUTO_INCREMENT," +
                    "userID INT," +
                    "text VARCHAR(255))";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPost(User user, String text) {
        String sql = "INSERT INTO post (userID, text) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, user.getId());
            statement.setString(2, text);
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getMessages(User user) {
        String sql = "SELECT text FROM post WHERE userID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Post> postList = new ArrayList<>();
            while (resultSet.next()) {
                String text = resultSet.getString("text");

                Post post = new Post(text);
                postList.add(post);
            }
            return postList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
