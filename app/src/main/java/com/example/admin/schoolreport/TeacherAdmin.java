package com.example.admin.schoolreport;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherAdmin extends AppCompatActivity {

    DatabaseHandler studentDB;

    Button btnAddData,btnViewData,btnUpdate,btnDelete;
    EditText Name, Identity, Grade, Date, English, Maths, LifeOrientation, Economics, NaturalScience, History, Geography, Status, StudentNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_admin);


        studentDB = new DatabaseHandler(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String username = getIntent().getStringExtra("Username");

        Name = (EditText)findViewById(R.id.editText_name);
        Identity = (EditText)findViewById(R.id.editText_identity);
        Grade = (EditText)findViewById(R.id.editText_grade);
        Date = (EditText)findViewById(R.id.editText_date);
        English = (EditText)findViewById(R.id.editText_english);
        Maths = (EditText)findViewById(R.id.editText_maths);
        LifeOrientation = (EditText)findViewById(R.id.editText_lifeOrientation);
        NaturalScience = (EditText)findViewById(R.id.editText_naturalScience);
        History = (EditText)findViewById(R.id.editText_History);
        Geography = (EditText)findViewById(R.id.editText_Geography);
         Economics= (EditText)findViewById(R.id.editText_Economics);
        Status = (EditText)findViewById(R.id.editText__status);
        StudentNumber = (EditText)findViewById(R.id.editText__student_number);
        btnAddData = (Button)findViewById(R.id.btnAdd);
        btnViewData = (Button)findViewById(R.id.btn_ViewData);
        btnUpdate = (Button)findViewById(R.id.btn_Update);
        btnDelete = (Button)findViewById(R.id.btn_Delete);

        AddData();
        ViewData();
        UpdateData();
        deleteData();
    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String identity = Identity.getText().toString();
                String grade = Grade.getText().toString();
                String date = Date.getText().toString();
                String subject1 = English.getText().toString();
                String subject2 = Maths.getText().toString();
                String subject3 = LifeOrientation.getText().toString();
                String subject4 = Economics.getText().toString();
                String subject5 = NaturalScience.getText().toString();
                String subject6 = History.getText().toString();
                String subject7 = Geography.getText().toString();
                String status = Status.getText().toString();

                boolean insertData = studentDB.addData(name,identity,grade,date,subject1,subject2,subject3,subject4,subject5,subject6,subject7,status);

                if (!insertData == true){
                    Toast.makeText(TeacherAdmin.this, "Data successfully Added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TeacherAdmin.this, "Something went wrong,Try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void ViewData(){

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data =  studentDB.showData();

                if (data.getCount() == 0){

                    display("Error"," No data Found");
                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while(data.moveToNext()){
                    buffer.append("Student Number: " + data.getString(0) + "\n");
                    buffer.append("Name and Surname: " + data.getString(1) + "\n");
                    buffer.append("Identity: " + data.getString(2) + "\n");
                    buffer.append("Grade: " + data.getString(3) + "\n");
                    buffer.append("Date: " + data.getString(4) + "\n");
                    buffer.append("" + data.getString(5) + "\n");
                    buffer.append("" + data.getString(6) + "\n");
                    buffer.append("" + data.getString(7) + "\n");
                    buffer.append("" + data.getString(8) + "\n");
                    buffer.append("" + data.getString(9) + "\n");
                    buffer.append("" + data.getString(10) + "\n");
                    buffer.append("" + data.getString(11) + "\n");
                    buffer.append("Status:" + data.getString(12) + "\n");

                    display("Student Report:", buffer.toString());
                }
            }

        });
    }

    public  void  display(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void UpdateData(){

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = StudentNumber.getText().toString().length();

                if (temp > 0){

                    boolean update = studentDB.updateData(StudentNumber.getText().toString(), Name.getText().toString(), Identity.getText().toString(), Date.getText().toString(), Grade.getText().toString(), English.getText().toString(), Maths.getText().toString(), LifeOrientation.getText().toString(), Economics.getText().toString(), NaturalScience.getText().toString(), History.getText().toString(), Geography.getText().toString(), Status.getText().toString());
                    if (!update == true){
                        Toast.makeText(TeacherAdmin.this, "Data successfully updated", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(TeacherAdmin.this, "You must enter a Student Number to update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = StudentNumber.getText().toString().length();
                if (temp > 0){
                    Integer deleteRow = studentDB.deleteData(StudentNumber.getText().toString());
                    if (deleteRow > 0){

                        Toast.makeText(TeacherAdmin.this, "Data succesfully deleted", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(TeacherAdmin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TeacherAdmin.this, "Enter student number to delete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}