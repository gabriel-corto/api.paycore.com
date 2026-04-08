package com.labs.paycore.user.infra.database.postgresql;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.labs.paycore.user.domain.User;
import com.labs.paycore.user.domain.UserRepository;
import com.labs.paycore.user.infra.jpa.repositories.JpaUserRepository;

@Repository
@Primary
public class PostgreSQLUserRepository implements UserRepository {
  private final JpaUserRepository jpaUserRepository;

  public PostgreSQLUserRepository(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    var user = this.jpaUserRepository.findByEmail(email);
    return user.map(u -> PostgreSQLUserMapper.toDomain(u));
  }

  @Override
  public Optional<User> findByNif(String nif) {
    var user = this.jpaUserRepository.findByNif(nif);
    return user.map(u -> PostgreSQLUserMapper.toDomain(u));
  }

  @Override
  public Optional<User> findById(UUID id) {
    return this.jpaUserRepository.findById(id).map(user -> PostgreSQLUserMapper.toDomain(user));
  }

  @Override
  public void save(User user) {
    this.jpaUserRepository.save(PostgreSQLUserMapper.toJpaModel(user));
  }
}
