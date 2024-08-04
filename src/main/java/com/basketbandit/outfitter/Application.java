package com.basketbandit.outfitter;

import com.basketbandit.outfitter.entity.Category;
import com.basketbandit.outfitter.entity.Item;
import com.basketbandit.outfitter.entity.Outfit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Application {
	public static ArrayList<Item> items = new ArrayList<>();
	public static ArrayList<Outfit> outfits = new ArrayList<>();
	public static ArrayList<Category> categories = new ArrayList<>();

	public Application() {
		Yaml yaml = new Yaml();
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application/item-categories.yml")){
			HashMap<String, List<String>> c = (HashMap<String, List<String>>) yaml.load(inputStream);
			c.keySet().forEach(key -> categories.add(new Category(key, c.get(key))));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Categories parsed: " + categories.size());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
