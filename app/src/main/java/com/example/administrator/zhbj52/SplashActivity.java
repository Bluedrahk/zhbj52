package com.example.administrator.zhbj52;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj52.utils.PrefUtils;

public class SplashActivity extends AppCompatActivity {

    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout)findViewById(R.id.rl_root);
        startAnim();
    }

    private void startAnim(){

        AnimationSet set = new AnimationSet(false);//动画集合

        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(2000);//动画时间
        rotate.setFillAfter(true);

        ScaleAnimation scale = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(2000);//动画时间
        scale.setFillAfter(true);

        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(1000);//动画时间
        alpha.setFillAfter(true);

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlRoot.startAnimation(set);

    }
    private void jumpNextPage(){
        //判断之前有没有显示过新手引导页
        boolean userGuide = PrefUtils.getBoolean(this,"is_user_guide_showed",false);
        if(!userGuide){
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
        } else{
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
        finish();
    }
}
