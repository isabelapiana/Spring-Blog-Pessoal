package org.generation.blogPessoal.controller;

import java.util.List;

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

@RestController //Criar controller
@RequestMapping("/postagens") //Criar Endpoint
@CrossOrigin("*") //Habilida a API a receber requisição de qualquer porta
public class PostagemController {

	@Autowired //Injeção de dependência/transferência de responsabilidade (para que não tenha que ficar repetindo os códigos do repository, ele já instancia o repository sozinho sempre que for usar o repository)
	private PostagemRepository postagemRepository;
	
	@GetMapping //ResponseEntity - prepara a requisição para ter uma resposta; Depois coloca quem será postado; 
	public ResponseEntity<List<Postagem>> getAll (){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}") //ResponseEntity - prepara a requisição para ter uma resposta; Depois coloca quem será postado; 
	public ResponseEntity<Postagem> buscaPostagemPorId (@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) //Aceita se der certo (entrega o objeto) e se der errado (dá resposta de erro)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscaPorTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	@PostMapping
	public ResponseEntity<Postagem> adicionaPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		postagemRepository.deleteById(id);
	}
}
