package br.com.jovasdevs.padraospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  Projeto Spring Boot gerado via Spring Initizlizr.
 *  Módulos selecionados:
 *  Spring Data JPS
 *  Spring Web
 *  H2 Database
 *  OpenFeign
 *
 * @author jovasdevs
 *
 */

@SpringBootApplication
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
