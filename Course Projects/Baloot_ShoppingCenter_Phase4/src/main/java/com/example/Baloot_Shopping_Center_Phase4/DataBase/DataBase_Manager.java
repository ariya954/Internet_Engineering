package com.example.Baloot_Shopping_Center_Phase4.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBase_Manager<T, I> {
    abstract protected String getFindByIdStatement();

    abstract protected void fillFindByIdStatementValues(PreparedStatement st, I id) throws SQLException;

    abstract protected String getInsertStatement();

    abstract protected String getRemoveStatement();

    abstract protected String getUpdateStatement();

    abstract protected void fillInsertStatementValues(PreparedStatement st, T data) throws SQLException;

    abstract protected void fillRemoveStatementValues(PreparedStatement st, T obj) throws SQLException;

    abstract protected void fillUpdateStatementValues(PreparedStatement st, T data, I id) throws SQLException;

    abstract protected String getFindAllStatement();

    abstract protected T convertResultSetToDomainModel(ResultSet rs) throws SQLException;

    abstract protected ArrayList<T> convertResultSetToDomainModelList(ResultSet rs) throws SQLException;

    public T findById(I id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByIdStatement());
        fillFindByIdStatementValues(st, id);
        try {
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next()) {
                st.close();
                con.close();
                return null;
            }
            T result = convertResultSetToDomainModel(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.find query.");
            e.printStackTrace();
            throw e;
        }
    }

    public void insert(T obj) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getInsertStatement());
        fillInsertStatementValues(st, obj);
        try {
            st.execute();
            st.close();
            con.close();
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.insert query.");
            e.printStackTrace();
        }
    }

    public void remove(T obj) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getRemoveStatement());
        fillRemoveStatementValues(st, obj);
        try {
            st.execute();
            st.close();
            con.close();
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.remove query.");
            e.printStackTrace();
        }
    }

    public void updateIdByNewObject(I id, T obj) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getUpdateStatement());
        fillUpdateStatementValues(st, obj, id);
        try {
            st.execute();
            st.close();
            con.close();
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.update query.");
            e.printStackTrace();
        }
    }

    public List<T> findAll() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindAllStatement());
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<T> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findAll query.");
            e.printStackTrace();
            throw e;
        }
    }
}