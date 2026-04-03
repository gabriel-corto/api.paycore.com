package com.labs.paycore.user.infra.jpa.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labs.paycore.user.infra.jpa.models.UserModel;

public interface JpaUserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByEmail(String email);
  Optional<UserModel> findByNif(String nif);
}
