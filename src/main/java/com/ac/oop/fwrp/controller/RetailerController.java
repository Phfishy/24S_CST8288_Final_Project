package com.ac.oop.fwrp.controller;
@WebServlet("/food-item/*")
public class RetailerController extends HttpServlet{
    private FoodItemService foodItemService = new FoodItemService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if ("/add".equals(action)) {
            addFoodItem(request, response);
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

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            item.setExpirationDate(dateFormat.parse(request.getParameter("expirationDate")));

            FoodItem addedItem = foodItemService.addFoodItem(item);
            request.setAttribute("foodItem", addedItem);
            request.getRequestDispatcher("/jsp/food-item-added.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

}
