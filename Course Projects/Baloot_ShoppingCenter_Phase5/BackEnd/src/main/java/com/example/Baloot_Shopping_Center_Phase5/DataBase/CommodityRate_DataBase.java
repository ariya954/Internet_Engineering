package com.example.Baloot_Shopping_Center_Phase5.DataBase;

import com.example.Baloot_Shopping_Center_Phase5.User_Rate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityRate_DataBase extends DataBase_Manager<User_Rate, String> {
    private static final String COMMODITIES_RATES_DataBase = "Commodities_Rates";
    private static CommodityRate_DataBase instance = null;

    public static CommodityRate_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new CommodityRate_DataBase();
        return instance;
    }

    private CommodityRate_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(scorer CHAR(255),\n" +
                                "commodity_id INTEGER,\n" +
                                "rate INTEGER,\n" +
                                "FOREIGN KEY(commodity_id) REFERENCES Commodities(id),\n" +
                                "FOREIGN KEY(scorer) REFERENCES Users(username));", COMMODITIES_RATES_DataBase)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s CommentRate WHERE scorer = ?;", COMMODITIES_RATES_DataBase);
    }

    protected String getFindByCommodityStatement() {
        return String.format("SELECT* FROM %s CommodityRate WHERE commodity_id = ?;", COMMODITIES_RATES_DataBase);
    }

    protected String getFindByScorerAndCommodityStatement() {
        return String.format("SELECT* FROM %s CommodityRate WHERE scorer = ? AND commodity_id = ?;", COMMODITIES_RATES_DataBase);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, String scorer) throws SQLException {
        st.setString(1, scorer);
    }

    protected void fillFindByCommodityStatementValues(PreparedStatement st, Integer commodity_id) throws SQLException {
        st.setInt(1, commodity_id);
    }

    protected void fillFindByScorerAndCommodityStatementValues(PreparedStatement st, String scorer, Integer commodity_id) throws SQLException {
        st.setString(1, scorer);
        st.setInt(2, commodity_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(scorer, commodity_id, rate) VALUES(?,?,?)", COMMODITIES_RATES_DataBase);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET scorer = ?, commodity_id = ?, rate = ? WHERE scorer = ? AND commodity_id = ?", COMMODITIES_RATES_DataBase);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE scorer = ? AND commodity_id = ?", COMMODITIES_RATES_DataBase);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, User_Rate user_rate) throws SQLException {
        st.setString(1, user_rate.username);
        st.setInt(2, user_rate.rated_item_id);
        st.setInt(3, user_rate.rate);
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, User_Rate user_rate, String scorer) throws SQLException {
        st.setString(1, user_rate.username);
        st.setInt(2, user_rate.rated_item_id);
        st.setInt(3, user_rate.rate);
        st.setString(4, scorer);
        st.setInt(5, user_rate.rated_item_id);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, User_Rate user_rate) throws SQLException {
        st.setString(1, user_rate.username);
        st.setInt(2, user_rate.rated_item_id);
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", COMMODITIES_RATES_DataBase);
    }

    @Override
    protected User_Rate convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        return new User_Rate(rs.getString(1), rs.getInt(2), rs.getInt(3));
    }

    @Override
    protected ArrayList<User_Rate> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<User_Rate> rates = new ArrayList<>();
        while (rs.next()) {
            rates.add(this.convertResultSetToDomainModel(rs));
        }
        return rates;
    }

    public User_Rate findByScorerAndCommodity(String owner, Integer commodity_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByScorerAndCommodityStatement());
        fillFindByScorerAndCommodityStatementValues(st, owner, commodity_id);
        try {
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next()) {
                st.close();
                con.close();
                return null;
            }
            User_Rate result = convertResultSetToDomainModel(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByScorerAndCommodity query.");
            e.printStackTrace();
            throw e;
        }
    }

    public List<User_Rate> FindByCommodity(Integer commodity_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByCommodityStatement());
        fillFindByCommodityStatementValues(st, commodity_id);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<User_Rate> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByCommodity query.");
            e.printStackTrace();
            throw e;
        }
    }
}