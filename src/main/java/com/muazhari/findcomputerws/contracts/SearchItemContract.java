package com.muazhari.findcomputerws.contracts;

public class SearchItemContract {
    private final String username;
    private final String name;
    private final String description;
    private final String category;
    private final String price;

    public SearchItemContract(String username, String name, String description, String category, String price) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }


    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }
}
