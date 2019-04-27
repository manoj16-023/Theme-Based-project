package com.example.manojkumar.collegeguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    private EditText reset_email;
    public String send_email;
    private Button reset;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        reset_email=(EditText)findViewById(R.id.et_Email);
        reset = (Button) findViewById(R.id.btn_send);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_email=reset_email.getText().toString().trim();
                if(send_email.equals("")){
                    Toast.makeText(forgot_password.this,"Please enter your Email",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(send_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(forgot_password.this, "Password reset Email sent!", Toast.LENGTH_SHORT).show();
                                finish();;
                                startActivity(new Intent(forgot_password.this,LoginActivity.class));
                            }else{
                                Toast.makeText(forgot_password.this,"Error in sending Password reset",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
