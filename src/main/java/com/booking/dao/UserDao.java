package com.booking.dao;

import com.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    public User findByByEmail(String email);

    public List<User> findByName(String name);

}
