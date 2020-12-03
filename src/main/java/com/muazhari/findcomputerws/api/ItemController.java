package com.muazhari.findcomputerws.api;


import com.muazhari.findcomputerws.contracts.SearchItemContract;
import com.muazhari.findcomputerws.models.Item;
import com.muazhari.findcomputerws.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/items")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/with/username")
    public List<SearchItemContract> getAllWithUsername() {
        return itemService.getAllWithUsername();
    }


    @GetMapping("/{itemId}")
    public Item getById(@PathVariable("itemId") UUID itemId) {
        return itemService.getById(itemId);
    }

    @DeleteMapping("/{itemId}")
    public Integer deleteById(@PathVariable("itemId") UUID itemId) {
        return itemService.deleteById(itemId);
    }
}
