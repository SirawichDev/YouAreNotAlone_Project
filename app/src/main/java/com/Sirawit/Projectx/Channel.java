package com.Sirawit.Projectx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class Channel extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "Tracker - Channel";
    private String tempChar;

    String Phone1;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    @Bind(R.id.enterchannel)
    Button tt;
     String channelName;

    int MAX_LENGTH = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        Phone1 = intent.getStringExtra("Phone");
        initView();


        tt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


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
            Log.e(TAG, "AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(tempChar.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "AES encryption error");
        }

        String ss = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
        ss = ss.substring(0, 5);
         TextView channelEditText = (TextView) findViewById(R.id.Chammelen);
        channelEditText.setText(ss);
        channelEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

                            channelName = channelEditText.getText().toString();

    }
    private void initView() {
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        mWaveSwipeRefreshLayout.setOnRefreshListener(this);
        mWaveSwipeRefreshLayout.setWaveColor(Color.rgb(52, 152, 219));

    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        tt.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Channel.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Let Go...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }



    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        tt.setEnabled(true);
        Intent intent = new Intent(Channel.this, GMapsShareLocationActivity.class);
        intent.putExtra("channel",channelName);
        intent.putExtra("Phone",Phone1);
        startActivity(intent);;
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        tt.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        return valid;
    }


@Override
    public void onRefresh() {
        {refresh();
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
                Log.e(TAG, "AES secret key spec error");
            }

            // Encode the original data with AES
            byte[] encodedBytes = null;
            try {
                Cipher c = Cipher.getInstance("AES");
                c.init(Cipher.ENCRYPT_MODE, sks);
                encodedBytes = c.doFinal(tempChar.getBytes());
            } catch (Exception e) {
                Log.e(TAG, "AES encryption error");
            }

            String ss = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
            ss = ss.substring(0, 5);
            final TextView channelEditText = (TextView) findViewById(R.id.Chammelen);
            channelEditText.setText(ss);
            channelEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

                                channelName = channelEditText.getText().toString();

        }

        }


    private void refresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新が終了したらインジケータ非表示

                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
        },5000);
    }
    protected void onResume() {
        //mWaveSwipeRefreshLayout.setRefreshing(true);
        refresh();
        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mWaveSwipeRefreshLayout.setRefreshing(true);
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
