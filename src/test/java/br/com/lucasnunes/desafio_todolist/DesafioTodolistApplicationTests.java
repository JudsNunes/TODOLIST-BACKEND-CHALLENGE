package br.com.lucasnunes.desafio_todolist;

import br.com.lucasnunes.desafio_todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DesafioTodolistApplicationTests {

	//injentando o cliente htpp para testa os endpoints
	@Autowired
	private WebTestClient webTestClient;


	@Test
	void testeCreateTodoSucess() {
		var todo = new Todo("Todo 1","desc todo 1",false,1);
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());

	}

	@Test
	void testeCreateTodoFailure(){
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("","",false,0)
				).exchange()
				.expectStatus().isBadRequest();
	}
}
