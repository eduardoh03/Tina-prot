package com.somostina.tina;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.Servico;
import com.somostina.tina.repositories.ProcedimentoRepository;
import com.somostina.tina.repositories.ServicoRepository;

@SpringBootApplication
public class TinaApplication implements CommandLineRunner {

	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TinaApplication.class, args);
		}

		public void run(String... args) throws Exception {
			Servico serv1 = new Servico(null, "Cabelo");
			Servico serv2 = new Servico(null, "Maquiagem");
			
			Procedimento p1 = new Procedimento(null, "Corte Feminino", 50.00);
			Procedimento p2 = new Procedimento(null, "Corte Masculino", 30.00);
			Procedimento p3 = new Procedimento(null, "Hidratação", 150.00);
			Procedimento p4 = new Procedimento(null, "Maquiagem Artistica", 200.00);
			Procedimento p5 = new Procedimento(null, "Maquiagem + Cílios", 150.00);
			
			serv1.getProcedimentos().addAll(Arrays.asList(p1, p2, p3));
			serv2.getProcedimentos().addAll(Arrays.asList(p4, p5));

			p1.getServicos().addAll(Arrays.asList(serv1));
			p2.getServicos().addAll(Arrays.asList(serv1));
			p3.getServicos().addAll(Arrays.asList(serv1));
			p4.getServicos().addAll(Arrays.asList(serv2));
			p5.getServicos().addAll(Arrays.asList(serv2));
			
			servicoRepository.saveAll(Arrays.asList(serv1, serv2));
			procedimentoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		}

}
