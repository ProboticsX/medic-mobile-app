package com.example.john.medic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by john on 4/4/2018.
 */

public class problemadaptor extends BaseAdapter{

    ArrayList<String> problemname;
    Context context;

    public problemadaptor(ArrayList<String> problemname, Context context) {
        this.problemname = problemname;
        this.context = context;
    }

    @Override
    public int getCount() {
        return problemname.size();
    }

    @Override
    public String getItem(int i) {
        return problemname.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

View view1=view;
if (view==null){
    LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view1=inflater.inflate(R.layout.problem_list_item,viewGroup,false);
    ViewHolder holder=new ViewHolder();
    holder.diseasename=view1.findViewById(R.id.diseasename);
    view1.setTag(holder);
}
ViewHolder holder1=(ViewHolder) view1.getTag();
String expense=getItem(i);
holder1.diseasename.setText(expense);
holder1.diseasename.setTextColor(Color.RED);
return view1;
    }
    public class ViewHolder{
        TextView diseasename;
    }
}
