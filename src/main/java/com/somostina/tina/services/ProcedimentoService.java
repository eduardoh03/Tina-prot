package com.somostina.tina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.dto.ProcedimentoDTO;
import com.somostina.tina.repositories.ProcedimentoRepository;
import com.somostina.tina.repositories.ServicoRepository;
import com.somostina.tina.services.exceptions.DataIntegrityException;
import com.somostina.tina.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository repo;
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Procedimento find(Integer id) {
		Optional<Procedimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + 
				", Tipo: " + Procedimento.class.getName()));
	}
	
	public Procedimento insert(Procedimento obj) {
		obj.setId(null);// impedir que seja uma atualização
		obj = repo.save(obj);
		servicoRepository.save(obj.getServico());
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
	
	private void updateData(Procedimento newObj, Procedimento obj) {
		newObj.setNome(obj.getNome());
		System.out.println(obj.getPreco());
		System.out.println(newObj.getPreco());
		if(obj.getPreco() == null) {
			System.out.println("NAO ALTERADO");
		}else {
			System.out.println("ALTERADO");
			newObj.setPreco(obj.getPreco());
		}
	}
}
