package edu.ntut.user.myhw3;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import  android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    private MainActivity self;
    private RadioButton mSpnMale;
    private RadioButton mSpnFemale;
    private RadioGroup mRadAge;
    private RadioGroup mRadSex;
    private Spinner mSpnAgeRange;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxthabit;
    private CheckBox mChkmusic;
    private CheckBox mChksing;
    private CheckBox mChkdance;
    private CheckBox mChktrival;
    private CheckBox mChkread;
    private CheckBox mChkwrite;
    private CheckBox mChkclimbing;
    private CheckBox mChkswimming;
    private CheckBox mChkfood;
    private CheckBox mChkdrawing;
    private String selectedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self = this;
        mSpnMale = (RadioButton) findViewById(R.id.rabmale);
        mSpnFemale = (RadioButton) findViewById(R.id.rabfemale);
        mRadSex = (RadioGroup) findViewById(R.id.radGrpSex);
        mRadAge = (RadioGroup) findViewById(R.id.radGrpAge);
        mChkmusic = (CheckBox) findViewById(R.id.chkmusic);
        mChksing = (CheckBox) findViewById(R.id.chksing);
        mChkdance = (CheckBox) findViewById(R.id.chkdance);
        mChkread = (CheckBox) findViewById(R.id.chkread);
        mChkwrite = (CheckBox) findViewById(R.id.chkwrite);
        mChkclimbing = (CheckBox) findViewById(R.id.chkclimbing);
        mChkswimming = (CheckBox) findViewById(R.id.chkswimming);
        mChkfood = (CheckBox) findViewById(R.id.chkfood);
        mChkdrawing = (CheckBox) findViewById(R.id.chkdrawing);
        mChktrival = (CheckBox) findViewById(R.id.chktrival);
        mSpnAgeRange = (Spinner) findViewById(R.id.spnRnAge);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxthabit =  (TextView) findViewById(R.id.txthabit);
        mRadSex.setOnCheckedChangeListener(spnsexSelect);
        mBtnOK.setOnClickListener(btnOKOnClick);
    }

    private RadioGroup.OnCheckedChangeListener spnsexSelect = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            RadioButton target = (RadioButton) radioGroup.findViewById(id);
            Resources res = getResources();
            ArrayAdapter<String> adapter;

            if (target == mSpnMale) {
                adapter = new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1, res.getStringArray(R.array.mage_list));
            } else {
                adapter = new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1, res.getStringArray(R.array.fmage_list));
            }

            mSpnAgeRange.setAdapter(adapter);
        }
    };


    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MarriageSuggestion ms = new MarriageSuggestion();           // 建立「婚姻建議」物件
            boolean isMale = mSpnMale.isChecked();
            String sexStr = isMale ? "male" : "female";

            if (isMale) {
                switch (mSpnAgeRange.getSelectedItem().toString()) {
                    case "小於30歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 1));
                        break;
                    case "30~40歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 2));
                        break;
                    case "大於40歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 3));
                }
            } else {
                switch (mSpnAgeRange.getSelectedItem().toString()) {
                    case "小於28歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 1));
                        break;
                    case "28~35歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 2));
                        break;
                    case "大於35歲":
                        mTxtSug.setText(ms.getSuggestion(sexStr, 3));
                }
            }
            String s = getString(R.string.habit);
            if (mChkmusic.isChecked())
                s += mChkmusic.getText().toString();
            if (mChksing.isChecked())
                s += mChksing.getText().toString();
            if (mChkdance.isChecked())
                s += mChkdance.getText().toString();
            if (mChktrival.isChecked())
                s += mChktrival.getText().toString();
            if (mChkread.isChecked())
                s += mChkread.getText().toString();
            if (mChkwrite.isChecked())
                s += mChkwrite.getText().toString();
            if (mChkclimbing.isChecked())
                s += mChkclimbing.getText().toString();
            if (mChkswimming.isChecked())
                s += mChkswimming.getText().toString();
            if (mChkfood.isChecked())
                s += mChkfood.getText().toString();
            if (mChkdrawing.isChecked())
                s += mChkdrawing.getText().toString();
            mTxthabit.setText(s);
        }
    };
}
