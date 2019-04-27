package com.example.manojkumar.collegeguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_notification extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_SEND_SMS=1;
    private RadioButton admin_sports;
    private RadioButton admin_placements;
    private RadioButton admin_academics;
    private RadioButton admin_service;
    private EditText message;
    private Button send;
    public String sports;
    public String academics;
    public String placements;
    public String services;
    public String status;
    public String Content;
    public String Phone_no;
    private TextView printer;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);
        admin_sports=(RadioButton)findViewById(R.id.ad_sports);
        admin_academics=(RadioButton)findViewById(R.id.ad_academics);
        admin_service=(RadioButton)findViewById(R.id.ad_services);
        admin_placements=(RadioButton)findViewById(R.id.ad_placements);
        printer=(TextView)findViewById(R.id.print);
        message=(EditText)findViewById(R.id.ad_message);
        send=(Button)findViewById(R.id.ad_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checking();
            }
        });

    }
    private void checking(){
        if(admin_sports.isChecked()){
            sports="True";
        }else{
            sports="False";
        }
        if(admin_academics.isChecked()){
            academics="True";
        }else{
            academics="False";
        }
        if(admin_placements.isChecked()) {
            placements = "True";
        }else{
            placements="False";
        }
        if(admin_service.isChecked()){
            services="True";
        }else{
            services="False";
        }
        sendNotification();


    }

    private void sendNotification() {
        Content=message.getText().toString().trim();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(Admin_notification.this, "YOU Just came to retrieve section", Toast.LENGTH_SHORT).show();
                if(sports.equals("True")){
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        UserProfile retrieve= user.getValue(UserProfile.class);
                        assert retrieve != null;
                        status = retrieve.getUserSports();
                        Phone_no = retrieve.getUserPhone();
                        //System.out.println(status);
                        //System.out.println(Phone_no);
                        if(status.equals("True")) {
                          //  System.out.println("Hai hgcgvk..............");
                            sendSMSMMessage(Phone_no, "From Sports:" + Content);
                            Toast.makeText(Admin_notification.this, "Retrieve Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else if(academics.equals("True")){
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        UserProfile retrieve= user.getValue(UserProfile.class);
                        assert retrieve != null;
                        status = retrieve.getUserAcademics();
                        Phone_no = retrieve.getUserPhone();
                        if(status.equals("True")) {
                            sendSMSMMessage(Phone_no, "From Academics:" + Content);
                            Toast.makeText(Admin_notification.this, "Retrieve Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else if(services.equals("True")){
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        UserProfile retrieve= user.getValue(UserProfile.class);
                        assert retrieve != null;
                        status = retrieve.getUserService();
                        Phone_no = retrieve.getUserPhone();
                        if(status.equals("True")) {
                            sendSMSMMessage(Phone_no, "From Social Services:" + Content);
                            Toast.makeText(Admin_notification.this, "Retrieve Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else if(placements.equals("True")){
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        UserProfile retrieve= user.getValue(UserProfile.class);
                        assert retrieve != null;
                        status = retrieve.getUserPlacements();
                        Phone_no = retrieve.getUserPhone();
                        if(status.equals("True") ){
                            sendSMSMMessage(Phone_no, "From Placements:" + Content);
                            Toast.makeText(Admin_notification.this, "Retrieve Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin_notification.this, "Unable to retrieve details", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void sendSMSMMessage(String number,String message) {


        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSION_REQUEST_SEND_SMS);
            } else {
                String dial = "smsto:" + number;
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message, null, null);
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Error in sending message", Toast.LENGTH_SHORT).show();
        }
    }
}
