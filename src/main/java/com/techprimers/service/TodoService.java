package com.techprimers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.techprimers.model.QTodos;
import com.techprimers.model.Todos;
import com.techprimers.repository.TodosJpaRepository;
import com.techprimers.repository.UserJpaRespository;

@Service
public class TodoService {

	@Autowired
	private TodosJpaRepository todosJpaRepository;

//	@Autowired
//	private UserJpaRespository userJpaRepository;

	public List<Todos> All(Long id) {
		
		if(id != null) {
			return todosJpaRepository.findByOwnerId(id);
		}
		
		return todosJpaRepository.findAll();
	}
	
	public Page<Todos> todoList( Long id, Pageable pageable) {
		
		BooleanBuilder build = new BooleanBuilder();
		QTodos qtodos = QTodos.todos;
		
		if(id != null) {
			build.and(qtodos.id.eq(id));
		}
        return todosJpaRepository.findAll(build, pageable);
	}

	public Todos findById(Long id) {

		Todos todos = todosJpaRepository.findOne(id);

		if (todos == null) {
			throw new RuntimeException("Todos not found");
		}

		return todos;
	}

	public Todos Save(Todos todos) {
		return todosJpaRepository.save(todos);
	}
	
	public Todos Update(Long id, Todos todos) {

		Todos todos1 = todosJpaRepository.findOne(id);
		
		if (todos1 == null) {
			throw new RuntimeException("Todo not found");
		}
		todos1.setName(todos.getName());
		todos1.setDescription(todos.getDescription());
		todos1.setStatus(todos.getStatus());
		todos1.setOwner(todos.getOwner());

		return todosJpaRepository.save(todos1);
	}

	public void Delete(Long id) {
		Todos todo = todosJpaRepository.findOne(id);

		if (todo == null) {
			throw new RuntimeException("Todo not found");
		}

		todosJpaRepository.delete(id);
	}

}
