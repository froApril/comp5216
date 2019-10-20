package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Beans.OrderItem;
import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class OrderItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderItem> list;

    public OrderItemAdapter(Context context, ArrayList<OrderItem> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OrderItem getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder=new ViewHolder();
        view= LayoutInflater.from(context).inflate(R.layout.order_item,null);
        viewHolder.tableIDView = (TextView) view.findViewById(R.id.tableID);
        viewHolder.orderTimeView = (TextView) view.findViewById(R.id.orderTime);
        viewHolder.priceView= (TextView) view.findViewById(R.id.totalPrice);
        view.setTag(viewHolder);

        viewHolder.tableIDView.setText(""+list.get(i).getTableID());
        viewHolder.orderTimeView.setText(""+list.get(i).getOrderTime());
        viewHolder.priceView.setText(""+list.get(i).getTotalPrice());

        return view;
    }

    class ViewHolder {
        TextView tableIDView;
        TextView orderTimeView;
        TextView priceView;
    }
}
