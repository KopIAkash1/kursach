package com.example.kursach;

import java.util.List;

public class Record {
    private String header;
    private String text;
    private List<String> skills;

    public Record(String header, String text, List<String> skills) {
        this.header = header;
        this.text = text;
        this.skills = skills;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
