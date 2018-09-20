package com.bscllc.jpa.repositories;

import com.bscllc.jpa.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}