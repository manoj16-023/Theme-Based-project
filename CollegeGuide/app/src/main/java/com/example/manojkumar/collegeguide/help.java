package com.example.manojkumar.collegeguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class help extends AppCompatActivity {
    private TextView redirect_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        redirect_to=findViewById(R.id.textView22);
    }
}
