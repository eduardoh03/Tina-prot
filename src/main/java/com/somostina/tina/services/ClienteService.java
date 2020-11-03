package com.somostina.tina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.somostina.tina.domain.Cidade;
import com.somostina.tina.domain.Cliente;
import com.somostina.tina.domain.Endereco;
import com.somostina.tina.domain.dto.ClienteDTO;
import com.somostina.tina.domain.dto.ClienteNewDTO;
import com.somostina.tina.repositories.ClienteRepository;
import com.somostina.tina.repositories.EnderecoRepository;
import com.somostina.tina.services.exceptions.DataIntegrityException;
import com.somostina.tina.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque o Cliente possuir Pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), null, null);

	}

	public Cliente newCli(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getCpf(),
				objDto.getSexo());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		if (obj.getNome() == null) {
		} else {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail() == null) {
		} else {
			newObj.setEmail(obj.getEmail());
		}
		if (obj.getTelefone() == null) {
		} else {
			newObj.setTelefone(obj.getTelefone());
		}
	}
}
