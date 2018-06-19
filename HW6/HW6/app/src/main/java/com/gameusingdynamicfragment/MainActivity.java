package com.gameusingdynamicfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackInterface {

    private final static String TAG = "Result";
    private int mCount = 0;
    public MainFragment.GameResultType gameResultType;
    public MainFragment mainFragment;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
    }

    @Override
    public void updateGameResult(int iNumberSet, int iNumberPlayerWin, int iNumberComWin, int iNumberDraw) {
        if (findViewById(R.id.frameLay).isShown()) {
            switch (gameResultType) {
                case TYPE_1:
                    ((GameResultFragment) fragResult).updateGameResult(iNumberSet, iNumberPlayerWin,
                            iNumberComWin, iNumberDraw);
                    break;
                case TYPE_2:
                    ((GameResult2Fragment) fragResult).updateGameResult(iNumberSet, iNumberPlayerWin,
                            iNumberComWin, iNumberDraw);
                    break;
            }
        }

    }

    public void InvokeUpdateResult() {
        mainFragment.UpdateResult();
    }

    @Override
    public void enableGameResult(MainFragment.GameResultType type) {
        FragmentTransaction fragTran;
        String FragTag;

        switch (type) {
            case TYPE_1:
                GameResultFragment frag = new GameResultFragment();
                fragTran = getSupportFragmentManager().beginTransaction();
                mCount++;
                FragTag = TAG + mCount;
                fragTran.replace(R.id.frameLay, frag, FragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;
            case TYPE_2:
                GameResult2Fragment frag2 = new GameResult2Fragment();
                fragTran = getSupportFragmentManager().beginTransaction();
                mCount++;
                FragTag = TAG + mCount;
                fragTran.replace(R.id.frameLay, frag2, FragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;
            case HIDE:
                FragmentManager fragMgr = getSupportFragmentManager();
                FragTag = TAG + mCount;
                Fragment fragGameResult = fragMgr.findFragmentByTag(FragTag);
                if (fragGameResult != null) {
                    fragTran = fragMgr.beginTransaction();
                    fragTran.remove(fragGameResult);
                    fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragTran.addToBackStack(null);
                    fragTran.commit();
                }
                break;
        }

    }
}
