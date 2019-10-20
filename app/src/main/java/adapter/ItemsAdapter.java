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

import Beans.Items;
import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class ItemsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Items> list;
    private int num1;

    public ItemsAdapter(Context context, ArrayList<Items> list) {
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
        final ViewHolder viewHolder=new ViewHolder(); ;
        view= LayoutInflater.from(context).inflate(R.layout.activity_item,null);
        viewHolder.titleView = (TextView) view.findViewById(R.id.restaurant_item_name);
        viewHolder.descriptionView = (TextView) view.findViewById(R.id.restaurant_item_description);
        viewHolder.priceView= (TextView) view.findViewById(R.id.restaurant_item_price);
        viewHolder.image= (ImageView) view.findViewById(R.id.restaurant_item_image);
        viewHolder.minus= (ImageView) view.findViewById(R.id.id_lv_minus);
        viewHolder.amountView= (TextView) view.findViewById(R.id.id_lv_num);
        viewHolder.add= (ImageView) view.findViewById(R.id.id_lv_add);
        view.setTag(viewHolder);

        viewHolder.titleView.setText(""+list.get(i).getTitle());
        viewHolder.descriptionView.setText(""+list.get(i).getDescription());
        viewHolder.priceView.setText(""+list.get(i).getPrice());

        Glide.with(viewHolder.image.getContext())
                .load(list.get(i).getImage())
                .into(viewHolder.image);

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = list.get(i).getNum();//获取到（减号）改动过的 数量
                num1 = num1 -1;
                if(num1 <=0){//逻辑判断，如果减为0的话，就一直为0，要不然就是符号了。
                    num1 =0;
                }
                viewHolder.amountView.setText(num1 +"");
                list.get(i).setNum(num1);
                onMyItemClickListener.onBtnItemListener(viewHolder.amountView,i, num1);//使用刚才自定义的接口。
            }
        });
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = list.get(i).getNum();//获取到（加号）改动过的 数量
                num1++;
                viewHolder.amountView.setText(num1 +"");
                list.get(i).setNum(num1);
                onMyItemClickListener.onBtnItemListener(viewHolder.amountView,i, num1);
            }
        });
        return view;
    }

    public interface OnMyItemClickListener{
        //ID  ， 数量
        void onBtnItemListener(TextView tv, int i, int num);
    }

    public OnMyItemClickListener onMyItemClickListener;

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener){
        this.onMyItemClickListener=onMyItemClickListener;
    }

    class ViewHolder {
        ImageView image;
        TextView titleView;
        TextView descriptionView;
        TextView priceView;
        TextView amountView;
        ImageView minus;
        ImageView add;
    }
}
