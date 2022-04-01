package com.myasnikova.converterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter extends AppCompatActivity
{

    EditText edittxt_ru;
    TextView tV_ru;
    Spinner spinner_valute;
    TextView tV_valute;
    SpinnerAdapter spinnerAdapter;
    String charCode;
    String data;
    Gson gson;
    Root root;
    List<Valute> valute;
    int valuteItemFromMain;
    Valute valute_item;
    Valute valuteFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        init();

        Intent intent = getIntent();
        charCode = intent.getStringExtra("charCode");
        data = intent.getStringExtra("data");
        root=gson.fromJson(data, Root.class);
        list_init();
        valuteItemFromMain=valuteFromMain.getNominal();
        edittxt_ru.setText(String.valueOf(valuteItemFromMain));


        edittxt_ru.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String str=s.toString();
                int lenght=str.length();
                if (lenght>0)
                {
                    valuteItemFromMain=Integer.parseInt((s.toString()));
                }
                else
                {
                    valuteItemFromMain=0;
                }

                convertor();
            }
        });


        tV_ru.setText(valuteFromMain.getName());

        spinnerAdapter= new SpinnerAdapter(getApplicationContext(), R.layout.spinner_item, valute);
        spinner_valute.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged ();
        spinner_valute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                valute_item=valute.get(position);
                convertor();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

    }

    public void init()
    {
        edittxt_ru=findViewById(R.id.edittxt_ru);
        tV_ru=findViewById(R.id.tV_ru);
        spinner_valute=findViewById(R.id.spinner_valute);
        tV_valute=findViewById(R.id.tV_valute);
        gson=new Gson();
        valute=new ArrayList<>();
        valute_item=new Valute();
        valuteFromMain=new Valute();
    }


    public void list_init()
    {
        for (Map.Entry<String, Valute> entry : root.getValute().entrySet())
        {
            Valute valuteItem = new Valute();
            valuteItem.setID(entry.getValue().getID());
            valuteItem.setNumCode(entry.getValue().getNumCode());
            valuteItem.setCharCode(entry.getValue().getCharCode());
            valuteItem.setNominal(entry.getValue().getNominal());
            valuteItem.setName(entry.getValue().getName());
            valuteItem.setValue(entry.getValue().getValue());
            valuteItem.setPrevious(entry.getValue().getPrevious());

            if (valuteItem.getCharCode().equals(charCode)) {
                valuteFromMain = valuteItem;
            }
            valute.add(valuteItem);
        }
    }

    public void convertor()
    {
        double nominal= Double.parseDouble(String.valueOf(valute_item.getValue()));
        double nominalFromMain= Double.parseDouble(String.valueOf(valuteFromMain.getValue()));

        double temp=(nominal*valuteItemFromMain/nominalFromMain);

        int iValue=(int)(temp*1000);
        double dValue=temp*1000;
        if (dValue-iValue>=0.5)
        {
            iValue+=1;
        }
        dValue=(double) iValue;
        Double val=dValue/1000;
        tV_valute.setText(String.valueOf(val));
    }
}