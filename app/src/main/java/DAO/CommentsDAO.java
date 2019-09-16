package DAO;

import java.util.List;

import Beans.Comments;

public interface CommentsDAO {
    List<Object> getCommentsByUser(String username);

    List<Object> getCommentsByStore(String store);

    void addComment(Comments comments);

    void updateComment(Integer id);

    void deleteComment(Integer id);


}
