package com.concessionaria.app.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.concessionaria.app.controller.dto.request.CarroRequest;
import com.concessionaria.app.model.Carro;
import com.concessionaria.app.model.Cliente;
import com.concessionaria.app.repository.CarroRepository;
import com.concessionaria.app.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes/{idCliente}/carros")
public class CarroController {

	private CarroRepository repository;
	private ClienteRepository clienteRepository;
	
	public CarroController(CarroRepository repository, ClienteRepository clienteRepository) {
		this.repository = repository;
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping
	public ResponseEntity<Void> cadastrar(@PathVariable Long id, @Valid @RequestBody CarroRequest request) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
							new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
		
		Carro carro = request.toModel(cliente);
		
		repository.save(carro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/carros/{id}")
											.buildAndExpand(carro.getId())
											.toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
	
}
