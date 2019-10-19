package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Beans.MenuItem;
import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class MenuItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MenuItem> list;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder=new ViewHolder(); 
        view= LayoutInflater.from(context).inflate(R.layout.menu_item,null);
        viewHolder.titleView = (TextView) view.findViewById(R.id.restaurant_item_name);
        viewHolder.descriptionView = (TextView) view.findViewById(R.id.restaurant_item_description);
        viewHolder.priceView= (TextView) view.findViewById(R.id.restaurant_item_price);
        viewHolder.image= (ImageView) view.findViewById(R.id.restaurant_item_image);
        view.setTag(viewHolder);

        viewHolder.titleView.setText(""+list.get(i).getTitle());
        viewHolder.descriptionView.setText(""+list.get(i).getDescription());
        viewHolder.priceView.setText(""+list.get(i).getPrice());
        Glide.with(viewHolder.image.getContext())
                .load(list.get(i).getImage())
                .into(viewHolder.image);

        return view;
    }

    class ViewHolder {
        ImageView image;
        TextView titleView;
        TextView descriptionView;
        TextView priceView;
    }
}