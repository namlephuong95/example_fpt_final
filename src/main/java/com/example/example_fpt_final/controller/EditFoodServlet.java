package com.example.example_fpt_final.controller;

import com.example.example_fpt_final.entity.Category;
import com.example.example_fpt_final.entity.Food;
import com.example.example_fpt_final.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EditFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);
    private JpaRepository<Category> categoryJpaRepository = new JpaRepository<>(Category.class);
    public static List<Category> categories = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Food food = foodJpaRepository.findById(id);
            if (food == null) {
                resp.getWriter().println("Food is not found!");
            } else {
                categories = categoryJpaRepository.findAll();
                req.setAttribute("categories", categories);
                req.setAttribute("food", food);
                req.getRequestDispatcher("/admin/food/edit.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Food food = foodJpaRepository.findById(id);
        if(food == null){
            resp.getWriter().println("Food is not found!");
        }else {
            String code = req.getParameter("code");
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            int status = Integer.parseInt(req.getParameter("status"));
            int category = Integer.parseInt(req.getParameter("category"));
            Date dateNow = new Date();

            food.setCode(code);
            food.setName(name);
            food.setPrice(price);
            food.setDescription(description);
            food.setThumbnail(thumbnail);
            food.setStatus(status);
            food.setCategory(category);
            food.setUpodated_at(dateNow);

            if(food.isValid()){
                foodJpaRepository.update(food);
                resp.sendRedirect("/admin/food/list");
            }else{
                HashMap<String, String> errors = new HashMap<>();
                errors = food.getErrors();
                errors.put("status", "Food is on selling, cannot be updated. Please change status with update");
                req.setAttribute("errors", errors);
                req.setAttribute("food", food);
                categories = categoryJpaRepository.findAll();
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/admin/food/edit.jsp").forward(req,resp);
            }
        }

    }
}
