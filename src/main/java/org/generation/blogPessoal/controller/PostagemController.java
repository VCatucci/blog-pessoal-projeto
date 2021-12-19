package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/postagem")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	/** findAllTema */
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> getAll() {
		List<Postagem> list = repository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	/** postPostagem */
	@PostMapping("/save")
	public ResponseEntity<Postagem> save(@RequestBody Postagem postagem) {
		return ResponseEntity.status(201).body(repository.save(postagem));
	}
	
	/** findByIDPostagem */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable(value = "id") Long id) {
		Optional<Postagem> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.status(200).body(optional.get());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe!");
		}
	}
	
	/** findByTextoPostagem */
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Postagem>> GetByTexto (@PathVariable String texto) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	
	/** putPostagem */
	@PutMapping("/update")
	public Postagem update(@RequestBody Postagem newPostagem) {
		return repository.save(newPostagem);
	}
	
	/** deletePostagem */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(value = "id")Long id) {
		repository.deleteById(id);
	}
}