package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.Subscription;
import com.ac.oop.fwrp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDao {

    public Subscription createSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscription (user_id, communication_method, food_preference, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, subscription.getUserId());
            pstmt.setInt(2, subscription.getCommunicationMethod());
            pstmt.setString(3, subscription.getFoodPreferences());
            pstmt.setDouble(4, subscription.getLatitude());
            pstmt.setDouble(5, subscription.getLongitude());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating subscription failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subscription.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating subscription failed, no ID obtained.");
                }
            }
        }
        return subscription;
    }

    public Subscription getSubscription(long id) throws SQLException {
        String sql = "SELECT * FROM subscription WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSubscription(rs);
                }
            }
        }
        return null;
    }

    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscription";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                subscriptions.add(mapResultSetToSubscription(rs));
            }
        }
        return subscriptions;
    }

    public boolean updateSubscription(Subscription subscription) throws SQLException {
        String sql = "UPDATE subscription SET user_id = ?, communication_method = ?, food_preference = ? , latitude = ?, longitude = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, subscription.getUserId());
            pstmt.setInt(2, subscription.getCommunicationMethod());
            pstmt.setString(3, subscription.getFoodPreferences());
            pstmt.setDouble(5, subscription.getLatitude());
            pstmt.setDouble(6, subscription.getLongitude());
            pstmt.setLong(7, subscription.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteSubscription(long id) throws SQLException {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    private Subscription mapResultSetToSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getLong("id"));
        subscription.setUserId(rs.getLong("user_id"));
        subscription.setCommunicationMethod(rs.getInt("communication_method"));
        subscription.setFoodPreferences(rs.getString("food_preference"));
        subscription.setLatitude(rs.getDouble("latitude"));
        subscription.setLongitude(rs.getDouble("longitude"));
        return subscription;
    }
}
