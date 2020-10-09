package com.example.safeex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText title_input, email_input, username_input, password_input;
    Button add_button;

    //    static String ti,em,user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        email_input = findViewById(R.id.email_input);
        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//          ti = title_input.getText().toString().trim();
//            em= email_input.getText().toString().trim();
//            user=username_input.getText().toString().trim();
//            pass=password_input.getText().toString().trim();
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addPass(title_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        username_input.getText().toString().trim(),
                        password_input.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, MainActivity2.class);
                AddActivity.this.startActivity(intent);
            }
        });


    }

}