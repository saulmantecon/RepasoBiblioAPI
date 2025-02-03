package org.example.hotelapi2.repository;

import org.example.hotelapi2.model.Hotel;
import org.example.hotelapi2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPwd(String user, String pwd);
}
