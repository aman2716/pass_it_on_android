package com.example.vamashikrishna.passiton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AfterLoginFinance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_finance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Finance Team");


    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }

}
