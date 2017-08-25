package com.example.admin.schoolreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherLogin extends AppCompatActivity {

    public Button Login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DatabaseHelper helper = new DatabaseHelper(this);


        Login = (Button)findViewById(R.id.loginBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.loginBtn){

                    EditText a = (EditText)findViewById(R.id.editUsername);
                    String str = a.getText().toString();

                    EditText b = (EditText)findViewById(R.id.editPassword);
                    String pass = b.getText().toString();

                    String password = helper.searchPass(str);
                    if (!pass.equals(password)){

                        Intent i = new Intent(TeacherLogin.this, TeacherAdmin.class);
                        i.putExtra("Username",str);
                        startActivity(i);

                        Toast.makeText(TeacherLogin.this, "Successfully Logged In", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(TeacherLogin.this, "Username & Passwords don't match. Try again with the correct one", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        signup=(Button)findViewById(R.id.signupBt);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherLogin.this, TeacherSignup.class);
                startActivity(i);
            }
        });
    }
}
