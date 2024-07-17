package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, String> {
}
