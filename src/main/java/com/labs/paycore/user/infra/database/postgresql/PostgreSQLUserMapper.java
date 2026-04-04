package com.labs.paycore.user.infra.database.postgresql;

import com.labs.paycore.user.domain.Email;
import com.labs.paycore.user.domain.Nif;
import com.labs.paycore.user.domain.User;

import com.labs.paycore.user.infra.jpa.models.UserModel;

public class PostgreSQLUserMapper {
  static User toDomain(UserModel userModel) {
    return User.restore(
      userModel.getId(), 
      userModel.getName(), 
      Email.create(userModel.getEmail()), 
      Nif.create(userModel.getNif()), 
      userModel.getPassword()
    );
  }

  static UserModel toJpaModel(User user) {
    var userModel = new UserModel();

    userModel.setId(user.getId());
    userModel.setName(user.getName());
    userModel.setEmail(user.getEmail().getValue());
    userModel.setNif(user.getNif().getValue());
    userModel.setPassword(user.getPassword());

    return userModel;
  }
}
