package com.example.example_fpt_final.entity;

import com.example.example_fpt_final.annotation.*;
import com.example.example_fpt_final.util.SQLDataTypes;

import java.util.Date;
import java.util.HashMap;

@Entity(tableName = "foods")
public class Food {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = SQLDataTypes.INTEGER)
    private int id;
    @Column(columnName = "code", columnType = SQLDataTypes.VARCHAR50)
    private String code;
    @Column(columnName = "name", columnType = SQLDataTypes.VARCHAR50)
    private String name;
    @Column(columnName = "category", columnType = SQLDataTypes.INTEGER)
    private int category;
    @Column(columnName = "description", columnType = SQLDataTypes.VARCHAR255)
    private String description;
    @Column(columnName = "thumbnail", columnType = SQLDataTypes.TEXT)
    private String thumbnail;
    @Column(columnName = "price", columnType = SQLDataTypes.DOUBLE)
    private double price;
    @Column(columnName = "created_at", columnType = SQLDataTypes.DATETIME)
    private Date created_at;
    @Column(columnName = "upodated_at", columnType = SQLDataTypes.DATETIME)
    private Date upodated_at;
    @Column(columnName = "status", columnType = SQLDataTypes.INTEGER)
    private int status;

    public Food() {
        this.name = "";
        this.code = "";
        this.description = "";
        this.thumbnail = "";
        this.status = 1;
        this.category = 1;
    }

    public Food(String code, String name, int category, String description, String thumbnail, double price, Date created_at, Date upodated_at, int status) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.created_at = created_at;
        this.upodated_at = upodated_at;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", created_at=" + created_at +
                ", upodated_at=" + upodated_at +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpodated_at() {
        return upodated_at;
    }

    public void setUpodated_at(Date upodated_at) {
        this.upodated_at = upodated_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isValid() {
        return getErrors().size() == 0;
    }

    public HashMap<String, String> getErrors(){
        HashMap<String, String> errors = new HashMap<>();
        if (name == null || name.length() <= 7){
            errors.put("name", "Name cannot be empty or less than 7 characters");
        }
        if(price <= 0){
            errors.put("price", "Price should not be less than 0");
        }
        return errors;
    }
}
