package com.Sirawit.Projectx;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import devlight.io.library.ntb.NavigationTabBar;



public class Navchannel extends AppCompatActivity  {
    private String tempChar;
   public String Phone1;
    String password;
    int MAX_LENGTH = 30;
    String channelName;
    public String text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navchannel);
        Intent intent = getIntent();
         Phone1 = intent.getStringExtra("Phone");
        initUI();


    }

    public void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
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
                if(position == 0 ){
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_mode, null, false);
                    container.addView(view);
                    return view;
                }
                else if(position == 1){
                    final  View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_teleinput, null, false);
                    final  EditText get = (EditText) view.findViewById(R.id.enter);
                    final Button but = (Button) view.findViewById((R.id.enterphone));

                    but.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            String passwords = get.getText().toString();
                            Intent intent = new Intent(Navchannel.this, Navchannel.class);
                            intent.putExtra("Phone",passwords);

                            startActivity(intent);
                        }

                    });
                    container.addView(view);
                    return view;

                }

                else {
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.activity_channel, null, false);

                     final EditText txtPage = (EditText) view.findViewById(R.id.Chammelen);
                    Random generator = new Random();
                    StringBuilder randomStringBuilder = new StringBuilder();
                    int randomLength = generator.nextInt(MAX_LENGTH);
                    for (int i = 0; i < randomLength; i++) {
                        tempChar = String.valueOf((char) (generator.nextInt(96) + 32));
                        randomStringBuilder.append(tempChar);
                    }
                    // Original text

                    // Set up secret key spec for 128-bit AES encryption and decryption
                    SecretKeySpec sks = null;
                    try {
                        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                        sr.setSeed("any data used as random seed".getBytes());
                        KeyGenerator kg = KeyGenerator.getInstance("AES");
                        kg.init(128, sr);
                        sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
                    } catch (Exception e) {

                    }

                    // Encode the original data with AES
                    byte[] encodedBytes = null;
                    try {
                        Cipher c = Cipher.getInstance("AES");
                        c.init(Cipher.ENCRYPT_MODE, sks);
                        encodedBytes = c.doFinal(tempChar.getBytes());
                    } catch (Exception e) {

                    }

                    String ss = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
                    ss = ss.substring(0, 5);
                    text = ss;
                    txtPage.setText(ss);

                    final Button setChannel = (Button) view.findViewById(R.id.enterchannel);
                    setChannel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            String chanelset = txtPage.getText().toString();
                            Intent intent = new Intent(Navchannel.this, GMapsShareLocationActivity.class);
                            intent.putExtra("Phone",Phone1);
                            intent.putExtra("channel",text);
                            startActivity(intent);
                        }
                    });

                    container.addView(view);
                    return view;
                }
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
        navigationTabBar.setViewPager(viewPager, 2);
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

    public void getlist(View view){
        Intent intent = new Intent(Navchannel.this, CiontractList.class);
        intent.putExtra("Phonex",password);
        startActivity(intent);
    }

}
