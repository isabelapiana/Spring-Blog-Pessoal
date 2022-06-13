package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTeste {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(
				new Usuario(0L, "João da Silva", "joao@gmail.com.br", "13465278", "https://i.imgur.com/asdfg.jpg"));

		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@gmail.com.br", "13465278",
				"https://i.imgur.com/asdfg.jpg"));

		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@gmail.com.br", "13465278",
				"https://i.imgur.com/asdfg.jpg"));

		usuarioRepository.save(
				new Usuario(0L, "Paulo Antunes", "paulo@gmail.com.br", "13465278", "https://i.imgur.com/asdfg.jpg"));
	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
