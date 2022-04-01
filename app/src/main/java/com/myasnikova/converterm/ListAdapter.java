package com.myasnikova.converterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Valute>
{
    private List<Valute> mainList;//доп. список чтобы можно было обратиться к appList, кот. передается через конструктор
    List<String> listNotification;
    Double margin;

    public ListAdapter(@NonNull Context context, int resource, List<Valute> appList)
    {
        super(context, resource, appList);
        mainList=appList;
        listNotification=new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView==null) //если элемент создается в первый раз
        {
            viewHolder= new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);//надувание
            viewHolder.tV_nominal=convertView.findViewById(R.id.tV_nominal);
            viewHolder.tV_name=convertView.findViewById(R.id.tV_name);
            viewHolder.tV_value=convertView.findViewById(R.id.tV_value);
            viewHolder.tV_margin=convertView.findViewById(R.id.tV_margin);
            viewHolder.img_margin=convertView.findViewById(R.id.img_margin);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tV_nominal.setText(String.valueOf(mainList.get(position).getNominal() + " " + mainList.get(position).getCharCode()));
        viewHolder.tV_name.setText(mainList.get(position).getName());
        viewHolder.tV_value.setText(String.valueOf(mainList.get(position).getValue()));
        margin(position);

        viewHolder.tV_margin.setText(String.valueOf(margin));

        if (margin>0)
        {
            viewHolder.img_margin.setImageResource(R.drawable.ic_up);
        }
        else if (margin<0)
        {
            viewHolder.img_margin.setImageResource(R.drawable.ic_down);
        }

        return convertView;
    }

    static class ViewHolder //класс для хранения элементов списка, когда те скрываются при прокручивании
    {
        TextView tV_nominal;
        TextView tV_name;
        TextView tV_value;
        TextView tV_margin;
        ImageView img_margin;
    }

    private void margin(int position)
    {
        double temp=mainList.get(position).getValue() - mainList.get(position).getPrevious();
        int iValue=(int)(temp*1000);
        double dValue=temp*1000;
        if (dValue-iValue>=0.5)
        {
            iValue+=1;
        }
        dValue=(double) iValue;
        margin=dValue/1000;
    }
}
