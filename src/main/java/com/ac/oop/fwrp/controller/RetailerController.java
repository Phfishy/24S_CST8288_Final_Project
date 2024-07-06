package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.service.FoodItemService;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/retailer/*")
public class RetailerController extends HttpServlet {
    private FoodItemService foodItemService = new FoodItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/list":
                try {
                    request.setAttribute("foodItems", foodItemService.getAllFoodItems());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("/jsp/foodItemList.jsp").forward(request, response);
                break;
            case "/add":
                request.getRequestDispatcher("/jsp/foodItemCreate.jsp").forward(request, response);
                break;
            case "/update":
                Long id = Long.parseLong(request.getParameter("id"));
                try {
                    request.setAttribute("foodItem", foodItemService.getFoodItemById(id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("/jsp/foodItemUpdate.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/add":
                addFoodItem(request, response);
                break;
            case "/update":
                try {
                    updateFoodItem(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void updateFoodItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Long id = Long.parseLong(request.getParameter("id"));
        FoodItem item = foodItemService.getFoodItemById(id);
        if (item != null) {
            item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            item.setIsSurplus(Boolean.parseBoolean(request.getParameter("isSurplus")));
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                item.setExpirationDate(dateFormat.parse(request.getParameter("expirationDate")));
                foodItemService.updateFoodItem(item);
                request.setAttribute("foodItem", item);
                request.getRequestDispatcher("/jsp/foodItemList.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addFoodItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodItem item = new FoodItem();
        item.setRetailerId(Long.parseLong(request.getParameter("retailerId")));
        item.setName(request.getParameter("name"));
        item.setDescription(request.getParameter("description"));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        item.setPrice(new BigDecimal(request.getParameter("price")));
        item.setDiscountedPrice(new BigDecimal(request.getParameter("discountedPrice")));
        item.setIsSurplus(Boolean.parseBoolean(request.getParameter("isSurplus")));
        item.setIsForDonation(Boolean.parseBoolean(request.getParameter("isForDonation")));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            item.setExpirationDate(dateFormat.parse(request.getParameter("expirationDate")));

            FoodItem addedItem = foodItemService.addFoodItem(item);
            request.setAttribute("foodItem", addedItem);
            request.getRequestDispatcher("/jsp/foodItemList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

}
