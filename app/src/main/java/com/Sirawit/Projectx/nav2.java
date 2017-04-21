package com.Sirawit.Projectx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.OnClick;
import devlight.io.library.ntb.NavigationTabBar;


public class nav2 extends AppCompatActivity {
public String password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav2);
        initUI();



    }

    public void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                if (position == 3) {
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_channelforfollow, null, false);

                    final EditText txtPages = (EditText) view.findViewById(R.id.simpleEditText);
                    final Button displays = (Button) view.findViewById(R.id.display);
                    displays.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            password = txtPages.getText().toString();
                            Intent intent = new Intent(nav2.this, GMapsFollowLocationActivity.class);
                            intent.putExtra("channel", password);
                            startActivity(intent);
                        }

                    });
                    container.addView(view);
                    return view;
                }  if(position == 2) {
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_mode, null, false);
                    Button Butfollow = (Button)view.findViewById(R.id.tofap);
                    Butfollow.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(nav2.this, nav2.class);
                            startActivity(intent);
                        }
                    });
                    Button ButShared = (Button)view.findViewById(R.id.tomap);
                    ButShared.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(nav2.this, Navshared.class);
                            startActivity(intent);
                        }
                    });
                    container.addView(view);
                    return view;
                }
                if(position == 4){
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_aboutme, null, false);
                    container.addView(view);
                    return view;
                }
      return null;
            }

        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ff),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ff1))
                        .title("Mode")
                        .badgeTitle("^_^")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.in2),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.in))
                        .title("Phone Number")
                        .badgeTitle("Shared")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh),
                        Color.parseColor(colors[2]))
                        //    .selectedIcon(getResources().getDrawable(R.drawable.cha))
                        .title("Channel")
                        .badgeTitle("Shared")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.im),
                        Color.parseColor(colors[3]))
                        //.selectedIcon(getResources().getDrawable(R.drawable.opp))
                        .title("Channel")
                        .badgeTitle("Follower")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("About")
                        .badgeTitle("Me")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 3);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

}
