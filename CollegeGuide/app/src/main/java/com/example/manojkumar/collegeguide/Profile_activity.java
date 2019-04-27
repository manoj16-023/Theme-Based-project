package com.example.manojkumar.collegeguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Profile_activity extends AppCompatActivity {
    private TextView profile_na;
    private TextView profile_no;

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference reference;
    public FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        profile_na=(TextView)findViewById(R.id.profile_name);
        profile_no=(TextView)findViewById(R.id.profile_phone);
        Button back=(Button)findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Profile_activity.this,LoginActivity.class));
            }
        });
        Button show = (Button) findViewById(R.id.profile_show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase=FirebaseDatabase.getInstance();
                reference= firebaseDatabase.getReference((firebaseAuth.getUid()));
                reference.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                        assert userProfile != null;
                        profile_na.setText("Name:"+userProfile.getUserName());
                        profile_no.setText("Phone:"+userProfile.getUserPhone());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Profile_activity.this,"Unable to retrieve",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
