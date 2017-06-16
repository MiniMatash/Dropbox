package com.minimatash.service.impl;

import com.minimatash.persistence.LoginPersistence;
import com.minimatash.persistence.impl.LoginPersistenceImpl;
import com.minimatash.service.LoginService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by on 20.06.16.
 */
public class LoginServiceImpl implements LoginService {

    LoginPersistence loginPersistence = new LoginPersistenceImpl();

    public Boolean login(String login, Integer password) throws SQLException {
        return loginPersistence.login(login, password);
    }

    public Boolean registerLog(String login, Integer password) throws SQLException, IOException {
        return loginPersistence.registerLog(login, password);
    }
}
