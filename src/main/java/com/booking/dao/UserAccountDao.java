package com.booking.dao;

import com.booking.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

    UserAccount getByUserId(long userId);

}
