package com.example.safeex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input,email_input,username_input,password_input;
    Button update_button, delete_button;

    String id,title,email,username,password;
   // String id1,title1,email1,username1,password1;

   // String ti, e , user , pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        email_input = findViewById(R.id.email_input2);
        username_input = findViewById(R.id.username_input2);
        password_input = findViewById(R.id.password_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        // First we call this
        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
        {
            id = null;
            title = null;
            email = null;
            username = null;
            password = null;
        }
        else
        {
            id = bundle.getString("id");
            title = bundle.getString("title");
            title_input.setText(title);
            email= bundle.getString("email");
            email_input.setText(email);
            username = bundle.getString("username");
            username_input.setText(username);
            password = bundle.getString("password");
            password_input.setText(password );
        }

        getAndSetIntentData();


//        if(AddActivity.ti != null && AddActivity.em != null && AddActivity.user != null && AddActivity.pass != null ){
//            ti=AddActivity.ti;
//            e=AddActivity.em;
//            user=AddActivity.user;
//            pass=AddActivity.pass;
//            title_input.setText(ti);
//            email_input.setText(e);
//            username_input.setText(user);
//            password_input.setText(pass);
//
//        }

        // Set Actionbar title and after GetAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                username = username_input.getText().toString().trim();
                password = password_input.getText().toString().trim();
                myDB.updateData(id,title,email,username,password);
                startActivity(new Intent(UpdateActivity.this , MainActivity2.class) );
            }
        });

       // delete_button.setOnClickListener(view -> confirmDialog());
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
         if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("email") &&
                 getIntent().hasExtra("username") && getIntent().hasExtra("password") ){
             // Getting Data from Intent
             id = getIntent().getStringExtra("id");
             title = getIntent().getStringExtra("title");
             email = getIntent().getStringExtra("email");
             username = getIntent().getStringExtra("username");
             password = getIntent().getStringExtra("password");

             // Setting Intent Data
             title_input.setText(title);
             email_input.setText(email);
             username_input.setText(username);
             password_input.setText(password);

         }else{
         //    Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
         }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + title + "?");
        builder.setMessage("Are you sure want to delete " + title + "?" );
        builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOnRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}