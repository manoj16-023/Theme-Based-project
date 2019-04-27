package com.example.manojkumar.collegeguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity {
    private EditText name;
    private EditText Email;
    private EditText password_reg;
    private EditText phone_number;
    private CheckBox sports;
    private CheckBox academics;
    private CheckBox placements;
    private CheckBox service;
    private Button register;
    private TextView login_redirect;
   // private ProgressDialog progressDialog;
   // private static final String TAG="REgister Example";
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myref;
    private String E_mail,Name,Number,Password;
    private String Sports,Placements,Services,Academics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        firebaseAuth = FirebaseAuth.getInstance();


      //  progressDialog = new ProgressDialog(this);
        name=(EditText)findViewById(R.id.reg_name);
        Email=(EditText)findViewById(R.id.reg_email);
        password_reg=(EditText)findViewById(R.id.reg_password);
        phone_number=(EditText)findViewById(R.id.reg_phone);
        sports=(CheckBox)findViewById(R.id.reg_sports);
        academics=(CheckBox)findViewById(R.id.reg_acdemic);
        placements=(CheckBox)findViewById(R.id.reg_placements);
        service=(CheckBox)findViewById(R.id.reg_service);
        register=(Button)findViewById(R.id.reg_button);
        login_redirect=(TextView)findViewById(R.id.login_direct);
//        try {
//            //
//        } catch (Exception e) {
//            Log.e(TAG, "Received an exception register " + e.getMessage() );
//        }
       // final int rEgister_activity = Log.e(TAG, "REgister activity");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user();
                checkEmailVerification();
            }
        });
        login_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_activity.this,LoginActivity.class));
            }
        });
    }


    private void register_user() {
         E_mail= Email.getText().toString().trim();
         Name = name.getText().toString().trim();
         Password =password_reg.getText().toString().trim();
         Number =phone_number.getText().toString().trim();
         if(sports.isChecked()){
             Sports="True";
         }else{
             Sports="False";
         }
         if(academics.isChecked()){
             Academics="True";
         }else{
             Academics="False";
         }
         if(placements.isChecked()){
             Placements="True";
         }else{
             Placements="False";
         }
         if(service.isChecked()){
            Services="True";
        }else{
             Services="False";
         }

        if(TextUtils.isEmpty(E_mail)){
            Toast.makeText(this,"Please Enter Mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Name)){
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Number)){
            Toast.makeText(this,"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }
        // progressDialog.setMessage("Registering User...");
        // progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(E_mail,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            checkEmailVerification();
                        }
                        else{
                            Toast.makeText(Register_activity.this,"Could not Register ..Please Try Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void checkEmailVerification(){
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();


        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()){
                       sendUserData();
                       Toast.makeText(Register_activity.this,"Successfully Registeted ,Verfication Email sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                       startActivity(new Intent(Register_activity.this,LoginActivity.class));
                   }
                   else{
                       Toast.makeText(Register_activity.this,"Registration unsuccessfull",Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
        else{
            Toast.makeText(Register_activity.this, "Verify your Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    private void sendUserData(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        myref= firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile= new UserProfile(E_mail,Password,Name,Number,Sports,Academics,Services,Placements);
        myref.setValue(userProfile);

    }
}
