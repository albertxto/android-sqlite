package com.example.albert.sqlite;

public class Products {
    private int id;
    private String name;
    private String description;

    //Constructor
    public Products(){
        //Empty Constructor
    }

    public Products(String name){
        this.name = name;
    }

    //Getter & Setter
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
