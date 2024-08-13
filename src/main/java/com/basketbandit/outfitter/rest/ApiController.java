package com.basketbandit.outfitter.rest;

import com.basketbandit.outfitter.Application;
import com.basketbandit.outfitter.database.Item;
import com.basketbandit.outfitter.database.ItemRepository;
import com.basketbandit.outfitter.database.Outfit;
import com.basketbandit.outfitter.database.OutfitRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OutfitRepository outfitRepository;

    @GetMapping("/api/v1/get/item/{id}")
    public String getItem(@PathVariable("id") int id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            return item.get().name() + " " + item.get().subcategory();
        }
        return "Not found.";
    }

    @PostMapping("/api/v1/wardrobe/update")
    public @ResponseBody String updateItem(HttpServletResponse response, @RequestParam Optional<Integer> itemId, @RequestParam Optional<String> name, @RequestParam Optional<String> subcategory, @RequestParam("image") Optional<MultipartFile> image, @RequestParam Optional<Integer> size, @RequestParam Optional<String[]> seasons) {
        try {
            Item item = new Item();

            // Get existing item from database for update if present.
            if(itemId.isPresent()) {
                Optional<Item> temp = itemRepository.findById(itemId.get());
                if(temp.isPresent()) {
                    item = temp.get();
                }
            }

            if(name.isPresent()) {
                item.setName(name.get());
            }

            if(subcategory.isPresent()) {
                item.setSubcategory(subcategory.get());
            }

            if(image.isPresent()) {
                if(!image.get().getOriginalFilename().isEmpty()) {
                    Path imagePath = Path.of(Application.imageDirectory.toString() + "/" + image.get().getOriginalFilename());
                    Files.write(imagePath, image.get().getBytes());
                    item.setImageFilename(image.get().getOriginalFilename());
                }
            }

            if(size.isPresent()) {
                item.setSize(size.get());
            }

            if(seasons.isPresent()) {
                item.setSeasons(seasons.get());
            }

            itemRepository.save(item);
            Application.wardrobe.addItem(item);
            response.sendRedirect("/wardrobe");
            return "Success";
        } catch(Exception e) {
            try { // this is dirt
                e.printStackTrace();
                response.sendRedirect("/wardrobe");
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
            return "Failed";
        }
    }

    @PostMapping("/api/v1/outfit/update")
    public @ResponseBody String updateOutfit(HttpServletResponse response, @RequestParam Optional<Integer> id, @RequestParam Optional<String> name, @RequestParam("image") Optional<MultipartFile> image, @RequestParam Optional<Integer[]> items) {
        try {
            Outfit outfit = new Outfit();

            if(name.isPresent()) {
                outfit.setName(name.get());
            }

            if(image.isPresent()) {
                if(!image.get().getOriginalFilename().isEmpty()) {
                    Path imagePath = Path.of(Application.imageDirectory.toString() + "/" + image.get().getOriginalFilename());
                    Files.write(imagePath, image.get().getBytes());
                    outfit.setImageFilename(image.get().getOriginalFilename());
                }
            }

            if(items.isPresent()) {
                ArrayList<Item> temp = new ArrayList<>();
                Integer[] ids = items.get();
                Arrays.stream(ids).forEach(integer -> {
                    Optional<Item> item = itemRepository.findById(integer);
                    if(item.isPresent()) {
                        temp.add(item.get());
                    }
                });
                outfit.setItems(temp);
            }

            outfitRepository.save(outfit);
            return "Success";
        } catch(Exception e) {
            try { // this is dirt
                e.printStackTrace();
                response.sendRedirect("/outfits");
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
            return "Failed";
        }
    }
}

