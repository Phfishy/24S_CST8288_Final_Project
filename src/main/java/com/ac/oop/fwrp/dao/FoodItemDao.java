package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDao {

    // Create (Insert) a new food item
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
            pstmt.setBoolean(8, item.getIsSurplus());
            pstmt.setBoolean(9, item.getIsForDonation());
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

    // Read (Retrieve) a food item by ID
    public FoodItem getFoodItemById(long id) throws SQLException {
        String sql = "SELECT * FROM food_item WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractFoodItemFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Read (Retrieve) all food items
    public List<FoodItem> getAllFoodItems() throws SQLException {
        String sql = "SELECT * FROM food_item";
        List<FoodItem> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                items.add(extractFoodItemFromResultSet(rs));
            }
        }
        return items;
    }

    // Update an existing food item
    public boolean updateFoodItem(FoodItem item) throws SQLException {
        String sql = "UPDATE food_item SET retailer_id = ?, name = ?, description = ?, quantity = ?, price = ?, discounted_price = ?, expiration_date = ?, is_surplus = ?, is_for_donation = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, item.getRetailerId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getDescription());
            pstmt.setInt(4, item.getQuantity());
            pstmt.setBigDecimal(5, item.getPrice());
            pstmt.setBigDecimal(6, item.getDiscountedPrice());
            pstmt.setTimestamp(7, new Timestamp(item.getExpirationDate().getTime()));
            pstmt.setBoolean(8, item.getIsSurplus());
            pstmt.setBoolean(9, item.getIsForDonation());
            pstmt.setLong(10, item.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Delete a food item
    public boolean deleteFoodItem(long id) throws SQLException {
        String sql = "DELETE FROM food_item WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Read (Retrieve) food items by retailer ID
    public List<FoodItem> getFoodItemsByRetailerId(long retailerId) throws SQLException {
        String sql = "SELECT * FROM food_item WHERE retailer_id = ?";
        List<FoodItem> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, retailerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(extractFoodItemFromResultSet(rs));
                }
            }
        }
        return items;
    }

    public List<FoodItem> getFoodItemsByDonationStatus(boolean isForDonation) throws SQLException {
        String sql = "SELECT * FROM food_item WHERE is_for_donation = ?";
        List<FoodItem> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isForDonation);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(extractFoodItemFromResultSet(rs));
                }
            }
        }
        return items;
    }

    public List<FoodItem> getDiscountedFoodItems() throws SQLException {
        String sql = "SELECT * FROM food_item WHERE is_for_donation = false AND discounted_price IS NOT NULL";
        List<FoodItem> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(extractFoodItemFromResultSet(rs));
                }
            }
        }
        return items;
    }

    // Helper method to extract a FoodItem object from a ResultSet
    private FoodItem extractFoodItemFromResultSet(ResultSet rs) throws SQLException {
        FoodItem item = new FoodItem();
        item.setId(rs.getLong("id"));
        item.setRetailerId(rs.getLong("retailer_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setDiscountedPrice(rs.getBigDecimal("discounted_price"));
        item.setExpirationDate(rs.getTimestamp("expiration_date"));
        item.setIsSurplus(rs.getBoolean("is_surplus"));
        item.setIsForDonation(rs.getBoolean("is_for_donation"));
        return item;
    }

    public void markAsSurplus(Long id) {
        String sql = "UPDATE food_item SET is_surplus = true WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}