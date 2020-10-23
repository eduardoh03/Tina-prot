package com.somostina.tina;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.somostina.tina.domain.Servico;
import com.somostina.tina.repositories.ServicoRepository;

@SpringBootApplication
public class TinaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TinaApplication.class, args);
		}
		@Autowired
		private ServicoRepository servicoRepository;
		public void run(String... args) throws Exception {
			Servico serv1 = new Servico(null, "Cabelo");
			Servico serv2 = new Servico(null, "Maquiagem");

			servicoRepository.saveAll(Arrays.asList(serv1, serv2));
		}

}
