package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ListView listView;
    Button button;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Record newRecord = new Record("Заголовок", "Текст записи", new ArrayList<String>());
        Record newRecord2 = new Record("Заголовок2", "Текст записи2", new ArrayList<String>());
        List<Record> recordList = new ArrayList<Record>();
        recordList.add(newRecord);
        recordList.add(newRecord2);
        listView = findViewById(R.id.listView);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logoutButton);
        textView = findViewById(R.id.lentaText);
        user = auth.getCurrentUser();

        RecordAdapter recordAdapter = new RecordAdapter(this,recordList);
        listView.setAdapter(recordAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}