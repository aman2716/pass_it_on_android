package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class SignUpFinance extends AppCompatActivity {

    private LoginDbFinance dbFinance;
    List<Login> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_finance);
        setTitle("Finance Team Sign up");

        final EditText email = (EditText)findViewById(R.id.edit_email_up_f);
        final EditText password = (EditText)findViewById(R.id.edit_pass_up_f);
        final EditText con_password = (EditText)findViewById(R.id.edit_pass2_up_f);
        Button signup = (Button)findViewById(R.id.sign_up_f);
        Button signin = (Button)findViewById(R.id.sign_in_f);

        dbFinance = new LoginDbFinance(this);
        try {
            dbFinance.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list = dbFinance.getAllDetails();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intentfun(FinanceActivity.class);
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

                        Toast.makeText(SignUpFinance.this,"User alredy exist",Toast.LENGTH_SHORT).show();
                    }

                }

                if(!type_email.endsWith("@ignite.world")){
                    Toast.makeText(SignUpFinance.this, "Enter your IgniteWorld mail", Toast.LENGTH_SHORT).show();
                }
                else if(type_pass.length() < 6){
                    Toast.makeText(SignUpFinance.this,"Password should contain atleast 6 letters",Toast.LENGTH_SHORT).show();
                }
                else if(!type_co_pass.equals(type_pass)){
                    Toast.makeText(SignUpFinance.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    dbFinance.createLogin(type_email, type_pass);
                    Intentfun(AfterLoginFinance.class);
                }
            }
        });
    }

    public void Intentfun(Class Activity){
        Intent intent = new Intent(SignUpFinance.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent, bndlanimation);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }
}
