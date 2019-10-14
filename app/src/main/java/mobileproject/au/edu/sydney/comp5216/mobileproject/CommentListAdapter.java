package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.CommentDetail;


public class CommentListAdapter extends BaseAdapter {

    private List<CommentDetail>detailList;
    private LayoutInflater mInflater;

    public CommentListAdapter(LayoutInflater inflater,List<CommentDetail>detailList){
        this.mInflater = inflater;
        this.detailList = detailList;
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.activity_comment_item,null);
        CommentDetail commentDetail = detailList.get(position);
        TextView username = view.findViewById(R.id.comment_username);
        TextView message = view.findViewById(R.id.comment_message);
        SimpleRatingBar rating = view.findViewById(R.id.ratingbar);
        username.setText(commentDetail.getUsername());
        message.setText(commentDetail.getDetail());
        rating.setRating(commentDetail.getSocre());
        return view;
    }
}
