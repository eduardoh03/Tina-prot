package com.somostina.tina.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.dto.ProcedimentoDTO;
import com.somostina.tina.services.ProcedimentoService;

@RestController
@RequestMapping(value = "/procedimento")
public class ProcedimentoResource {

	@Autowired
	private ProcedimentoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Procedimento> find(@PathVariable Integer id) {
		Procedimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProcedimentoDTO objDto, @PathVariable Integer id){
		Procedimento obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Procedimento>> findAll() {
		List<Procedimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}

