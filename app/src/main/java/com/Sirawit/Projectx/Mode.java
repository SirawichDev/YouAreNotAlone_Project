package com.Sirawit.Projectx;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Mode extends ActionBarActivity implements View.OnClickListener {
    String channelName;
    String Phone1;
    @Bind(R.id.tomap)
    Button bon;
    @Bind(R.id.tofap)
    Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        ButterKnife.bind(this);



        final Button con = (Button) findViewById(R.id.tofap);
        con.setOnClickListener(this);
        final Button bon = (Button) findViewById(R.id.tomap);
        bon.setOnClickListener(this);

    }
    public void login() {


        if (!validate()) {
            onLoginFailed();
            return;
        }

        con.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Mode.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Type Phone number");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                                               // onLoginFailed();
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 5000);

    }
    public void login1() {


        if (!validate()) {
            onLoginFailed1();
            return;
        }

        bon.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Mode.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Enter Channel..");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        // onLoginFailed();
                        onLoginSuccess1();
                        progressDialog.dismiss();
                    }
                }, 5000);

    }
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        con.setEnabled(true);

        finish();
    }
    public void onLoginSuccess1() {
        bon.setEnabled(true);

        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        con.setEnabled(true);
    }

    public void onLoginFailed1() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        bon.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        return valid;
    }


    private void callActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);

        intent.putExtra("channel", channelName);
        intent.putExtra("Phone", Phone1);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.tomap:
                login();
                i  = new Intent(Mode.this, Teleinput.class);


                startActivity(i);
                break;
            case R.id.tofap:
                login1();
                i = new Intent(Mode.this, Channelforfollow.class);

                startActivity(i);
                break;
        }

    }
}



