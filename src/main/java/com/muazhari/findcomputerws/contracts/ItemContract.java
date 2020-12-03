package com.muazhari.findcomputerws.contracts;

public class ItemContract {
    private final String name;
    private final String description;
    private final String category;
    private final String price;

    public ItemContract(String name, String description, String category, String price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
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
