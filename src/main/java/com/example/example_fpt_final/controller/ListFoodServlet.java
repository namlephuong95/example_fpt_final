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
import java.util.HashMap;

public class ListFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);
    private JpaRepository<Category> categoryJpaRepository = new JpaRepository<>(Category.class);
    public static ArrayList<Food> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int perPage = 5;
        int totalPage;
        list = (ArrayList<Food>) foodJpaRepository.where("status", "!=", 3);
        HashMap<Category, Food> reulstList = new HashMap<>();
        for (Food obj :
                list) {
            Category category = categoryJpaRepository.findById(obj.getCategory());
            reulstList.put(category, obj);
        }

        if((req.getParameter("page")!=null)){
            page = Integer.parseInt(req.getParameter("page"));
        }
        if(req.getParameter("perPage")!=null){
            perPage = Integer.parseInt(req.getParameter("perPage"));
        }

        req.setAttribute("list", reulstList);
        req.getRequestDispatcher("/admin/food/list.jsp").forward(req, resp);
    }
}
