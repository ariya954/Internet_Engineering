package com.example.Baloot_Shopping_Center_Phase4.DataBase;

import com.example.Baloot_Shopping_Center_Phase4.Purchased_List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasedList_DataBase extends DataBase_Manager<Purchased_List, String> {
    private static final String PurchasedLists_TABLE = "PurchasedLists";
    private static PurchasedList_DataBase instance = null;

    public static PurchasedList_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new PurchasedList_DataBase();
        return instance;
    }

    private PurchasedList_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(purchased_commodity_id INTEGER,\n" +
                                "number_of_purchased INTEGER,\n" +
                                "buyer CHAR(255),\n" +
                                "FOREIGN KEY(purchased_commodity_id) REFERENCES COMMODITIES(id),\n" +
                                "FOREIGN KEY(buyer) REFERENCES USERS(username));", PurchasedLists_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s PurchasedList WHERE buyer = ?;", PurchasedLists_TABLE);
    }

    protected String getFindByBuyerStatement() {
        return String.format("SELECT* FROM %s PurchasedList WHERE buyer = ?;", PurchasedLists_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, String buyer) throws SQLException {
        st.setString(1, buyer);
    }

    protected void fillFindByBuyerStatementValues(PreparedStatement st, String buyer) throws SQLException {
        st.setString(1, buyer);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(purchased_commodity_id, number_of_purchased, buyer) VALUES(?,?,?)", PurchasedLists_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET purchased_commodity_id = ?, number_of_purchased = ?, buyer = ? WHERE buyer = ? AND purchased_commodity_id = ?", PurchasedLists_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE buyer = ? AND purchased_commodity_id = ?", PurchasedLists_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, Purchased_List purchased_list) throws SQLException {
        st.setInt(1, purchased_list.Commodity);
        st.setInt(2, purchased_list.number_of_purchased);
        st.setString(3, purchased_list.buyer);
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, Purchased_List purchased_list, String buyer) throws SQLException {
        st.setInt(1, purchased_list.Commodity);
        st.setInt(2, purchased_list.number_of_purchased);
        st.setString(3, purchased_list.buyer);
        st.setString(4, buyer);
        st.setInt(5, purchased_list.Commodity);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, Purchased_List purchased_list) throws SQLException {
        st.setString(1, purchased_list.buyer);
        st.setInt(2, purchased_list.Commodity);
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", PurchasedLists_TABLE);
    }

    @Override
    protected Purchased_List convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        return new Purchased_List(rs.getInt(1), rs.getInt(2), rs.getString(3));
    }

    @Override
    protected ArrayList<Purchased_List> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<Purchased_List> purchased_lists = new ArrayList<>();
        while (rs.next()) {
            purchased_lists.add(this.convertResultSetToDomainModel(rs));
        }
        return purchased_lists;
    }

    public List<Purchased_List> FindByBuyer(String owner) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByBuyerStatement());
        fillFindByBuyerStatementValues(st, owner);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<Purchased_List> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByBuyer query.");
            e.printStackTrace();
            throw e;
        }
    }
}