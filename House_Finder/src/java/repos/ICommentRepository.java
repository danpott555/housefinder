/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import java.util.Date;
import java.util.List;
import model.Comments;

/**
 *
 * @author ADMIN
 */
public interface ICommentRepository {

    //Retrieve a list of comments associated with a specific house ID.
    List<Comments> getCommentByHouseID(int houseID);

    //Add a new comment to a house, including the comment text, rating, date of the comment, user ID, and house ID.
    void addCommenr(String comment, double rate, Date dateComment, int userID, int houseID);
}
