package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LaunchingActivity extends AppCompatActivity {
    private ListView types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        types = (ListView)findViewById(R.id.types);
        displayTypes();
    }

    private void displayTypes() {

        ArrayList<Typo> typeList = new ArrayList<Typo>();
        Typo country = new Typo("an Employee here",false);
        typeList.add(country);
        country = new Typo("in People's Team",false);
        typeList.add(country);
        country = new Typo("in Finance Team",false);
        typeList.add(country);
        MyCustomAdapter customAdapter = new MyCustomAdapter(LaunchingActivity.this,R.layout.type_info,typeList);
        types.setAdapter(customAdapter);

    }
    public class MyCustomAdapter extends ArrayAdapter<Typo> {

        private ArrayList<Typo> typelist;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Typo> typelist) {
            super(context, textViewResourceId, typelist);
            this.typelist = new ArrayList<Typo>();
            this.typelist.addAll(typelist);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.type_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.type);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                final ViewHolder finalHolder = holder;
                final ViewHolder finalHolder1 = holder;
                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Typo typo = (Typo) cb.getTag();

                        if(finalHolder.name.getText().equals("an Employee here"))
                        {
                            Intentfun(EmployeeActivity.class);
                            finalHolder1.name.setChecked(false);
                        }
                        else if(finalHolder.name.getText().equals("in People's Team")){
                            Intentfun(PeopleActivity.class);
                            finalHolder1.name.setChecked(false);
                        }
                        else {
                            Intentfun(FinanceActivity.class);
                            finalHolder1.name.setChecked(false);
                        }
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Typo typo = typelist.get(position);
            holder.name.setText(typo.getName());
            holder.name.setChecked(typo.isSelected());
            holder.name.setTag(typo);

            return convertView;

        }
    }
    public void Intentfun(Class Activity) {
        Intent intent = new Intent(LaunchingActivity.this,Activity);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent,bndlanimation);
    }
}
