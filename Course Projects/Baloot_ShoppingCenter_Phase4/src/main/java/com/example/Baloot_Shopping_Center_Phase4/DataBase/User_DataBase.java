package com.example.Baloot_Shopping_Center_Phase4.DataBase;

import com.example.Baloot_Shopping_Center_Phase4.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_DataBase extends DataBase_Manager<User, String> {
    private static final String USERS_TABLE = "Users";
    private static User_DataBase instance = null;

    public static User_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new User_DataBase();
        return instance;
    }

    private User_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(username CHAR(255),\n" +
                                "password CHAR(255),\n" +
                                "email CHAR(255),\n" +
                                "birthDate CHAR(255),\n" +
                                "address CHAR(255),\n" +
                                "credit INTEGER,\n" +
                                "PRIMARY KEY(username, email));", USERS_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s user WHERE user.username = ?;", USERS_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, String username) throws SQLException {
        st.setString(1, username);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(username, password, email, birthDate, address, credit) VALUES(?,?,?,?,?,?)", USERS_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET username = ?, password = ?, email = ?, birthDate = ?, address = ?, credit = ? WHERE username = ?", USERS_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE username = ?", USERS_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, User user) throws SQLException {
        st.setString(1, user.get_username());
        st.setString(2, user.get_password());
        st.setString(3, user.get_email());
        st.setString(4, user.get_birthDate());
        st.setString(5, user.get_address());
        st.setInt(6, user.get_credit());
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, User user, String username) throws SQLException {
        st.setString(1, user.get_username());
        st.setString(2, user.get_password());
        st.setString(3, user.get_email());
        st.setString(4, user.get_birthDate());
        st.setString(5, user.get_address());
        st.setInt(6, user.get_credit());
        st.setString(7, username);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, User user) throws SQLException {
        st.setString(1, user.get_username());
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", USERS_TABLE);
    }

    @Override
    protected User convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
    }

    @Override
    protected ArrayList<User> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(this.convertResultSetToDomainModel(rs));
        }
        return users;
    }
}