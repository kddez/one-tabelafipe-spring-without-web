package br.com.alura.fipe;

import br.com.alura.fipe.main.MainApplication;
import br.com.alura.fipe.service.APIConsumer;
import br.com.alura.fipe.service.DataConverter;
import com.sun.tools.javac.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApplication.class, args);
	}

	@Override
	public void run(String... args){
		MainApplication mainApplication = new MainApplication(new APIConsumer(), new DataConverter());
		mainApplication.showMenu();
	}
}
