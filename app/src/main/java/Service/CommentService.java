package Service;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.CommentDetail;
import Beans.Comments;
import DAO.CommentsDAO;
import androidx.annotation.NonNull;


public class CommentService implements CommentsDAO {

    DatabaseReference mDatabase;

    @Override
    public boolean addComment(Comments comments) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("restaurants").child(comments.getStorename())
                .child("comments").child(comments.getUsername())
                .setValue(comments.getCommentDetail())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("ok!!","insert");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("problem", e + "");
                    }
                });
        return true;
    }

    @Override
    public void updateComment(Comments newComment) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("restaurants").child(newComment.getStorename())
                .child("comments").child(newComment.getUsername())
                .setValue(newComment.getCommentDetail())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("update","ok!!!!");
                    }
                });

    }


}
