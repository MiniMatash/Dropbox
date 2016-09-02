package com.minimatash.service;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

/**
 * Created by on 20.06.16.
 */
public interface LoginService {
    Boolean getLog(String login,Integer password) throws SQLException;
    Boolean registerLog(String login, Integer password) throws SQLException, IOException;
}
