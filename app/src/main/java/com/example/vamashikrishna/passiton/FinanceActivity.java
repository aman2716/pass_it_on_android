package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class FinanceActivity extends AppCompatActivity {

    private LoginDbFinance dbFinance;
    SharedPreferences prefs_f,prefs2_f;
    SharedPreferences.Editor editor_f,editor2_f;
    List<Login> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        setTitle("Finance Team");

        final EditText email = (EditText)findViewById(R.id.edit_email_finance);
        final EditText password = (EditText)findViewById(R.id.edit_pass_finance);
        Button signin = (Button)findViewById(R.id.sign_in_bf);
        final Button show = (Button)findViewById(R.id.show_finance);
        Button signup = (Button)findViewById(R.id.sign_up_finance);

        prefs_f = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs2_f = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor_f = prefs_f.edit();
        editor2_f = prefs2_f.edit();
        final String previouslyStoredUser = prefs_f.getString("our_user", "");
        String previouslyStoredPass = prefs2_f.getString("our_pass","");
        email.setText(previouslyStoredUser);
        password.setText(previouslyStoredPass);

        dbFinance = new LoginDbFinance(this);
        try {
            dbFinance.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = dbFinance.getAllDetails();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intentfun(SignUpFinance.class);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show.getText().equals("Show")) {
                    show.setText(R.string.hide);
                    password.setTransformationMethod(null);
                } else {
                    show.setText(R.string.show);
                    password.setTransformationMethod(new PasswordTransformationMethod());

                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int j = 0;
                String type_email = email.getText().toString();
                String type_pass = password.getText().toString();
                editor_f.putString("our_user", type_email);
                editor2_f.putString("our_pass",type_pass);
                editor_f.commit();
                editor2_f.commit();

                for(int i = 1;i < list.size();i++)
                {
                    Login login = list.get(i);
                    if(login.getUser().equals(type_email) && login.getPassword().equals(type_pass))
                    {
                       Intentfun(AfterLoginFinance.class);
                        j++;
                    }
                }
                if(j == 0)
                    Toast.makeText(FinanceActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Intentfun(Class Activity){
        Intent intent = new Intent(FinanceActivity.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent, bndlanimation);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }
}
