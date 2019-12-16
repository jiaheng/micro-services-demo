package com.example.userservice.repository;

import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value="{ 'username' : ?0 }", fields="{username : 1, firstName : 1, lastName : 1, _id : 0}")
    Optional<User> findUserDetails(String username);

    @Query(value="{ 'username' : ?0 }", fields="{username : 1, encodedPassword : 1, roles : 1, _id : 0}")
    Optional<User> findUserLoginDetails(String username);

}
