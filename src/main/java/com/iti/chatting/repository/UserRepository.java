package com.iti.chatting.repository;

import com.iti.chatting.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    public UserEntity findByUsername(String name);
}
