package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.service.FoodItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@WebServlet("/consumer/*")
public class ConsumerController extends HttpServlet {
  private FoodItemService foodItemService = new FoodItemService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/purchase":
        Long id = Long.parseLong(request.getParameter("id"));
          try {
            request.setAttribute("foodItem", foodItemService.getFoodItemById(id));
            } catch (Exception e) {
            throw new RuntimeException(e);
            }
        request.getRequestDispatcher("/jsp/foodItemPurchase.jsp").forward(request, response);
        break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String action = request.getPathInfo();
    switch (action) {
      case "/purchase":
        purchaseFoodItem(request, response);
        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
        break;

      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  private void purchaseFoodItem(HttpServletRequest request, HttpServletResponse response) {
    Long id = Long.parseLong(request.getParameter("id"));
    Integer quantity = Integer.parseInt(request.getParameter("quantity"));

  }
}
