package com.labs.paycore.user.infra.database.postgresql;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.labs.paycore.user.domain.User;
import com.labs.paycore.user.domain.UserRepository;
import com.labs.paycore.user.infra.jpa.repositories.JpaUserRepository;

@Repository
public class PostgreSQLUserRepository implements UserRepository {
  private final JpaUserRepository jpaUserRepository;

  private PostgreSQLUserRepository(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    var user = this.jpaUserRepository.findByEmail(email);
    return user.map(u -> PostgreSQLUserMapper.toDomain(u));
  }

  @Override
  public Optional<User> findByNif(String nif) {
    var user = this.jpaUserRepository.findByEmail(nif);
    return user.map(u -> PostgreSQLUserMapper.toDomain(u));
  }

  @Override
  public void create(User user) {
    this.jpaUserRepository.save(PostgreSQLUserMapper.toJpaModel(user));
  }

 
}
