package com.gameusingdynamicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.StatisticsInterface {

    public MainFragment.GameResultType mGameResultType;
    public MainFragment mainFragment;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
    }

    @Override
    public void showGameStatistics(int miNumberSet, int miNumberPlayerWin, int miNumberComWin, int miNumberDraw) {
        Intent intent = new Intent(this, GameStatisticsActivity.class);
        intent.putExtra("CountSet", miNumberSet);
        intent.putExtra("CountPlayerWin", miNumberPlayerWin);
        intent.putExtra("CountComWin", miNumberComWin);
        intent.putExtra("CountDraw", miNumberDraw);
        startActivity(intent);
    }
}
