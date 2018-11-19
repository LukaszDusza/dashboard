package com.dfsp.dashboard.User;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserApp, Long> {

    UserApp findByUsername(String username);

}
