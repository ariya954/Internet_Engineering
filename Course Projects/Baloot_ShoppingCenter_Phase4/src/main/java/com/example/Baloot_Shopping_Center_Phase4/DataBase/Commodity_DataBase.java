package com.example.Baloot_Shopping_Center_Phase4.DataBase;

import com.example.Baloot_Shopping_Center_Phase4.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Commodity_DataBase extends DataBase_Manager<Commodity, Integer> {
    private static final String COMMODITIES_TABLE = "Commodities";
    private static Commodity_DataBase instance = null;

    public static Commodity_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new Commodity_DataBase();
        return instance;
    }

    private Commodity_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(id INTEGER,\n" +
                                "name CHAR(255),\n" +
                                "image CHAR(255),\n" +
                                "provider_id INTEGER,\n" +
                                "price INTEGER,\n" +
                                "categories TEXT(5),\n" +
                                "rating REAL,\n" +
                                "inStock INTEGER,\n" +
                                "FOREIGN KEY(provider_id) REFERENCES PROVIDERS(id),\n" +
                                "PRIMARY KEY(id));", COMMODITIES_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s commodity WHERE commodity.id = ?;", COMMODITIES_TABLE);
    }

    protected String getFindByCategoryStatement() {
        return String.format("SELECT* FROM %s commodity WHERE categories LIKE ?;", COMMODITIES_TABLE);
    }

    protected String getFindByNameStatement() {
        return String.format("SELECT* FROM %s commodity WHERE name LIKE ?;", COMMODITIES_TABLE);
    }

    protected String getFindByProviderStatement() {
        return String.format("SELECT* FROM %s commodity WHERE provider_id = ?;", COMMODITIES_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, Integer commodity_id) throws SQLException {
        st.setInt(1, commodity_id);
    }

    protected void fillFindByCategoryStatementValues(PreparedStatement st, String category) throws SQLException {
        st.setString(1, "%" + category + "%");
    }

    protected void fillFindByNameStatementValues(PreparedStatement st, String name) throws SQLException {
        st.setString(1, "%" + name + "%");
    }

    protected void fillFindByProviderStatementValues(PreparedStatement st, Integer provider_id) throws SQLException {
        st.setInt(1, provider_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(id, name, image, provider_id, price, categories, rating, inStock) VALUES(?,?,?,?,?,?,?,?)", COMMODITIES_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET id = ?, name = ?, image = ?, provider_id = ?, price = ?, categories = ?, rating= ?, inStock = ? WHERE id = ?", COMMODITIES_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE id = ?", COMMODITIES_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, Commodity commodity) throws SQLException {
        st.setInt(1, commodity.get_id());
        st.setString(2, commodity.get_name());
        st.setString(3, commodity.get_image());
        st.setInt(4, commodity.get_provider_id());
        st.setInt(5, commodity.get_price());
        List<String> categories = commodity.get_categories();
        String categories_list = "[";
        for(int i = 0; i < categories.size() - 1; i++)
            categories_list = categories_list + "\"" + categories.get(i) + "\", ";
        categories_list = categories_list + "\"" + categories.get(categories.size() - 1) + "\"]";
        st.setNString(6, categories_list);
        st.setFloat(7, commodity.get_rating());
        st.setInt(8, commodity.get_inStock());
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, Commodity commodity, Integer commodity_id) throws SQLException {
        st.setInt(1, commodity.get_id());
        st.setString(2, commodity.get_name());
        st.setString(3, commodity.get_image());
        st.setInt(4, commodity.get_provider_id());
        st.setInt(5, commodity.get_price());
        List<String> categories = commodity.get_categories();
        String categories_list = "[";
        for(int i = 0; i < categories.size() - 1; i++)
            categories_list = categories_list + "\"" + categories.get(i) + "\", ";
        categories_list = categories_list + "\"" + categories.get(categories.size() - 1) + "\"]";
        st.setNString(6, categories_list);
        st.setFloat(7, commodity.get_rating());
        st.setInt(8, commodity.get_inStock());
        st.setInt(9, commodity_id);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, Commodity commodity) throws SQLException {
        st.setInt(1, commodity.get_id());
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", COMMODITIES_TABLE);
    }

    @Override
    protected Commodity convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        try {
            return new Commodity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), new ObjectMapper().readValue(rs.getNString(6), List.class), rs.getFloat(7), rs.getInt(8));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ArrayList<Commodity> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<Commodity> commodities = new ArrayList<>();
        while (rs.next()) {
            commodities.add(this.convertResultSetToDomainModel(rs));
        }
        return commodities;
    }

    public List<Commodity> findByCategory(String category) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByCategoryStatement());
        fillFindByCategoryStatementValues(st, category);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<Commodity> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByCategory query.");
            e.printStackTrace();
            throw e;
        }
    }

    public List<Commodity> findByName(String name) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByNameStatement());
        fillFindByNameStatementValues(st, name);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<Commodity> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByName query.");
            e.printStackTrace();
            throw e;
        }
    }

    public List<Commodity> findByProvider(Integer provider_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByProviderStatement());
        fillFindByProviderStatementValues(st, provider_id);
        try {
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null) {
                st.close();
                con.close();
                return new ArrayList<>();
            }
            List<Commodity> result = convertResultSetToDomainModelList(resultSet);
            st.close();
            con.close();
            return result;
        } catch (Exception e) {
            st.close();
            con.close();
            System.out.println("error in Repository.findByProvider query.");
            e.printStackTrace();
            throw e;
        }
    }
}