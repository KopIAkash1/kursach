package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    Button logoutButton, profileButton, addButton, deleteButton;
    TextView textView;
    LinearLayout adminTools;
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
        logoutButton = findViewById(R.id.logoutButton); // +
        profileButton = findViewById(R.id.profileButton); // -
        addButton = findViewById(R.id.addRecord); //
        deleteButton = findViewById(R.id.delRecord); // -
        textView = findViewById(R.id.lentaText);
        adminTools = findViewById(R.id.adminTools);
        adminTools.setVisibility(View.GONE);
        user = auth.getCurrentUser();
        if(user.getEmail().equals("rossia1234556@yandex.ru")){
            adminTools.setVisibility(View.VISIBLE);
        }
        RecordAdapter recordAdapter = new RecordAdapter(this,recordList);
        listView.setAdapter(recordAdapter);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordCreater.class);
                startActivity(intent);
                finish();
            }
        });
    }
}