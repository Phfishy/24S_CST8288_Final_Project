package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.FoodItem;

import java.sql.*;

public class FoodItemDao {
    public FoodItem createFoodItem(FoodItem item) throws SQLException {
        String sql = "INSERT INTO food_item (retailer_id, name, description, quantity, price, discounted_price, expiration_date, is_surplus, is_for_donation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, item.getRetailerId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getDescription());
            pstmt.setInt(4, item.getQuantity());
            pstmt.setBigDecimal(5, item.getPrice());
            pstmt.setBigDecimal(6, item.getDiscountedPrice());
            pstmt.setTimestamp(7, new Timestamp(item.getExpirationDate().getTime()));
            pstmt.setBoolean(8, item.getSurplus());
            pstmt.setBoolean(9, item.getForDonation());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating food item failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating food item failed, no ID obtained.");
                }
            }
        }
        return item;
    }
}
