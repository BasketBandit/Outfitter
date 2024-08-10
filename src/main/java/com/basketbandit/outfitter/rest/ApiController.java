package com.basketbandit.outfitter.rest;

import com.basketbandit.outfitter.Application;
import com.basketbandit.outfitter.database.Item;
import com.basketbandit.outfitter.database.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/api/v1/wardrobe/add")
    public @ResponseBody String addItem(@RequestParam String name, @RequestParam String subcategory, @RequestParam("image") MultipartFile image, @RequestParam int size, @RequestParam String[] seasons) {
        try {
            Files.createDirectories(Path.of("/data/img/"));
            Path path = Paths.get("/data/img/", image.getOriginalFilename());
            Files.write(path, image.getBytes());
            Item item = new Item(name, subcategory, "/data/img/"+image.getOriginalFilename(), size, seasons);
            itemRepository.save(item);
            Application.wardrobe.addItem(item);
            return "Success";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}


