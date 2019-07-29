package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
