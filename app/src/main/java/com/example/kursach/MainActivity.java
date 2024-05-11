package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ListView listView;
    Button logoutButton, profileButton, addButton, deleteButton;
    DatabaseReference groupRef = FirebaseDatabase.getInstance().getReference("RecordMass").child("Records");
    TextView textView;
    LinearLayout adminTools;
    FirebaseUser user;
    public List<Record> recordList = new ArrayList<>();
    public void UpdateUI(List<Record> rl){
        RecordAdapter recordAdapter = new RecordAdapter(getApplicationContext(),rl);
        listView.setAdapter(recordAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot recordSnapshot : snapshot.getChildren()){
                    String re = recordSnapshot.getValue().toString();
                    Log.wtf(re,re);
                    String[] words = re.split(" ");
                    String header = re.split("header=")[1].split(",")[0];
                    Log.wtf(header,header);
                    String text = re.split("text=")[1].split("\\}")[0];
                    Log.wtf(text,text);
                    String buff = re.split("skills=")[1];
                    String[] skill = new String[' '];
                    try{
                        skill = buff.split(",");
                    } catch (Exception e){
                        skill[0] = buff;
                    }
                    Log.wtf(skill[0],skill[0]);
                    ArrayList<String> skillsMass = new ArrayList<>(Arrays.asList(skill));
                    Record record = new Record(header,text,skillsMass);
                    recordList.add(record);
                }
                UpdateUI(recordList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordDelete.class);
                startActivity(intent);
                finish();
            }
        });
    }
}