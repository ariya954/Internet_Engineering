package com.example.Baloot_Shopping_Center_Phase5.DataBase;

import com.example.Baloot_Shopping_Center_Phase5.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Provider_DataBase extends DataBase_Manager<Provider, Integer> {
    private static final String PROVIDERS_TABLE = "Providers";
    private static Provider_DataBase instance = null;

    public static Provider_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new Provider_DataBase();
        return instance;
    }

    private Provider_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(id INTEGER,\n" +
                                "name CHAR(255),\n" +
                                "registryDate CHAR(255),\n" +
                                "PRIMARY KEY(id));", PROVIDERS_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s provider WHERE provider.id = ?;", PROVIDERS_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, Integer provider_id) throws SQLException {
        st.setInt(1, provider_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(id, name, registryDate) VALUES(?,?,?)", PROVIDERS_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET id = ?, name = ?, registryDate = ? WHERE id = ?", PROVIDERS_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE id = ?", PROVIDERS_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, Provider provider) throws SQLException {
        st.setInt(1, provider.get_id());
        st.setString(2, provider.get_name());
        st.setString(3, provider.get_registryDate());
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, Provider provider, Integer provider_id) throws SQLException {
        st.setInt(1, provider.get_id());
        st.setString(2, provider.get_name());
        st.setString(3, provider.get_registryDate());
        st.setInt(4, provider_id);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, Provider provider) throws SQLException {
        st.setInt(1, provider.get_id());
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", PROVIDERS_TABLE);
    }

    @Override
    protected Provider convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        return new Provider(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    @Override
    protected ArrayList<Provider> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<Provider> providers = new ArrayList<>();
        while (rs.next()) {
            providers.add(this.convertResultSetToDomainModel(rs));
        }
        return providers;
    }
}