package com.example.Baloot_Shopping_Center_Phase5.DataBase;

import com.example.Baloot_Shopping_Center_Phase5.User_Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Comment_DataBase extends DataBase_Manager<User_Comment, Integer> {
    private static final String COMMENTS_TABLE = "Comments";
    private static Comment_DataBase instance = null;

    public static Comment_DataBase getInstance() throws SQLException {
        if (instance == null)
            instance = new Comment_DataBase();
        return instance;
    }

    private Comment_DataBase() throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement createTableStatement = con.prepareStatement(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s(id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                                "owner CHAR(255),\n" +
                                "commodity_id INTEGER,\n" +
                                "comment CHAR(255),\n" +
                                "comment_date CHAR(255),\n" +
                                "PRIMARY KEY(id),\n" +
                                "FOREIGN KEY(commodity_id) REFERENCES Commodities(id),\n" +
                                "FOREIGN KEY(owner) REFERENCES Users(email));", COMMENTS_TABLE)
        );
        createTableStatement.executeUpdate();
        createTableStatement.close();
        con.close();
    }

    @Override
    protected String getFindByIdStatement() {
        return String.format("SELECT* FROM %s comment WHERE id = ?;", COMMENTS_TABLE);
    }

    protected String getFindByCommodityStatement() {
        return String.format("SELECT* FROM %s comment WHERE commodity_id = ?;", COMMENTS_TABLE);
    }

    @Override
    protected void fillFindByIdStatementValues(PreparedStatement st, Integer comment_id) throws SQLException {
        st.setInt(1, comment_id);
    }

    protected void fillFindByCommodityStatementValues(PreparedStatement st, Integer commodity_id) throws SQLException {
        st.setInt(1, commodity_id);
    }

    @Override
    protected String getInsertStatement() {
        return String.format("INSERT INTO %s(owner, commodity_id, comment, comment_date) VALUES(?,?,?,?)", COMMENTS_TABLE);
    }

    @Override
    protected String getUpdateStatement() {
        return String.format("UPDATE %s SET owner = ?, commodity_id = ?, comment = ?, comment_date = ? WHERE id = ?", COMMENTS_TABLE);
    }

    @Override
    protected String getRemoveStatement() {
        return String.format("DELETE FROM %s WHERE id = ?", COMMENTS_TABLE);
    }

    @Override
    protected void fillInsertStatementValues(PreparedStatement st, User_Comment comment) throws SQLException {
        st.setString(1, comment.get_user_email());
        st.setInt(2, comment.get_commodity_id());
        st.setString(3, comment.get_comment());
        st.setString(4, comment.get_comment_date());
    }

    @Override
    protected void fillUpdateStatementValues(PreparedStatement st, User_Comment comment, Integer comment_id) throws SQLException {
        st.setString(1, comment.get_user_email());
        st.setInt(2, comment.get_commodity_id());
        st.setString(3, comment.get_comment());
        st.setString(4, comment.get_comment_date());
        st.setInt(5, comment_id);
    }

    @Override
    protected void fillRemoveStatementValues(PreparedStatement st, User_Comment comment) throws SQLException {
        st.setInt(1, comment.get_id());
    }

    @Override
    protected String getFindAllStatement() {
        return String.format("SELECT * FROM %s;", COMMENTS_TABLE);
    }

    @Override
    protected User_Comment convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        User_Comment user_comment =  new User_Comment(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
        user_comment.set_id(rs.getInt(1));
        return user_comment;
    }

    @Override
    protected ArrayList<User_Comment> convertResultSetToDomainModelList(ResultSet rs) throws SQLException {
        ArrayList<User_Comment> comments = new ArrayList<>();
        while (rs.next()) {
            comments.add(this.convertResultSetToDomainModel(rs));
        }
        return comments;
    }

    public List<User_Comment> FindByCommodity(Integer commodity_id) throws SQLException {
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
            List<User_Comment> result = convertResultSetToDomainModelList(resultSet);
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