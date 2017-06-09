package com.minimatash.persistence.impl;

import com.minimatash.persistence.LoginPersistence;
import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;

import java.io.IOException;
import java.sql.*;

public class LoginPersistenceImpl implements LoginPersistence {

    private FileWorkService fileWork = new FileWorkServiceImpl();

    private Connection connection;

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dropbox","root","qwerty");
    }

    @Override
    public Boolean getLog(String login, Integer password) throws SQLException {
        connection = getConnection();
        String selectUserSQL = "Select * from loginPage WHERE login = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL);
        preparedStatement.setString(1,login);
        preparedStatement.setInt(2,password);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

    @Override
    public Boolean registerLog(String login, Integer password) throws SQLException, IOException {
        connection = getConnection();
        String selectUserSQL = "Select * from loginPage WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL);
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return false;
        }else{
            String addUserSQL = "INSERT INTO loginPage (" + "login,password" + ") VALUES (?,?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(addUserSQL);
            preparedStatement2.setString(1,login);
            preparedStatement2.setInt(2,password);
            preparedStatement2.execute();
            fileWork.createUserFolder(login);
            return true;
        }
    }
}
