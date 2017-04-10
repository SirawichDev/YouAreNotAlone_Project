package com.Sirawit.Projectx;

import android.content.Intent;
import com.pubnub.api.*;
import org.json.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import static android.R.id.message;
import static com.Sirawit.Projectx.R.id.Phone1;

public class MainActivity extends ActionBarActivity {

    private boolean useMapBox;
    private EditText channelEditText;
    private Switch mSwitch;
    private String channelName;
    private static final String TAG = "Tracker - MainActivity";
    private String tempChar;
    private String Phonenumber;
    private EditText Phone1;
    // ==============================================================================
    // Activity Life Cycle
    // ==============================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSwitch = (Switch) findViewById(R.id.switchMaps);


        // Attach a listener to check for changes in state
        mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    useMapBox = true;
                } else {
                    useMapBox = false;
                }

            }
        });
        int MAX_LENGTH = 30;

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
        final TextView channelEditText = (TextView) findViewById(R.id.channelEditText);
        channelEditText.setText(ss);
        channelEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        channelEditText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            channelName = channelEditText.getText().toString();
                            String message = "Chosen channel: " + channelName;
                            Toast.makeText(MainActivity.this, message,
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, message);
                            return true;
                        default:
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
        final EditText PhoneEditer = (EditText) findViewById(R.id.Phone1);

        PhoneEditer.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            Phonenumber = PhoneEditer.getText().toString();
                            String message1 = "Number is : " + Phonenumber;
                            Toast.makeText(MainActivity.this, message1,
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, message1);
                            return true;
                        default:
                            break;
                    }
                    return true;
                }
                return false;
            }
        });



}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // ==============================================================================
    // Button Actions
    // ==============================================================================

    public void shareLocation(View view) {
        if (useMapBox) {
            Log.d(TAG, "Share Location With MapBox Chosen on channel: "
                    + channelName);
            callActivity(MBoxShareLocationActivity.class);

        } else {
            Log.d(TAG, "Share Location With Google Maps Chosen on channel: "
                    + channelName);
            callActivity(GMapsShareLocationActivity.class);

        }
        return;
    }

    public void followLocation(View view) {
        if (useMapBox) {
            Log.d(TAG, "Follow Location With MapBox Chosen on channel: "
                    + channelName);
            callActivity(MBoxFollowLocationActivity.class);

        } else {
            Log.d(TAG, "Follow Location With Google Maps Chosen on channel: "
                    + channelName);
            callActivity(GMapsFollowLocationActivity.class);

        }
        return;
    }

    private void callActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);

        intent.putExtra("channel", channelName);
        intent.putExtra("Phone", Phonenumber);
        startActivity(intent);
    }

}



