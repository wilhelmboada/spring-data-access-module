package com.booking.service;

import com.booking.dao.UserAccountDao;
import com.booking.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    UserAccountDao userAccountDao;

    public UserAccount getUserAccount(long userAccountId) {
        return userAccountDao.getById(userAccountId);
    }

    public UserAccount getUserAccountByUserId(long userId) {
        return userAccountDao.getByUserId(userId);
    }

    public UserAccount createUserAccount(UserAccount userAccount) {
        logger.info("create userAccount: {}", userAccount.getId() );
        return userAccountDao.save(userAccount);
    }

    public UserAccount addMoneyToUserAccount(long userAccountId, Double money) {
        logger.info("update userAccount: {}", userAccountId);
        UserAccount byId = userAccountDao.getById(userAccountId);
        byId.setPrepaidAmount(money + byId.getPrepaidAmount());
        return userAccountDao.save(byId);
    }

    public UserAccount withdrawFromUserAccount(long userAccountId, Double money) {
        logger.info("update userAccount: {}", userAccountId);
        UserAccount byId = userAccountDao.getById(userAccountId);
        byId.setPrepaidAmount(byId.getPrepaidAmount() - money);
        return userAccountDao.save(byId);
    }

}
