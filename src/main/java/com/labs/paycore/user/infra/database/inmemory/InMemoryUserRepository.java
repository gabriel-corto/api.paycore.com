package com.labs.paycore.user.infra.database.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.labs.paycore.user.domain.User;
import com.labs.paycore.user.domain.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository {
  private List<User> users = new ArrayList<>();
  
  @Override
  public Optional<User> findByEmail(String email) {
    return this.users.stream().filter(user -> user.getEmail().getValue() == email).findFirst();
  }

  @Override
  public Optional<User> findByNif(String nif) {
    return this.users.stream().filter(user -> user.getNif().getValue() == nif).findFirst();
  }

  @Override
  public void save(User user) {
    this.users.add(user);
  }
}
