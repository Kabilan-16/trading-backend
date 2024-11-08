package com.trading3.trading.repository;


import com.trading3.trading.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

//remove,update data That means we can manage from database
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
