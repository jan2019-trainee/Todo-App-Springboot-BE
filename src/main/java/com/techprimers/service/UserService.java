package com.techprimers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.techprimers.model.QUser;
import com.techprimers.model.User;
import com.techprimers.repository.UserJpaRespository;

@Service
public class UserService {

	@Autowired
	private UserJpaRespository userJpaRespository;

	
	public List<User> All() {
		return userJpaRespository.findAll();
	}
	
	public Page<User> userList( String firstName, String occupation, Pageable pageable) {
		
		BooleanBuilder build = new BooleanBuilder();
		QUser quser = QUser.user;
		
		if(firstName != null) {
			build.and(quser.first_name.eq(firstName));
		}
		if(occupation != null) {
			build.and(quser.occupation.eq(occupation));
		}
	
        return userJpaRespository.findAll(build, pageable);
	}

	public User findById(Long id) {

		User user = userJpaRespository.findOne(id);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		return user;
	}

	public User Save(User user) {
		return userJpaRespository.save(user);
		
	}

	public User Update(Long id, User users) {

		User user1 = userJpaRespository.findOne(id);

		if (user1 == null) {
			throw new RuntimeException("User not found");
		}
		user1.setFirst_name(users.getFirst_name());
		user1.setLast_name(users.getLast_name());
		user1.setOccupation(users.getOccupation());
		user1.setProfile_picture(users.getProfile_picture());

		return userJpaRespository.save(user1);
	}

	public void Delete(Long id) {
		User user1 = userJpaRespository.findOne(id);

		if (user1 == null) {
			throw new RuntimeException("User not found");
		}
		
		userJpaRespository.delete(id);
	}

}
