package com.corejsf;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Justin Wheeler
 */
@ManagedBean(name = "search", eager = true)
@SessionScoped
public class Search implements Serializable {
    private ConnectDB connection;
    private String selection;
    private String user_in;
    private String query;

    //Using constants to avoid misuse
    private static final String SELECTALL = "*";
    private static final String TABLENAME = "REVIEWS";

    public List<Reviews> getSearch() throws SQLException, FileNotFoundException {
        connection = new ConnectDB();

        List<Reviews> searches = new ArrayList<>();
        if (selection != null && user_in != null) {

            //Integers require a different input when searching so I seperated them
            if (selection.equalsIgnoreCase("review_id") || selection.equalsIgnoreCase("review_score")) {
                
                //Without parsing for an int this statement its possible to use SQL injection here
                try {
                    Integer.parseInt(user_in);
                } catch (NumberFormatException ex) {
                    System.out.println("User attempted to enter a value other than an integer.");
                } finally {
                    query = String.format("SELECT %s FROM %s WHERE %s = %s", SELECTALL, TABLENAME, selection, user_in);
                }
            } else {
                //This queries string values hence the '' around %s
                query = String.format("SELECT %s FROM %s WHERE %s LIKE '%s' ", SELECTALL, TABLENAME, selection, user_in);
            }
        }

        try {
            PreparedStatement pst = connection.conn.prepareStatement(query);
            pst.execute();
            ResultSet rs = pst.getResultSet();

            while (rs.next()) {
                Reviews search = new Reviews();
                search.setId(rs.getInt(1));
                search.setGameName(rs.getString(2));
                search.setConsole(rs.getString(3));
                search.setYear(rs.getString(4));
                search.setReviewer(rs.getString(5));
                search.setReviewScore(rs.getInt(6));

                //Add the parsed info to the ArrayList
                searches.add(search);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searches;
    }

    /*
    * Getters and Setters
    */
    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void setUser_in(String user_in) {
        this.user_in = user_in;
    }

    public String getSelection() {
        return selection;
    }

    public String getUser_in() {
        return user_in;
    }
}
