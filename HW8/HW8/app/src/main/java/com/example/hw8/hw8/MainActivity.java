package com.example.hw8.hw8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private Spinner spnList;
    private Button btnInput, btnRecord;
    private EditText edtDate, edtCost;
    private DatePicker datePick;
    private ArrayList<String> data;
    private Formatter stringFormat;
    private StringBuilder stringBuild;
    private int inputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnList = (Spinner)findViewById(R.id.spn_list);
        btnInput = (Button) findViewById(R.id.btn_input);
        btnRecord = (Button) findViewById(R.id.btn_record);
        edtDate = (EditText) findViewById(R.id.edt_date);
        edtCost = (EditText) findViewById(R.id.edt_cost);
        datePick = (DatePicker) findViewById(R.id.datePick);
        stringBuild = new StringBuilder();
        stringFormat = new Formatter(stringBuild);
        data=new ArrayList();
        inputNumber = 0;

        ArrayAdapter itemList=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.item_list));
        spnList.setAdapter(itemList);

        datePick.setOnDateChangedListener(datePickerDateChange);
        btnInput.setOnClickListener(btnInputOnClick);
        btnRecord.setOnClickListener(btnRecordOnClick);
    }
    DatePicker.OnDateChangedListener datePickerDateChange = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String tempDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            edtDate.setText(tempDate);
        }
    };
    View.OnClickListener btnInputOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, edtCost.getText().toString(), Toast.LENGTH_SHORT).show();
            stringBuild.delete(0, stringBuild.length());
            stringFormat.format("項目%d  %10s  %10s  %5s", inputNumber++, edtDate.getText().toString(), spnList.getSelectedItem().toString(), edtCost.getText().toString());
            data.add(stringBuild.toString());
        }
    };
    View.OnClickListener btnRecordOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,DataActivity.class);
            intent.putStringArrayListExtra("data",data);
            startActivity(intent);
        }
    };
}
