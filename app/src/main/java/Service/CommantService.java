package Service;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.Comments;
import DAO.CommentsDAO;
import androidx.annotation.NonNull;


public class CommantService implements CommentsDAO {

    DocumentReference mDoc  = FirebaseFirestore.getInstance().document("comments/commentJson");

    @Override
    public void addComment(Comments comments) {
        Map<String, Comments> data = new HashMap<>();
        data.put(comments.getId().toString(),comments);

        mDoc.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.e("ok","---------");
                }
                else{
                    Log.e("?????","!!!!!!!1");
                }
            }
        });

    }

    @Override
    public void updateComment(Integer id) {

    }

    @Override
    public void deleteComment(Integer id) {

    }

    @Override
    public List<Object> getCommentsByUser(String username) {



        return null;
    }

    @Override
    public List<Object> getCommentsByStore(String store) {
        return null;
    }
}
