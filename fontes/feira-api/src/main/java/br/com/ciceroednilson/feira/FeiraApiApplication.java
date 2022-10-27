package br.com.ciceroednilson.feira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.ciceroednilson.*"})
public class FeiraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeiraApiApplication.class, args);
	}

}
