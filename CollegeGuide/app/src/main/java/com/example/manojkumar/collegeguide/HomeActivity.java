package com.example.manojkumar.collegeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    //private static final String TAG="Logging Example";
    private Button Admin_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        Admin_button = (Button)findViewById(R.id.ad_button);
        Admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(HomeActivity.this,Admin.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                break;
            }
            case R.id.profile:{
                finish();
                startActivity(new Intent(HomeActivity.this,Profile_activity.class));
                break;
                //you need to profile activity
            }
            case R.id.Help:{
                finish();
                startActivity(new Intent(HomeActivity.this,help.class));
                break;
                //You need to write help
            }
            case R.id.reset:{
                finish();
                startActivity(new Intent(HomeActivity.this,forgot_password.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
