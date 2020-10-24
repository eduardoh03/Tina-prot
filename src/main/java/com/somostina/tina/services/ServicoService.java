package com.somostina.tina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.somostina.tina.domain.Servico;
import com.somostina.tina.repositories.ServicoRepository;
import com.somostina.tina.services.exceptions.DataIntegrityException;
import com.somostina.tina.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repo;

	public Servico find(Integer id) {
		Optional<Servico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
	}

	public Servico insert(Servico obj) {
		obj.setId(null);// impedir que seja uma atualização
		return repo.save(obj);
	}

	public Servico update(Servico obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Serviço que possui Procedimentos");
		}
	}
	
	public List<Servico> findAll(){
		return repo.findAll();
	}
}
