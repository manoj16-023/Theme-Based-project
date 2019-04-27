package com.example.manojkumar.collegeguide;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity{
    private EditText eMail;
    private EditText password;
    private Button Login;
    private TextView newUserSignUp;
    private TextView attempts;
    private FirebaseAuth firebaseAuth;
    private int count=5;
    private TextView forgot;
    //private static final String TAG="Logging Example";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Log.e(TAG,"Manoj kumar");

        eMail = (EditText)findViewById(R.id.login_email);
        password=(EditText)findViewById(R.id.login_password);
        newUserSignUp=(TextView)findViewById(R.id.Sign_up);
        Login=(Button)findViewById(R.id.login_button);
        attempts=(TextView)findViewById(R.id.textView2);
        forgot=(TextView)findViewById(R.id.textView3);
        forgot.setText("");
        firebaseAuth=FirebaseAuth.getInstance();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,forgot_password.class));
            }
        });
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }
        newUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register_activity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {
        String E_Mail=eMail.getText().toString().trim();
        String Password=password.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(E_Mail,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                   }
                   else{
                       forgot.setText("Forgot Password");
                       count--;
                       if(count==0){
                           Login.setEnabled(false);
                       }
                       attempts.setText("No of Attempts remaining:"+count);
                       Toast.makeText(LoginActivity.this,"Login is Unsuccessfull",Toast.LENGTH_SHORT).show();
                   }

            }
        });
    }
}
