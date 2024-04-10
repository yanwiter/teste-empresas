package com.picpaysimplificado.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.picpaysimplificado.domain.user.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);

}
