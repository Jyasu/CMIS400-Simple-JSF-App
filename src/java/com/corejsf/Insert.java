package com.corejsf;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Justin Wheeler.
 */
@ManagedBean(name = "user")
@RequestScoped
public class Insert {

    private ConnectDB connection;
    private int reviewScore;
    private String gameName;
    private String console;
    private String year;
    private String reviewer;

    //Make constant to prevent tampering
    private static final String TABLENAME = "REVIEWS";
    private static final String COUNT_ROWS = String.format("SELECT COUNT(review_id) FROM %s", TABLENAME);

    //Counts the number of rows in the reviews table and returns the next review_id
    private int countRows() throws SQLException {
        int newCount = 0;

        PreparedStatement statement = connection.conn.prepareStatement(COUNT_ROWS);
        statement.execute();

        ResultSet result = statement.getResultSet();
        while (result.next()) {
            newCount = result.getInt(1);
        }
        //Returns the number of rows +1 to set value to the next highest review_id
        return newCount + 1;
    }
    
    //Inserts form values into reviews table
    public void insertReview() throws SQLException, FileNotFoundException {
        connection = new ConnectDB();
        int review_id = countRows();

        String insertNewReview = String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?)", TABLENAME);
        PreparedStatement pStatement = connection.conn.prepareStatement(insertNewReview);
        
        //review_id is set automatically to the very next review_id in the table
        pStatement.setInt(1, review_id);
        //Sets the values corresponding to user input
        pStatement.setString(2, gameName);
        pStatement.setString(3, console);
        pStatement.setString(4, year);
        pStatement.setString(5, reviewer);
        pStatement.setInt(6, reviewScore);
        pStatement.executeUpdate();
    }

    /*
    * Getters and Setters
    */
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
