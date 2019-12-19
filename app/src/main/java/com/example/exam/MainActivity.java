package com.example.exam;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tool_tv_main)
    TextView toolTvMain;
    @BindView(R.id.tool_main)
    Toolbar toolMain;
    @BindView(R.id.frag_main)
    FrameLayout fragMain;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.navi_main)
    NavigationView naviMain;
    @BindView(R.id.draw_main)
    DrawerLayout drawMain;
    private HomeFragment homeFragment;
    private WebFragment webFragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        onTool();
        onFragment();
    }

    //Fragment创建，添加，实现
    private void onFragment() {
        supportFragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        webFragment = new WebFragment();
        supportFragmentManager.beginTransaction().add(R.id.frag_main, homeFragment)
                .add(R.id.frag_main, webFragment).hide(webFragment).commit();
    }

    //ToolBar，侧滑，与fragment联动
    private void onTool() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawMain, toolMain, R.string.open, R.string.close);
        toggle.syncState();
        drawMain.addDrawerListener(toggle);
        drawMain.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                llMain.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        naviMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_item1) {
                    toolTvMain.setText("首页");
                    supportFragmentManager.beginTransaction().show(homeFragment).hide(webFragment).commit();
                } else {
                    toolTvMain.setText("我的");
                    supportFragmentManager.beginTransaction().show(webFragment).hide(homeFragment).commit();
                }
                return false;
            }
        });
    }

}
