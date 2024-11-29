/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * The Comments class represents user comments and ratings for houses. It
 * includes details such as comment ID, comment content, rating, comment date,
 * user ID, and house ID.
 *
 */
public class Comments {

    private int commentID;           // Unique ID for the comment
    private String comment;          // Content of the comment
    private double rate;             // Rating given by the user
    private Date dateComment;        // Date when the comment was made
    private int userID;              // ID of the user who made the comment
    private int houseID;             // ID of the house being commented on

    /**
     * Default constructor for the Comments class.
     */
    public Comments() {
    }

    /**
     * Parameterized constructor for the Comments class.
     *
     * @param commentID The unique ID for the comment
     * @param comment The content of the comment
     * @param rate The rating given by the user
     * @param dateComment The date when the comment was made
     * @param userID The ID of the user who made the comment
     * @param houseID The ID of the house being commented on
     */
    public Comments(int commentID, String comment, double rate, Date dateComment, int userID, int houseID) {
        this.commentID = commentID;
        this.comment = comment;
        this.rate = rate;
        this.dateComment = dateComment;
        this.userID = userID;
        this.houseID = houseID;
    }

    // Getters and setters for class attributes
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Comments object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Comments{" + "commentID=" + commentID + ", comment=" + comment + ", rate=" + rate + ", dateComment=" + dateComment + ", userID=" + userID + ", houseID=" + houseID + '}';
    }

}
