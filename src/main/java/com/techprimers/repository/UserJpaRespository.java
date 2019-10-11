package com.techprimers.repository;

import com.techprimers.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRespository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

 
}
