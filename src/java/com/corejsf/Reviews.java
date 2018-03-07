package com.corejsf;

/**
 *
 * @author J. Wheeler
 */

public class Reviews {
    private int id;
    private int reviewScore;
    private String gameName; 
    private String console; 
    private String year; 
    private String reviewer;
    
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
}
