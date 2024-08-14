package com.basketbandit.outfitter;

import com.basketbandit.outfitter.database.ItemRepository;
import com.basketbandit.outfitter.database.OutfitRepository;
import com.basketbandit.outfitter.database.Season;
import com.basketbandit.outfitter.database.SeasonRepository;
import com.basketbandit.outfitter.entity.Wardrobe;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Application {
	@Resource
	private SeasonRepository seasonRepository;
	@Resource
	private ItemRepository itemRepository;
	@Resource
	private OutfitRepository outfitRepository;
	public static HashMap<String, Season> seasons = new HashMap<>();
	public static HashMap<String, List<String>> categories = new HashMap<>();
	public static Path imageDirectory;
	public static Wardrobe wardrobe;

	public Application() {}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
        try {
			imageDirectory = Path.of(new File(".").getCanonicalPath()+"/data/img/");
			Files.createDirectories(imageDirectory); // Create image directory if it doesn't exist.
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        Yaml yaml = new Yaml();
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application/item-categories.yml")){
			categories = yaml.load(inputStream);
			seasonRepository.findAll().forEach(season -> seasons.put(season.name(), season));
			wardrobe = new Wardrobe(categories, itemRepository.findAll(), outfitRepository.findAll());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
