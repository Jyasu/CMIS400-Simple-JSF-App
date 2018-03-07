package com.corejsf;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "connectdb", eager = true)
@SessionScoped
public class ConnectDB {
  static Connection conn;
  private static final String tableName = "REVIEWS";
  
  private static final String sta = String.format("SELECT %s from %s", "*", tableName);
  
  public String getConnectionURL() throws FileNotFoundException{
      URL path = ConnectDB.class.getResource("DBConnection.txt");
      File file = new File(path.getFile());
      Scanner input = new Scanner(file);
      
      String connectionURL = input.nextLine();
      input.close();
      return connectionURL;
  }
  
  public List<Reviews> getReviews() throws SQLException, FileNotFoundException{
    String connectionURL = getConnectionURL();  
    conn = DriverManager.getConnection(connectionURL);
    List<Reviews> reviews = new ArrayList<Reviews>();
    try {
        PreparedStatement pst = conn.prepareStatement(sta);
        pst.execute();
        ResultSet rs = pst.getResultSet();
        
        while(rs.next()){
            Reviews review = new Reviews();
            review.setId(rs.getInt(1));
            review.setGameName(rs.getString(2));
            review.setConsole(rs.getString(3));
            review.setYear(rs.getString(4));
            review.setReviewer(rs.getString(5));
            review.setReviewScore(rs.getInt(6));
            
            //Add the parsed info to the ArrayList
            reviews.add(review);
        }
    }catch(SQLException e){
        e.printStackTrace();
    }  
    return reviews;
  }
}