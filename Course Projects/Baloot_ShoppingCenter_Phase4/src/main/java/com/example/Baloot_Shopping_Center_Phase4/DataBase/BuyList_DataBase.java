package com.example.Baloot_Shopping_Center_Phase4.DataBase;

import com.example.Baloot_Shopping_Center_Phase4.Buy_List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyList_DataBase extends DataBase_Manager<Buy_List, String> {
    private static final String BuyLists_TABLE = "BuyLists";
    private static BuyList_DataBase instance = null;

    public static BuyList_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new BuyList_DataBase();
        return instance;
    }

    private BuyList_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(commodity_id INTEGER,\n" +
                                "in_cart INTEGER,\n" +
                                "owner CHAR(255),\n" +
                                "FOREIGN KEY(commodity_id) REFERENCES COMMODITIES(id),\n" +
                                "FOREIGN KEY(owner) REFERENCES USERS(username));", BuyLists_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s BuyList WHERE owner = ?;", BuyLists_TABLE);
    }

    protected String getFindByOwnerStatement() {
        return String.format("SELECT* FROM %s BuyList WHERE owner = ?;", BuyLists_TABLE);
    }

    protected String getFindByOwnerAndCommodityStatement() {
        return String.format("SELECT* FROM %s BuyList WHERE owner = ? AND commodity_id = ?;", BuyLists_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, String owner) throws SQLException {
        st.setString(1, owner);
    }

    protected void fillFindByOwnerStatementValues(PreparedStatement st, String owner) throws SQLException {
        st.setString(1, owner);
    }

    protected void fillFindByOwnerAndCommodityStatementValues(PreparedStatement st, String owner, Integer commodity_id) throws SQLException {
        st.setString(1, owner);
        st.setInt(2, commodity_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(commodity_id, in_cart, owner) VALUES(?,?,?)", BuyLists_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET commodity_id = ?, in_cart = ?, owner = ? WHERE owner = ? AND commodity_id = ?", BuyLists_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE owner = ? AND commodity_id = ?", BuyLists_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, Buy_List buy_list) throws SQLException {
        st.setInt(1, buy_list.Commodity);
        st.setInt(2, buy_list.in_cart);
        st.setString(3, buy_list.owner);
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, Buy_List buy_list, String owner) throws SQLException {
        st.setInt(1, buy_list.Commodity);
        st.setInt(2, buy_list.in_cart);
        st.setString(3, buy_list.owner);
        st.setString(4, owner);
        st.setInt(5, buy_list.Commodity);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, Buy_List buy_list) throws SQLException {
        st.setString(1, buy_list.owner);
        st.setInt(2, buy_list.Commodity);
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", BuyLists_TABLE);
    }

    @Override
    protected Buy_List convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        Buy_List buy_list = new Buy_List(rs.getInt(1));
        buy_list.in_cart = rs.getInt(2);
        buy_list.owner = rs.getString(3);
        return buy_list;
    }

    @Override
    protected ArrayList<Buy_List> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<Buy_List> buy_lists = new ArrayList<>();
        while (rs.next()) {
            buy_lists.add(this.convertResultSetToDomainModel(rs));
        }
        return buy_lists;
    }

    public Buy_List findByOwnerAndCommodity(String owner, Integer commodity_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByOwnerAndCommodityStatement());
        fillFindByOwnerAndCommodityStatementValues(st, owner, commodity_id);
        try {
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next()) {
                st.close();
                con.close();
                return null;
            }
            Buy_List result = convertResultSetToDomainModel(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByOwnerAndCommodity query.");
            e.printStackTrace();
            throw e;
        }
    }

    public List<Buy_List> FindByOwner(String owner) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByOwnerStatement());
        fillFindByOwnerStatementValues(st, owner);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<Buy_List> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByOwner query.");
            e.printStackTrace();
            throw e;
        }
    }
}
