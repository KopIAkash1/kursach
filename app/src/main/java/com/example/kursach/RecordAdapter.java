package com.example.kursach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {
    private Context context;
    private List<Record> recordList;
    public RecordAdapter(@NonNull Context context, @NonNull List<Record> recordList) {
        super(context, 0, recordList);
        this.context = context;
        this.recordList = recordList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recorditem, parent, false);
        }

        Record record = recordList.get(position);

        TextView headerTextView = view.findViewById(R.id.skillsTextView);
        TextView textView = view.findViewById(R.id.headerTextView);

        headerTextView.setText(record.getHeader());

        StringBuilder skillsBuilder = new StringBuilder();
        for (String skill : record.getSkills()) {
            skillsBuilder.append(skill).append(", ");
        }
        // Удаляем последнюю запятую
        if (skillsBuilder.length() > 0) {
            skillsBuilder.delete(skillsBuilder.length() - 2, skillsBuilder.length());
        }
        textView.setText(record.getText());

        return view;
    }
    public void addRecord(Record record) {
        recordList.add(record);
        notifyDataSetChanged();
    }
}
