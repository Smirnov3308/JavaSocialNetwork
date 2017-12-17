package dao.impl;

import dao.BaseDao;
import dao.PrivateMessageDao;
import model.PrivateMessage;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrivateMessageDaoImpl extends BaseDao implements PrivateMessageDao {

    @Override
    protected void createTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            String sql = "CREATE TABLE IF NOT EXISTS privateMessage (" +
                    "id INT not null AUTO_INCREMENT," +
                    "senderID INT," +
                    "receiverID INT," +
                    "text VARCHAR(255)," +
                    "rating INT)";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPrivateMessage(User sender, User receiver, String text) {
        String sql = "INSERT INTO privateMessage (senderID, receiverID, text) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, sender.getId());
            statement.setInt(2, receiver.getId());
            statement.setString(3, text);
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrivateMessage> getMessages(User sender) {
        String sql = "SELECT senderID, receiverID, text, rating FROM privateMessage WHERE senderID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, sender.getId());
            ResultSet resultSet = statement.executeQuery();

            List<PrivateMessage> messages = new ArrayList<>();
            while (resultSet.next()) {
                int senderID = resultSet.getInt("senderID");
                int receiverID = resultSet.getInt("receiverID");
                String text = resultSet.getString("text");
                int ratng = resultSet.getInt("rating");

                PrivateMessage message = new PrivateMessage(senderID, receiverID, text);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PrivateMessage> getMessages(User sender, User receiver) {
        String sql = "SELECT senderID, receiverID, text, rating FROM privateMessage WHERE senderID = ? AND receiverID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, sender.getId());
            statement.setInt(2, receiver.getId());
            ResultSet resultSet = statement.executeQuery();

            List<PrivateMessage> messages = new ArrayList<>();
            while (resultSet.next()) {
                int senderID = resultSet.getInt("senderID");
                int receiverID = resultSet.getInt("receiverID");
                String text = resultSet.getString("text");
                int ratng = resultSet.getInt("rating");

                PrivateMessage message = new PrivateMessage(senderID, receiverID, text);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
