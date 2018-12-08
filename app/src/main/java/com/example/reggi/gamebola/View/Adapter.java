package com.example.reggi.gamebola.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reggi.gamebola.R;

import java.util.ArrayList;
import java.util.Collections;

public class Adapter extends BaseAdapter {
    protected LayoutInflater inflater;
    protected ArrayList<Integer> list;

    public Adapter(LayoutInflater inflater){
        this.inflater=inflater;
        this.list=new ArrayList<>();
    }

    public static Adapter newInstance(LayoutInflater inflater){
        Adapter result = new Adapter(inflater);
        result.inflater = inflater;
        return result;
    }

    public void addLine(int newLine){
        this.list.add(newLine);
        notifyDataSetChanged();
    }

    public void setData(ArrayList<Integer> newData){
        if (this.list == null){
            this.list = new ArrayList<>();
        }
        this.list.clear();
        this.list.addAll(newData);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result=inflater.inflate(R.layout.list_items,null);
        TextView tvScore = result.findViewById(R.id.score);
        TextView rank = result.findViewById(R.id.rank);

        descending();
        String listRank;

        if(position==0) {
            listRank = (position + 1) +""+ "st";
        }
        else if(position==1){
            listRank = (position + 1) +""+ "nd";
        }
        else if(position==2){
            listRank = (position + 1) +""+ "rd";
        }
        else{
            listRank = (position + 1) +""+ "th";
        }
        rank.setText(listRank);
        tvScore.setText(this.list.get(position).toString());
        return result;
    }

    public void descending(){
        Collections.sort(this.list);
        Collections.reverse(this.list);

    }
}
