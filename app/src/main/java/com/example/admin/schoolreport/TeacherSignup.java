package com.example.admin.schoolreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherSignup extends AppCompatActivity {
    public Button signUp,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final DatabaseHelper helper = new DatabaseHelper(this);

        login=(Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherSignup.this,TeacherLogin.class);
                startActivity(i);
            }
        });

        //onClicklistener when the signUp button is clicked
        signUp = (Button)findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.signUpBtn)
                {
                    //finding the components by ID
                    EditText name = (EditText)findViewById(R.id.TFName);
                    EditText email = (EditText)findViewById(R.id.TFEmail);
                    EditText username = (EditText)findViewById(R.id.TFUsername);
                    EditText password = (EditText)findViewById(R.id.TFPassword);
                    EditText confirm = (EditText)findViewById(R.id.TFConfirm);

                    String namestr = name.getText().toString();
                    String emailstr = email.getText().toString();
                    String usernamestr = username.getText().toString();
                    String passwordstr = password.getText().toString();
                    String confirmstr = confirm.getText().toString();

                    if (!passwordstr.equals(confirmstr))
                    {
                        //popup message when you enter the incorrect passwords
                        Toast.makeText(TeacherSignup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    if (passwordstr.equals(confirmstr))
                    {
                        Intent i = new Intent(TeacherSignup.this, TeacherLogin.class);
                        startActivity(i);
                        //pop up message when the registration is successful(Toast)
                        Toast.makeText(TeacherSignup.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                    }else{
                        //Insert the details on database
                        Contact c = new Contact();
                        c.setName(namestr);
                        c.setEmail(emailstr);
                        c.setUsername(usernamestr);
                        c.setPassword(passwordstr);

                        helper.insertContact(c);
                    }

                }
            }
        });



    }
}
