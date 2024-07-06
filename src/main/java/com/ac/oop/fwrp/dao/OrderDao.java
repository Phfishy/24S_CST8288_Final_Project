package com.ac.oop.fwrp.dao;

import com.ac.oop.fwrp.model.Order;
import com.ac.oop.fwrp.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao { // Create (Insert) a new order
  public Order createOrder(Order order) throws SQLException {
    String sql =
        "INSERT INTO `order` (item_id, buyer_id, quantity, transaction_type) VALUES (?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setLong(1, order.getItemId());
      pstmt.setLong(2, order.getBuyerId());
      pstmt.setInt(3, order.getQuantity());
      pstmt.setInt(4, order.getTransactionType());

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating order failed, no rows affected.");
      }
      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          order.setId(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Creating order failed, no ID obtained.");
        }
      }
    }

    return order;
  }

  // Read (Retrieve) an order by ID
  public Order getOrderById(long id) throws SQLException {
    String sql = "SELECT * FROM `order` WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setLong(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return extractOrderFromResultSet(rs);
        }
      }
    }

    return null;
  }

  // Read (Retrieve) all orders
  public List<Order> getAllOrders() throws SQLException {
    String sql = "SELECT * FROM `order`";
    List<Order> orders = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        orders.add(extractOrderFromResultSet(rs));
      }
    }

    return orders;
  }

  // Update an existing order
  public boolean updateOrder(Order order) throws SQLException {
    String sql =
        "UPDATE `order` SET item_id = ?, buyer_id = ?, quantity = ?, checkout_time = ?, transaction_type = ?, status = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setLong(1, order.getItemId());
      pstmt.setLong(2, order.getBuyerId());
      pstmt.setInt(3, order.getQuantity());
      pstmt.setTimestamp(4, new Timestamp(order.getCheckoutTime().getTime()));
      pstmt.setInt(5, order.getTransactionType());
      pstmt.setInt(6, order.getStatus());
      pstmt.setLong(7, order.getId());

      int affectedRows = pstmt.executeUpdate();
      return affectedRows > 0;
    }
  }

  // Delete an order
  public boolean deleteOrder(long id) throws SQLException {
    String sql = "DELETE FROM `order` WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setLong(1, id);

      int affectedRows = pstmt.executeUpdate();
      return affectedRows > 0;
    }
  }

  // Helper method to extract an Order object from a ResultSet
  private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
    Order order = new Order();
    order.setId(rs.getLong("id"));
    order.setItemId(rs.getLong("item_id"));
    order.setBuyerId(rs.getLong("buyer_id"));
    order.setQuantity(rs.getInt("quantity"));
    order.setCheckoutTime(rs.getTimestamp("checkout_time"));
    order.setTransactionType(rs.getInt("transaction_type"));
    order.setStatus(rs.getInt("status"));
    return order;
  }
}
