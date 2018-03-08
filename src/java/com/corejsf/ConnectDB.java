package com.corejsf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
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

/**
 * @author Justin Wheeler
 */
@ManagedBean(name = "connectdb", eager = true)
@SessionScoped
public class ConnectDB implements Serializable {

    public Connection conn;

    //Using constants to avoid misuse
    private static final String SELECTALL = "*";
    private static final String TABLENAME = "REVIEWS";
    private static final String REVIEW_ID = "review_id";

    //Default prepared statement to display database on index page
    private static final String QUERY = String.format("SELECT %s from %s ORDER BY %s", SELECTALL, TABLENAME, REVIEW_ID);


    //Constructor
    public ConnectDB() throws FileNotFoundException, SQLException {
        String connectionURL = getConnectionURL();
        this.conn = DriverManager.getConnection(connectionURL);
    }

    //Gets the connection url/username/pass from document to avoid hard-coding passwords
    private String getConnectionURL() throws FileNotFoundException {
        URL path = ConnectDB.class.getResource("DBConnection.txt");
        File file = new File(path.getFile());
        String connectionURL;
        try (Scanner input = new Scanner(file)) {
            connectionURL = input.nextLine();
        }
        return connectionURL;
    }

    //Returns database query as list to be displayed on index.xhtml
    public List<Reviews> getReviews() throws SQLException, FileNotFoundException {

        List<Reviews> reviews = new ArrayList<Reviews>();
        try {
            PreparedStatement pst = conn.prepareStatement(QUERY);
            pst.execute();

            ResultSet rs = pst.getResultSet();

            //Sets the results of query to their respective variables
            while (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }



}
