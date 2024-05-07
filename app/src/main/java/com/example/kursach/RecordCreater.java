package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class RecordCreater extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference groupRef = database.getReference("groups").child("Records");
    TextInputEditText headerRecord, textRecord, skillsRecord;
    Button backButton, addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        headerRecord = findViewById(R.id.headerRecord);
        textRecord = findViewById(R.id.textRecord);
        skillsRecord = findViewById(R.id.skillsRecord);

        backButton = findViewById(R.id.backButton);
        addButton = findViewById(R.id.addButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String header, text, skills;
                header = headerRecord.getText().toString();
                text = textRecord.getText().toString();
                skills = skillsRecord.getText().toString();
                ArrayList<String> skillsMass = new ArrayList<>(Arrays.asList(skills.split(",")));
                Record record = new Record(header,text,skillsMass);
                groupRef.setValue(record);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
