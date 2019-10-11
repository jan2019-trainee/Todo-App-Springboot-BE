package com.techprimers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.techprimers.model.Todos;

@Component
public interface TodosJpaRepository extends JpaRepository<Todos, Long>, QueryDslPredicateExecutor<Todos> {

	
	List<Todos> findByOwnerId(Long id);
}
