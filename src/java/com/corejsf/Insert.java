package com.corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Justin Wheeler.
 */
@ManagedBean(name="user")
@RequestScoped
public class Insert {
    Connection conn;
    private int id;
    private int reviewScore;
    private String gameName; 
    private String console; 
    private String year; 
    private String reviewer;    
    
    public void insertReview() throws SQLException{
    String connectionURL = "";  
    conn = DriverManager.getConnection(connectionURL); 
    
    String insertNewReview = "INSERT INTO REVIEWS VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(insertNewReview);
    pstmt.setInt(1, id);
    pstmt.setString(2, gameName);
    pstmt.setString(3, console);
    pstmt.setString(4, year);
    pstmt.setString(5, reviewer);
    pstmt.setInt(6, reviewScore);
    pstmt.executeUpdate();
  }

    public Connection getConn() {
        return conn;
    }

    public int getId() {
        return id;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public String getGameName() {
        return gameName;
    }

    public String getConsole() {
        return console;
    }

    public String getYear() {
        return year;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
