package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.User;
import com.ac.oop.fwrp.service.FoodItemService;
import com.ac.oop.fwrp.service.OrderService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/organization/*")
public class OrganizationController extends HttpServlet {
  private FoodItemService foodItemService = new FoodItemService();
  private OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/claim":
        Long id = Long.parseLong(request.getParameter("id"));
        try {
          request.setAttribute("foodItem", foodItemService.getFoodItemById(id));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/jsp/foodItemClaim.jsp").forward(request, response);
        break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/claim":
        try {
          claimFoodItem(request, response);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/dashboard");
        break;

      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  private void claimFoodItem(HttpServletRequest request, HttpServletResponse response)
      throws IOException, SQLException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    User user = (User) session.getAttribute("user");
    if (user.getType() != 10) {
      response.sendRedirect(request.getContextPath() + "/dashboard");
      return;
    }
    Long foodItemId = Long.parseLong(request.getParameter("id"));
    Integer quantity = Integer.parseInt(request.getParameter("quantity"));
    orderService.claimFoodItem(foodItemId, user.getId(), quantity);
  }
}
