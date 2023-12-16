package com.example.javee.Models;

public class Category {
    private Long id;
    private String category_name, discription;

    public Category() {
    }

    public Category(Long id, String category_name, String discription) {
        this.id = id;
        this.category_name = category_name;
        this.discription = discription;
    }

    public Category(String category_name, String discription) {
        this.category_name = category_name;
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String category_Name) {
        this.category_name = category_Name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + category_name + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }
}
