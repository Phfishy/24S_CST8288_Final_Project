package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.Alert;
import com.ac.oop.fwrp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDao {
    public Alert createAlert(Alert alert) throws SQLException {
        String sql = "INSERT INTO alert (user_id, item_id, type, alert_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, alert.getUserId());
            pstmt.setLong(2, alert.getItemId());
            pstmt.setInt(3, alert.getType());
            pstmt.setTimestamp(4, new Timestamp(alert.getAlertTime().getTime()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating alert failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alert.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating alert failed, no ID obtained.");
                }
            }
        }
        return alert;
    }

    public Alert getAlert(long id) throws SQLException {
        String sql = "SELECT * FROM alert WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAlert(rs);
                }
            }
        }
        return null;
    }

    public List<Alert> getAllAlerts() throws SQLException {
        List<Alert> alerts = new ArrayList<>();
        String sql = "SELECT * FROM alert";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alerts.add(mapResultSetToAlert(rs));
            }
        }
        return alerts;
    }

    public boolean updateAlert(Alert alert) throws SQLException {
        String sql = "UPDATE alert SET user_id = ?, item_id = ?, type = ?, alert_time = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, alert.getUserId());
            pstmt.setLong(2, alert.getItemId());
            pstmt.setInt(3, alert.getType());
            pstmt.setTimestamp(4, new Timestamp(alert.getAlertTime().getTime()));
            pstmt.setLong(5, alert.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteAlert(long id) throws SQLException {
        String sql = "DELETE FROM alert WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    private Alert mapResultSetToAlert(ResultSet rs) throws SQLException {
        Alert alert = new Alert();
        alert.setId(rs.getLong("id"));
        alert.setUserId(rs.getLong("user_id"));
        alert.setItemId(rs.getLong("item_id"));
        alert.setType(rs.getInt("type"));
        alert.setAlertTime(rs.getTimestamp("alert_time"));
        return alert;
    }
}
