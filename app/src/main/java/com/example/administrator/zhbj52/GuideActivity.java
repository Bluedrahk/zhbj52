package com.example.administrator.zhbj52;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj52.utils.PrefUtils;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private static final int[] mImageIds = new int[]{
            R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3
    };
    private ViewPager vpGuide;
    private ArrayList<ImageView> mImageViewList;
    private LinearLayout llPointGroup;
    private int mPointWidth;
    private View viewRedPoint;
    private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        vpGuide = (ViewPager)findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout)findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        btnStart = (Button)findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到主页面,表示已经展示了新手引导，把sp的Boolean设置为true；
                PrefUtils.setBoolean(GuideActivity.this,"is_user_guide_showed",true);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        initViews();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.addOnPageChangeListener(new GuidePageListener());

    }

    private void initViews(){
        mImageViewList = new ArrayList<ImageView>();
        for(int i=0;i<mImageIds.length;i++){
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(image);
        }
        for(int i=0;i<mImageIds.length;i++){
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,15);
            if(i>0){
                params.leftMargin = 15;
            }
            point.setLayoutParams(params);
            llPointGroup.addView(point);
        }

        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当layout执行结束回调此方法，l对layout视图树进行监听，等layout结束后再获取宽度
            @Override
            public void onGlobalLayout() {
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);//API 16以上才使用
                mPointWidth = llPointGroup.getChildAt(1).getLeft()-llPointGroup.getChildAt(0).getLeft();
            }
        });

    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    class GuidePageListener implements ViewPager.OnPageChangeListener{

        //滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            System.out.println("当前位置："+position+"，百分比："+"positionOffset"+",移动距离："+
            positionOffsetPixels);
            int len = (int)(mPointWidth * positionOffset) + position * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)viewRedPoint.getLayoutParams();
            params.leftMargin = len;
            viewRedPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if(position == mImageIds.length-1){
                btnStart.setVisibility(View.VISIBLE);
            }else{
                btnStart.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



}
