package com.muazhari.findcomputerws.models;

import java.util.UUID;

public class UserItemLink {
    private final UUID id;
    private final UUID userId;
    private final UUID itemId;

    public UserItemLink() {
        this.id = null;
        this.userId = null;
        this.itemId = null;
    }

    public UserItemLink(UUID id, UUID userId, UUID itemId) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getItemId() {
        return itemId;
    }
}
