package com.techprimers.controller;

import com.techprimers.model.User;
import com.techprimers.repository.UserJpaRespository;
import com.techprimers.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping
	public Page<User> findAll(
			@RequestParam(required = false) final String firstName,
			@RequestParam(required = false) final String occupation,
			@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
		
		Page<User> page = userService.userList(firstName, occupation, pageable);
		return page;
	}

	@GetMapping(value = "/{id}")
	public User findByName(@PathVariable final long id) {
		return userService.findById(id);
	}

//    @GetMapping(value = "/name/{name}")
//    public User findByName(@PathVariable final String name){
//        return userService.findByName(name);
//    }

	@PostMapping
	public User load(@RequestBody final User users) {
		return userService.Save(users);
//         userService.findByName(users.getName());
	}

	@PutMapping(value = "/{id}")
	public User load1(@PathVariable final long id, @RequestBody final User users) {
		userService.Update(id, users);
		return userService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public void load1(@PathVariable final long id) {
		userService.Delete(id);

	}
}
