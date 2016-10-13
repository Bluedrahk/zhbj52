package com.example.administrator.zhbj52;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.zhbj52.fragment.ContentFragment;
import com.example.administrator.zhbj52.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        //slidingMenu.setSecondaryMenu(R.layout.right_menu);
        //slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setBehindOffset(350);//设置预留屏幕的宽度
    }
    //初始化fragment，将fragment数据填充给布局文件
    private void initFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(), FRAGMENT_LEFT_MENU);//用fragment替换fragmentLayout
        transaction.replace(R.id.fl_content,new ContentFragment(),FRAGMENT_CONTENT);
        transaction.commit();
    }
}