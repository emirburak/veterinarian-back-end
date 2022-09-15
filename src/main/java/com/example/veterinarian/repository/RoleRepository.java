package com.example.veterinarian.repository;

import com.example.veterinarian.model.enums.ERole;
import com.example.veterinarian.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
