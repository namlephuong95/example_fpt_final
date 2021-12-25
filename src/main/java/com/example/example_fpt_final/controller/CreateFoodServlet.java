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

public class CreateFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);
    private JpaRepository<Category> categoryJpaRepository = new JpaRepository<>(Category.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = new ArrayList<>();
        categoryList = categoryJpaRepository.findAll();
        req.setAttribute("categories", categoryList);
        req.getRequestDispatcher("/admin/food/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
//            int id = Integer.parseInt(req.getParameter("id"));
            String code = req.getParameter("code");
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            int status = Integer.parseInt(req.getParameter("status"));
            int category = Integer.parseInt(req.getParameter("category"));
            Date dateNow = new Date();
            Food food = new Food(code, name, category, description, thumbnail, price, dateNow, dateNow, status);
            if(food.isValid()){
                foodJpaRepository.save(food);
                resp.sendRedirect("/admin/food/list");
            }else {
                HashMap<String, String> errors = new HashMap<>();
                errors = food.getErrors();
                req.setAttribute("errors", errors);
                req.setAttribute("food", food);
                List<Category> categoryList = new ArrayList<>();
                categoryList = categoryJpaRepository.findAll();
                req.setAttribute("categories", categoryList);
                req.getRequestDispatcher("/admin/food/create.jsp").forward(req,resp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
