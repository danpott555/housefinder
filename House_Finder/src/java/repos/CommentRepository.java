/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import dal.CommentDAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comments;

/**
 * The CommentRepository class acts as an intermediary between the business
 * logic and the data access layer for managing comments on houses.
 *
 * This class provides methods to retrieve comments by house ID and to add new
 * comments to houses.
 *
 * It implements the ICommentRepository interface to define the contract for
 * comment-related operations.
 *
 */
public class CommentRepository implements ICommentRepository{

    @Override
    public List<Comments> getCommentByHouseID(int houseID) {
        try {
            return new CommentDAO().getCommentByHouseID(houseID);
        } catch (Exception ex) {
            Logger.getLogger(CommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void addCommenr(String comment, double rate, Date dateComment, int userID, int houseID) {
         try {
            new CommentDAO().addCommenr(comment, rate, dateComment, userID, houseID);
        } catch (Exception ex) {
            Logger.getLogger(CommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
