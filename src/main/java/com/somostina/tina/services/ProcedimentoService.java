package com.somostina.tina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.Servico;
import com.somostina.tina.domain.dto.ProcedimentoDTO;
import com.somostina.tina.domain.dto.ProcedimentoNewDTO;
import com.somostina.tina.repositories.ProcedimentoRepository;
import com.somostina.tina.services.exceptions.DataIntegrityException;
import com.somostina.tina.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository repo;
	
	public Procedimento find(Integer id) {
		Optional<Procedimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + 
				", Tipo: " + Procedimento.class.getName()));
	}
	
	public Procedimento insert(Procedimento obj) {
		obj.setId(null);
		try {
			repo.save(obj);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new InvalidDataAccessApiUsageException("Não é possivel criar um Procedimento sem relaciona-lo a um Serviço valido");
		}
		return obj;
	}
	
	public Procedimento update(Procedimento obj) {
		Procedimento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Procedimentos que esta em um Pedido");
		}
	}
	
	public List<Procedimento> findAll(){
		return repo.findAll();
	}
	
	public Procedimento fromDTO(ProcedimentoDTO objDto) {
		return new Procedimento(objDto.getId(),objDto.getNome(), objDto.getPreco(), null);
	}
	
	public Procedimento newProced(ProcedimentoNewDTO objDto) {
		Servico serv = new Servico(objDto.getServicoId(), null);
		Procedimento proc = new Procedimento(null, objDto.getNome(), objDto.getPreco(), serv);
		serv.getProcedimentos().add(proc);
		return proc;
	}
	private void updateData(Procedimento newObj, Procedimento obj) {
		if(obj.getNome() == null) {
		}else {
			newObj.setNome(obj.getNome());
		}
		if(obj.getPreco() == null) {
		}else {
			newObj.setPreco(obj.getPreco());
		}
	}
}
