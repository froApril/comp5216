package DAO;

import java.util.List;
import java.util.Map;

import Beans.CommentDetail;
import Beans.Comments;

public interface CommentsDAO {

    boolean addComment(Comments comments);

    void updateComment(Comments newComment);

}
