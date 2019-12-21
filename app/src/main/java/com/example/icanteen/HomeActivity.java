package com.example.icanteen;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.*;

public class HomeActivity extends Activity implements View.OnClickListener {
    //声明ViewPager
    private ViewPager mViewpager;

    //声明四个Tab
    private LinearLayout mTabHome;
    private LinearLayout mTabBoard;
    private LinearLayout mTabMine;

    //声明四个ImageButton
    private ImageButton mHomeImg;
    private ImageButton mBoardImg;
    private ImageButton mMineImg;

    //声明ViewPager的适配器
    private PagerAdapter mAdpater;
    //用于装载四个Tab的List
    private List<View> mTabs = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉TitleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_layout);
        initViews();//初始化控件
        initDatas();//初始化数据
        initEvents();//初始化事件

    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabHome.setOnClickListener(this);
        mTabBoard.setOnClickListener(this);
        mTabMine.setOnClickListener(this);

        //添加ViewPager的切换Tab的监听事件
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = mViewpager.getCurrentItem();
                //将所以的ImageButton设置成灰色
                resetImgs();
                //将当前Tab对应的ImageButton设置成绿色
                switch (currentItem) {
                    case 0:
                        mHomeImg.setImageResource(R.mipmap.home);
                        break;
                    case 1:
                        mBoardImg.setImageResource(R.mipmap.board);
                        break;
                    case 2:
                        mMineImg.setImageResource(R.mipmap.profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDatas() {
        //初始化ViewPager的适配器
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs.get(position));
            }
        };
        //设置ViewPager的适配器
        mViewpager.setAdapter(mAdpater);
    }

    //初始化控件
    private void initViews() {
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabBoard = (LinearLayout) findViewById(R.id.id_tab_board);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);


        mHomeImg = (ImageButton) findViewById(R.id.id_tab_home_img);
        mBoardImg = (ImageButton) findViewById(R.id.id_tab_board_img);
        mMineImg = (ImageButton) findViewById(R.id.id_tab_mine_img);


        //获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View tab1 = inflater.inflate(R.layout.tab1_layout, null);
        View tab2 = inflater.inflate(R.layout.tab2_layout, null);
        View tab3 = inflater.inflate(R.layout.tab3_layout, null);


        //将四个Tab添加到集合中
        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);


    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton都设置成灰色
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_home:
                //设置viewPager的当前Tab
                mViewpager.setCurrentItem(0);
                //将当前Tab对应的ImageButton设置成绿色
                mHomeImg.setImageResource(R.mipmap.home_actived);
                break;
            case R.id.id_tab_board:
                mViewpager.setCurrentItem(1);
                mBoardImg.setImageResource(R.mipmap.board_actived);
                break;
            case R.id.id_tab_mine:
                mViewpager.setCurrentItem(2);
                mMineImg.setImageResource(R.mipmap.profile_actived);
                break;
        }
    }

    //将四个ImageButton设置成灰色
    private void resetImgs () {
        mHomeImg.setImageResource(R.mipmap.home);
        mBoardImg.setImageResource(R.mipmap.board);
        mMineImg.setImageResource(R.mipmap.profile);
    }
}
