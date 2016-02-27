package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private LoginDbEmployee dbEmployee;
    SharedPreferences prefs,prefs2;
    SharedPreferences.Editor editor,editor2;
    List<Login> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        setTitle("Employee");

        final EditText email = (EditText)findViewById(R.id.edit_email);
        final EditText password = (EditText)findViewById(R.id.edit_pass);
        Button signin = (Button)findViewById(R.id.sign_in_b);
        final Button show = (Button)findViewById(R.id.show);
        Button signup = (Button)findViewById(R.id.sign_up);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
        editor2 = prefs2.edit();
        final String previouslyStoredUser = prefs.getString("our_user", "");
        String previouslyStoredPass = prefs2.getString("our_pass","");
        email.setText(previouslyStoredUser);
        password.setText(previouslyStoredPass);


        dbEmployee = new LoginDbEmployee(this);
        try {
            dbEmployee.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = dbEmployee.getAllDetails();

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
                editor.putString("our_user", type_email);
                editor2.putString("our_pass",type_pass);
                editor.commit();
                editor2.commit();

                for(int i = 0;i < list.size();i++)
                {
                    Login login = list.get(i);
                    if(login.getUser().equals(type_email) && login.getPassword().equals(type_pass))
                    {
                        Intentfun(AfterLoginEmployee.class);
                        j++;
                    }
                }
                if(j == 0)
                    Toast.makeText(EmployeeActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intentfun(SignUpEmployee.class);
            }
        });
    }

    public void Intentfun(Class Activity){
        Intent intent = new Intent(EmployeeActivity.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent,bndlanimation);
    }


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }
}
