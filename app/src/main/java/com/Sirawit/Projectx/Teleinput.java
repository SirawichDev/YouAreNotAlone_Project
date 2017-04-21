package com.Sirawit.Projectx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.Bind;
import butterknife.ButterKnife;

public class Teleinput extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nvDrawer;
    private static final String TAG = "TelePhone Input";
    private static final int REQUEST_SIGNUP = 0;
    private String passwords;
    @Bind(R.id.enter)
    EditText _passwordText;
    @Bind(R.id.enterphone)
    Button _loginButton;
    @Bind(R.id.circularButton1)
    Button _Cir;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleinput);
        ButterKnife.bind(this);

        _Cir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginx();
            }
        });
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });



    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

         final ProgressDialog progressDialogs = new ProgressDialog(Teleinput.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialogs.setIndeterminate(true);
        progressDialogs.setMessage("Generating Channel...");
        progressDialogs.show();

         passwords = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialogs.dismiss();
                    }
                }, 3000);
    }
    public void loginx() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailedx();
            return;
        }

        _Cir.setEnabled(false);

         final ProgressDialog progressDialogxx = new ProgressDialog(Teleinput.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialogxx.setIndeterminate(true);
        progressDialogxx.setMessage("Checking Contract List..");
        progressDialogxx.show();


        passwords = _passwordText.getText().toString();
        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        getcontractlist();
                        // onLoginFailed();
                        progressDialogxx.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }
    public void getcontractlist(){
        _Cir.setEnabled(true);
        Intent newAc = new Intent(Teleinput.this,CiontractList.class);
        newAc.putExtra("Phonex",passwords);
        startActivity(newAc);
        finish();
    }



    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent newActivity = new Intent(Teleinput.this,Channel.class);
        newActivity.putExtra("Phone",passwords);
        startActivity(newActivity);

        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);

    }
    public void onLoginFailedx() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();


        _Cir.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        return valid;
    }



}


