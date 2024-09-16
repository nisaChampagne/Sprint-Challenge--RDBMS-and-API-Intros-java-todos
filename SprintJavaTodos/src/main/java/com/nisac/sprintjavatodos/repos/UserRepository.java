package com.nisac.sprintjavatodos.repos;

import com.nisac.sprintjavatodos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
