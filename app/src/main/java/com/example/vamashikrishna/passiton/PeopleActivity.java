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

public class PeopleActivity extends AppCompatActivity {

    private LoginDbPeople dbPeople;
    SharedPreferences prefs_p,prefs2_p;
    SharedPreferences.Editor editor_p,editor2_p;
    List<Login> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        setTitle("People's Team");

        final EditText email = (EditText)findViewById(R.id.edit_email_people);
        final EditText password = (EditText)findViewById(R.id.edit_pass_people);
        Button signin = (Button)findViewById(R.id.sign_in_bp);
        final Button show = (Button)findViewById(R.id.show_people);
        Button signup = (Button)findViewById(R.id.sign_up_people);

        prefs_p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs2_p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor_p = prefs_p.edit();
        editor2_p = prefs2_p.edit();
        final String previouslyStoredUser = prefs_p.getString("our_user", "");
        String previouslyStoredPass = prefs2_p.getString("our_pass","");
        email.setText(previouslyStoredUser);
        password.setText(previouslyStoredPass);

        dbPeople = new LoginDbPeople(this);
        try {
            dbPeople.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = dbPeople.getAllDetails();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intentfun(SignUpPeople.class);
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
                editor_p.putString("our_user", type_email);
                editor2_p.putString("our_pass",type_pass);
                editor_p.commit();
                editor2_p.commit();

                for(int i = 1;i < list.size();i++)
                {
                    Login login = list.get(i);
                    if(login.getUser().equals(type_email) && login.getPassword().equals(type_pass))
                    {
                        Intentfun(AfterLoginPeople.class);
                        j++;
                    }
                }
                if(j == 0)
                    Toast.makeText(PeopleActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Intentfun(Class Activity){
        Intent intent = new Intent(PeopleActivity.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent, bndlanimation);
    }


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }
}
