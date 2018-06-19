package com.galleryusingviewanimation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09,
            R.drawable.img10, R.drawable.img11, R.drawable.img12};

    private Integer[] miThumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th, R.drawable.img09th,
            R.drawable.img10th, R.drawable.img11th, R.drawable.img12th};

    Animation[] animationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgad = new ImageAdapter(this, miThumbImgArr);

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgad);

        makeAnimations();

        mGridView.setOnItemClickListener(getGridViewOnItemClick_AnimVersion);
    }

    protected void makeAnimations() {
        int width = mImgSwitcher.getWidth();
        int height = mImgSwitcher.getHeight();

        ScaleAnimation scale;
        RotateAnimation rotate;
        TranslateAnimation translate;

        AlphaAnimation alpha_in = new AlphaAnimation(0.3f, 0.7f);
        alpha_in.setInterpolator(new LinearInterpolator());
        alpha_in.setDuration(800);
        AlphaAnimation alpha_out = new AlphaAnimation(0.7f, 0.3f);
        alpha_out.setInterpolator(new LinearInterpolator());
        alpha_out.setDuration(800);

        AnimationSet scale_rotate_trans_in = new AnimationSet(false);
        scale = new ScaleAnimation(0.3f, 0.7f, 0.3f, 0.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(800);
        scale.setDuration(800);
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(800);
        rotate.setDuration(800);
        translate = new TranslateAnimation(1f, 0f, -300f, 0f);
        translate.setInterpolator(new LinearInterpolator());
        translate.setStartOffset(800);
        translate.setDuration(800);
        scale_rotate_trans_in.addAnimation(scale);
        scale_rotate_trans_in.addAnimation(rotate);
        scale_rotate_trans_in.addAnimation(translate);
        AnimationSet scale_rotate_trans_out = new AnimationSet(false);
        scale = new ScaleAnimation(0.7f, 0.3f, 0.7f, 0.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(800);
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setDuration(800);
        translate = new TranslateAnimation(1f, 0f, 0f, 300f);
        translate.setInterpolator(new LinearInterpolator());
        translate.setDuration(800);
        scale_rotate_trans_out.addAnimation(scale);
        scale_rotate_trans_out.addAnimation(rotate);
        scale_rotate_trans_out.addAnimation(translate);

        AnimationSet scale_rotate_in = new AnimationSet(false);
        scale = new ScaleAnimation(0.3f, 0.7f, 0.3f, 0.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(800);
        scale.setDuration(800);
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(800);
        rotate.setDuration(800);
        scale_rotate_in.addAnimation(scale);
        scale_rotate_in.addAnimation(rotate);
        AnimationSet scale_rotate_out = new AnimationSet(false);
        scale = new ScaleAnimation(0.7f, 0.3f, 0.7f, 0.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(800);
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setDuration(800);
        scale_rotate_out.addAnimation(scale);
        scale_rotate_out.addAnimation(rotate);

        TranslateAnimation trans_in =new TranslateAnimation(1f, 0f, 0f, 300f);
        translate.setInterpolator(new LinearInterpolator());
        translate.setDuration(800);

        /* ============== Init trans_out animation ============= */
        TranslateAnimation trans_out = new TranslateAnimation(0f, 0f, 0f, 300f);
        trans_out.setInterpolator(new LinearInterpolator());
        trans_out.setDuration(3000);

        animationList = new Animation[] {
                alpha_in,
                alpha_out,
                scale_rotate_trans_in,
                scale_rotate_trans_out,
                scale_rotate_in,
                scale_rotate_out,
                trans_in,
                trans_out
        };
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }


    private AdapterView.OnItemClickListener gridViewOnItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            switch ((int)(Math.random()*4 + 1)) {
                case 1:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.alpha_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.alpha_out));
                    break;
                case 2:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.trans_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.trans_out));
                    break;
                case 3:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.scale_rotate_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.scale_rotate_out));
                    break;
                case 4:
                    mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.scale_rotate_trans_in));
                    mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.scale_rotate_trans_out));
                }


                mImgSwitcher.setImageResource(miImgArr[position]);
        }
    };

    private AdapterView.OnItemClickListener getGridViewOnItemClick_AnimVersion = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int selection = (int)Math.floor(Math.random() * 4);
            mImgSwitcher.setInAnimation(animationList[selection * 2]);
            mImgSwitcher.setOutAnimation(animationList[selection * 2 + 1]);
            mImgSwitcher.setImageResource(miImgArr[position]);
        }
    };
}
