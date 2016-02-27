package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class SignUpPeople extends AppCompatActivity {

    private LoginDbPeople dbPeople;
    List<Login> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_people);
        setTitle("People's Team Sign up");

        final EditText email = (EditText)findViewById(R.id.edit_email_up_p);
        final EditText password = (EditText)findViewById(R.id.edit_pass_up_p);
        final EditText con_password = (EditText)findViewById(R.id.edit_pass2_up_p);
        Button signup = (Button)findViewById(R.id.sign_up_p);
        Button signin = (Button)findViewById(R.id.sign_in_p);

        dbPeople = new LoginDbPeople(this);
        try {
            dbPeople.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = dbPeople.getAllDetails();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intentfun(PeopleActivity.class);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type_email = email.getText().toString();
                String type_pass = password.getText().toString();
                String type_co_pass = con_password.getText().toString();

                for (int i = 0; i < list.size() ; i++) {
                    Login login = list.get(i);
                    int j = 0;
                    if(type_email.equals(login.getUser())){

                        Toast.makeText(SignUpPeople.this,"User alredy exist",Toast.LENGTH_SHORT).show();
                    }

                }

                if(!type_email.endsWith("@ignite.world")){
                    Toast.makeText(SignUpPeople.this, "Enter your IgniteWorld mail", Toast.LENGTH_SHORT).show();
                }
                else if(type_pass.length() < 6){
                    Toast.makeText(SignUpPeople.this,"Password should contain atleast 6 letters",Toast.LENGTH_SHORT).show();
                }
                else if(!type_co_pass.equals(type_pass)){
                    Toast.makeText(SignUpPeople.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    dbPeople.createLogin(type_email, type_pass);
                    Intentfun(AfterLoginPeople.class);
                }
            }
        });
    }

    public void Intentfun(Class Activity){
        Intent intent = new Intent(SignUpPeople.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent, bndlanimation);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }
}
