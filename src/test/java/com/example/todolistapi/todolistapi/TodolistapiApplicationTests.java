package com.example.todolistapi.todolistapi;

import com.example.todolistapi.todolistapi.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistapiApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Tarefa 1", "Descrição 1", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("nome").isEqualTo(todo.getNome())
				.jsonPath("descricao").isEqualTo(todo.getDescricao())
				.jsonPath("realizada").isEqualTo(todo.getRealizada())
				.jsonPath("prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("", "", false, 1)
				).exchange()
				.expectStatus().isBadRequest();
	}
}
