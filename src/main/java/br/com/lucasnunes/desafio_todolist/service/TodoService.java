package br.com.lucasnunes.desafio_todolist.service;


import br.com.lucasnunes.desafio_todolist.entity.Todo;
import br.com.lucasnunes.desafio_todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

//service que dizer que vai ser passível de injeção
@Service
public class TodoService {

    private TodoRepository todoRepository;
    //injeção de dependência TodoRepository na classe service
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
        //principio do DRY -> toda lista que retornar vai conter este método abaixo | estamos criando e listando;
    }

    //apenas retornamos todas as todos| estamos criando uma consulta utilizando o objeto  | temos uma consulta ordenada pela prioridade e depois pelo nome da todo
    public List<Todo> list(){
        Sort sort = Sort.by("prioridade").descending().and(
                Sort.by("nome").ascending());
                return todoRepository.findAll(sort);
    }


    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return list();
    }


    public List<Todo> delete(Long id){
        todoRepository.deleteAllById(Collections.singleton(id));
        return list();
    }





}
