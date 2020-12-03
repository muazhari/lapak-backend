package com.muazhari.findcomputerws.models;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final String category;
    private final String price;

    public Item() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.category = null;
        this.price = null;
    }

    public Item(UUID id, String name, String description, String category, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public UUID getId() {
        return id;
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
