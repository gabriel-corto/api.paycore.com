package com.labs.paycore.user.domain;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository {
  void save(User user); 
  Optional<User> findByEmail(String email);
  Optional<User> findByNif(String nif);
  Optional<User> findById(UUID id);
}
