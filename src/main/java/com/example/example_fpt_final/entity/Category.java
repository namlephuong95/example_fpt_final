package com.example.example_fpt_final.entity;

import com.example.example_fpt_final.annotation.Column;
import com.example.example_fpt_final.annotation.Entity;
import com.example.example_fpt_final.annotation.Id;
import com.example.example_fpt_final.util.SQLDataTypes;

@Entity(tableName = "categories")
public class Category {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = SQLDataTypes.INTEGER)
    private int id;
    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR50)
    private String name;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
