package com.myasnikova.converterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Valute>
{
    private List<Valute> mainList;
    private List<ViewHolder>listViewHolder;
    List<String> listNotification;

    public SpinnerAdapter(@NonNull Context context, int resource, List<Valute> appList)
    {
        super(context, resource, appList);
        mainList=appList;
        listViewHolder=new ArrayList<>();
        listNotification=new ArrayList<>();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, null, false);
            viewHolder.tV_itemSpinner = convertView.findViewById(R.id.tV_itemSpinner);
            convertView.setTag(viewHolder);
            listViewHolder.add(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tV_itemSpinner.setText(String.valueOf(mainList.get(position).getName()));
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        convertView =  View.inflate(parent.getContext(), R.layout.spinner_item, null);
        TextView tV_itemSpinner = (TextView) convertView.findViewById(R.id.tV_itemSpinner);
        tV_itemSpinner.setText(String.valueOf(mainList.get(position).getName()));

        return convertView;
    }

    static class ViewHolder
    {
        TextView tV_itemSpinner;
    }



}
