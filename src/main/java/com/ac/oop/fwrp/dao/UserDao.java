package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.User;

import java.sql.*;

public class UserDao {
    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, email, type, address, contract_number, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getType());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getContractNumber());
            pstmt.setString(6, user.getPassword());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return user;
    }

}
