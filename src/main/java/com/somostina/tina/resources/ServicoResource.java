package com.somostina.tina.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.somostina.tina.domain.Servico;

@RestController
@RequestMapping(value="/servicos")
public class ServicoResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Servico> insert() {
	Servico cat1 = new Servico(1, "Mãos e Pés");
	Servico cat2 = new Servico(2, "Massagem");
	List<Servico> lista = new ArrayList<>();
	lista.add(cat1);
	lista.add(cat2);
	return lista;
	}
}
