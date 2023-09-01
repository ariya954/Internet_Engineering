package com.example.Baloot_Shopping_Center_Phase5.DataBase;

import com.example.Baloot_Shopping_Center_Phase5.User_Rate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRate_DataBase extends DataBase_Manager<User_Rate, String> {
    private static final String COMMENTS_RATES_TABLE = "Comments_Rates";
    private static CommentRate_DataBase instance = null;

    public static CommentRate_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new CommentRate_DataBase();
        return instance;
    }

    private CommentRate_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(scorer CHAR(255),\n" +
                                "comment_id INTEGER,\n" +
                                "rate INTEGER,\n" +
                                "FOREIGN KEY(comment_id) REFERENCES Comments(id),\n" +
                                "FOREIGN KEY(scorer) REFERENCES Users(username));", COMMENTS_RATES_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s CommentRate WHERE scorer = ?;", COMMENTS_RATES_TABLE);
    }

    protected String getFindByCommentStatement() {
        return String.format("SELECT* FROM %s CommentRate WHERE comment_id = ?;", COMMENTS_RATES_TABLE);
    }

    protected String getFindByScorerAndCommentStatement() {
        return String.format("SELECT* FROM %s CommentRate WHERE scorer = ? AND comment_id = ?;", COMMENTS_RATES_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, String scorer) throws SQLException {
        st.setString(1, scorer);
    }

    protected void fillFindByCommentStatementValues(PreparedStatement st, Integer comment_id) throws SQLException {
        st.setInt(1, comment_id);
    }

    protected void fillFindByScorerAndCommentStatementValues(PreparedStatement st, String scorer, Integer comment_id) throws SQLException {
        st.setString(1, scorer);
        st.setInt(2, comment_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(scorer, comment_id, rate) VALUES(?,?,?)", COMMENTS_RATES_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET scorer = ?, comment_id = ?, rate = ? WHERE scorer = ? AND comment_id = ?", COMMENTS_RATES_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE scorer = ? AND comment_id = ?", COMMENTS_RATES_TABLE);
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
        return String.format("SELECT * FROM %s;", COMMENTS_RATES_TABLE);
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

    public User_Rate findByScorerAndComment(String owner, Integer comment_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByScorerAndCommentStatement());
        fillFindByScorerAndCommentStatementValues(st, owner, comment_id);
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
            System.out.println("error in Repository.findByScorerAndComment query.");
            e.printStackTrace();
            throw e;
        }
    }

    public List<User_Rate> FindByComment(Integer comment_id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(getFindByCommentStatement());
        fillFindByCommentStatementValues(st, comment_id);
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
            System.out.println("error in Repository.findByComment query.");
            e.printStackTrace();
            throw e;
        }
    }
}