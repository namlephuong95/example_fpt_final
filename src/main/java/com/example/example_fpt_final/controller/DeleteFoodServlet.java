package com.example.example_fpt_final.controller;

import com.example.example_fpt_final.entity.Food;
import com.example.example_fpt_final.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Food food = foodJpaRepository.findById(id);
            if (food == null) {
                resp.getWriter().println("Food is not found!");
//            } else if (food.getStatus() == 1) {
//                resp.getWriter().println("Food is on selling, cannot be deleted");
            } else {
                food.setStatus(3);
                foodJpaRepository.update(food);
                resp.getWriter().println("Delete food successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
