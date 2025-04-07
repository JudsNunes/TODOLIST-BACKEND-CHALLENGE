package br.com.lucasnunes.desafio_todolist.repository;

import br.com.lucasnunes.desafio_todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository com Spring Data são interfaces JPA.
//ESTUDAR SOBRE GENERICS
public interface TodoRepository extends JpaRepository<Todo,Long> {

}
