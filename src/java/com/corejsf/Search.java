package com.corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Justin Wheeler.
 */

@ManagedBean(name = "search", eager = true)
@SessionScoped
public class Search {
  private String selection;
  private String user_in;
  static Connection conn;
  private String sta = "SELECT * from REVIEWS";
  
  public List<Reviews> getSearch() throws SQLException{
    String connectionURL = "";  
    conn = DriverManager.getConnection(connectionURL);
    List<Reviews> searches = new ArrayList<Reviews>();
    if (selection != null && user_in != null){
        //Its important to add '' when searching for strings
        if (selection.equalsIgnoreCase("review_id") || selection.equalsIgnoreCase("review_score")){
            sta = "SELECT * from REVIEWS where "+selection+" = "+user_in;                        
        }else
            sta = "SELECT * from REVIEWS where "+selection+" = '"+user_in+"'";
    }
    
    try {
        PreparedStatement pst = conn.prepareStatement(sta);
        pst.execute();
        ResultSet rs = pst.getResultSet();
        
        while(rs.next()){
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
    }catch(SQLException e){
        e.printStackTrace();
    }  
    return searches;
  }

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