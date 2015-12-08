package android.atilim.tr.questionsbank;

import android.app.Activity;
import android.atilim.tr.questionsbank.MainActivity;
import android.atilim.tr.questionsbank.R;
import android.content.Intent;
import android.graphics.Color;
import android.widget.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

class CustomAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    Activity activity;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, String[] prgmNameList) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        activity=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_items, null);
        holder.tv=(TextView) rowView.findViewById(R.id.listItemTextview);
        holder.tv.setText(result[position]);
        holder.tv.setBackgroundColor(getRandomColor(position));


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Grammer.class);
               activity.startActivity(intent);
            }
        });
        return rowView;
    }

    private int getRandomColor(int listOfposition) {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
