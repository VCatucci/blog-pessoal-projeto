package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.dtos.UserCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UserLoginDTO;
import org.generation.blogPessoal.model.dtos.UsuarioRegisterDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioService services;

	
	@PostMapping("/save")
	public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioRegisterDTO newUsuario) {
		return services.registerUser(newUsuario);
	}


	@PutMapping("/credentials")
	public ResponseEntity<UserCredentialsDTO> credentials(@Valid @RequestBody UserLoginDTO user) {
		return services.getCredentials(user);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable(value = "id") Long id) {
		Optional<Usuario> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.status(200).body(optional.get());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe!");
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> list = repository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	 @GetMapping("/{token}")
	    public ResponseEntity<Usuario> getProfileByToken(@PathVariable String token){
	        return ResponseEntity.status(200).body(repository.findByToken(token).get());
	    }
}
