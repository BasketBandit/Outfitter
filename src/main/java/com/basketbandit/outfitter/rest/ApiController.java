package com.basketbandit.outfitter.rest;

import com.basketbandit.outfitter.database.Item;
import com.basketbandit.outfitter.database.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/v1/get/item/{id}")
    public String getItem(@PathVariable("id") int id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            return item.get().name() + " " + item.get().subcategory();
        }
        return "Not found.";
    }

    @PostMapping("/api/v1/add/item")
    public @ResponseBody String addItem(@RequestParam String name, @RequestParam String category, @RequestParam String subcategory, @RequestParam String imagePath) {
        //Item item = new Item(name, category, subcategory, imagePath);
        //itemRepository.save(item);
        return "Saved";
    }
}


