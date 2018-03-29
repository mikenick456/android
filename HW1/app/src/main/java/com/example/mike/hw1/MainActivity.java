package com.example.mike.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button btnDoSug;
    private EditText edtSex, edtAge;
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDoSug = (Button)findViewById(R.id.button);
        edtSex = (EditText)findViewById(R.id.edtSex);
        edtAge = (EditText)findViewById(R.id.edtAge);
        txtResult = (TextView)findViewById(R.id.txtResult);
        btnDoSug.setOnClickListener(btnDoSugOnClick);
    }
    private Button.OnClickListener btnDoSugOnClick =  new Button.OnClickListener(){
        public void onClick(View v){
            String strSex = edtSex.getText().toString();
            int iAge = Integer.parseInt(edtAge.getText().toString());

            String strSug = "結果: ";
            if(strSex.equals("男"))
                if(iAge<=30)
                    strSug+="不急";
                else if(iAge>=35)
                    strSug+="趕快結婚";
                else
                    strSug+="開始找對象";
            else
                if(iAge<=28)
                    strSug+="不急";
                else if(iAge>=32)
                    strSug+="趕快結婚";
                else
                    strSug+="開始找對象";
            txtResult.setText(strSug);
        }
    };
}
