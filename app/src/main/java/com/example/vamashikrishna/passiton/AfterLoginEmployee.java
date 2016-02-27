package com.example.vamashikrishna.passiton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AfterLoginEmployee extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Employee");

        final EditText emp_name = (EditText)findViewById(R.id.emp_name);
        final EditText emp_desig = (EditText)findViewById(R.id.emp_desig);
        final EditText emp_dep = (EditText)findViewById(R.id.emp_dep);
        final EditText emp_sal = (EditText)findViewById(R.id.emp_sal);
        Button next = (Button)findViewById(R.id.enter);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emp_name_user = emp_name.getText().toString();
                String emp_desig_user = emp_desig.getText().toString();
                String emp_dep_user = emp_dep.getText().toString();
                String emp_sal_user = emp_sal.getText().toString();

                if(emp_name_user.length() == 0 || emp_dep_user.length() == 0 ||
                        emp_desig_user.length() == 0 || emp_sal_user.length() == 0){
                    Toast.makeText(AfterLoginEmployee.this,"Credentials can't e empty",Toast.LENGTH_SHORT).show();
                }
                else
                    Intentfun(Forms.class,emp_name_user,emp_sal_user);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                // Ensure that there's a camera activity to handle the intent
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    // Create the File where the photo should go
//                    File photoFile = null;
//                    try {
//                        photoFile = createImageFile();
//                    } catch (IOException ex) {
//                    }
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                                Uri.fromFile(photoFile));
//                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                    }
//                }
//            }
//        });
    }

//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        Log.d("image storage", mCurrentPhotoPath);
//        return image;
//    }

    public void Intentfun(Class Activity,String name,String salary) {
        Intent intent = new Intent(AfterLoginEmployee.this,Activity);
        intent.putExtra("name",name);
        intent.putExtra("salary",salary);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left_go2, R.anim.enter_right2).toBundle();
        startActivity(intent, bndlanimation);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
    }

}
