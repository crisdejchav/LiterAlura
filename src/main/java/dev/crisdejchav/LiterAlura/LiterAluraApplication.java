package dev.crisdejchav.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.crisdejchav.LiterAlura.Main.Main;
import dev.crisdejchav.LiterAlura.repository.AuthorRepository;
import dev.crisdejchav.LiterAlura.repository.BookRepository;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository Brepository;

	@Autowired
	private AuthorRepository Arepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(Brepository, Arepository);
		main.showMenu();
	}
}
