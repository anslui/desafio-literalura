package br.com.alura.desafio.literalura;

import br.com.alura.desafio.literalura.repositorios.AutorRepository;
import br.com.alura.desafio.literalura.repositorios.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepositorio;
	@Autowired
	private AutorRepository autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroRepositorio, autorRepositorio);
		principal.exibeMenu();
		System.exit(0);
	}
}
