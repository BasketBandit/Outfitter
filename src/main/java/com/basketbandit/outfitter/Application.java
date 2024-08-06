package com.basketbandit.outfitter;

import com.basketbandit.outfitter.database.ItemRepository;
import com.basketbandit.outfitter.entity.Wardrobe;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Application {
	@Resource
	private ItemRepository itemRepository;
	public static Wardrobe wardrobe;
	public static HashMap<String, List<String>> categories = new HashMap<>();

	public Application() {}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
		Yaml yaml = new Yaml();
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application/item-categories.yml")){
			categories = yaml.load(inputStream);
			wardrobe = new Wardrobe(categories, itemRepository.findAll());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
