package com.muazhari.findcomputerws.api;

import com.muazhari.findcomputerws.contracts.ItemContract;
import com.muazhari.findcomputerws.models.Item;
import com.muazhari.findcomputerws.models.UserItemLink;
import com.muazhari.findcomputerws.services.UserItemLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class UserItemLinkController {
    private final UserItemLinkService userItemLinkService;

    public UserItemLinkController(UserItemLinkService userItemLinkService) {
        this.userItemLinkService = userItemLinkService;
    }

    @PostMapping("/useritemlink")
    public UserItemLink create(@RequestBody UserItemLink userItemLink) {
        return userItemLinkService.create(userItemLink);
    }

    @GetMapping
    public List<UserItemLink> getAll() {
        return userItemLinkService.getAll();
    }

    @GetMapping("/users/{username}/items/{itemId}")
    public Item getById(@PathVariable("username") String username,
                        @PathVariable("itemId") UUID itemId) {
        return userItemLinkService.getByUsernameAndItemId(username, itemId);
    }

    @GetMapping("/users/{username}/items")
    public List<Item> getUserItems(@PathVariable("username") String username) {
        return userItemLinkService.getAllItemByUsername(username);
    }

    @PostMapping("/users/{username}/items")
    public Item getUserItems(@PathVariable("username") String username, @RequestBody ItemContract itemContract) {
        return userItemLinkService.createItem(username, itemContract);
    }

    @PutMapping("/users/{username}/items/{itemId}")
    public Integer updateUserItems(@PathVariable("username") String username,
                                   @PathVariable("itemId") UUID itemId,
                                   @RequestBody ItemContract itemContract) {
        return userItemLinkService.updateItem(username, itemId, itemContract);
    }

    @DeleteMapping("/users/{username}/items/{itemId}")
    public Integer deleteUserItems(@PathVariable("username") String username,
                                   @PathVariable("itemId") UUID itemId) {
        return userItemLinkService.deleteItem(username, itemId);
    }
}
