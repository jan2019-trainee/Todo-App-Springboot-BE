package com.techprimers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.model.Todos;
import com.techprimers.model.User;
import com.techprimers.service.TodoService;
@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodosController {

	@Autowired	
	private TodoService todoService;

	 	@GetMapping
	    public Page<Todos> findAll(
	    		@RequestParam(required = false)  final Long id,
	    		@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
	
	 		Page<Todos> page = todoService.todoList(id, pageable);
	 		return page;
	    }
	 	

	    @GetMapping(value = "/{id}")
	    public Todos findByName(@PathVariable final long id){
	        return todoService.findById(id);
	    }
	    	   
	    @PostMapping
	    public Todos load(@RequestBody final Todos todo) {
	    	return todoService.Save(todo);
	    }
	  
	    @PutMapping(value = "/{id}")
	    public Todos load1(@PathVariable final long id,@RequestBody final Todos todo) {
	    	todoService.Update(id,todo);
	        return todoService.findById(id);
	    }
	    
	    @DeleteMapping(value = "/{id}")
	    public void load1(@PathVariable final long id) {
	    	todoService.Delete(id);
	    }
}
