package com.example.manojkumar.collegeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    private EditText Admin_name;
    private EditText Admin_pass;
    private Button admin_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Admin_name=(EditText)findViewById(R.id.Admin_phone);
        Admin_pass=(EditText)findViewById(R.id.Admin_password);
        admin_login=(Button)findViewById(R.id.Admin_login_button);
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String Phone=Admin_name.getText().toString().trim();
                    String Password=Admin_pass.getText().toString().trim();
                    admin_validate(Phone,Password);
            }
        });
    }
    private void admin_validate(String Phone,String Password){
        if(Phone.equalsIgnoreCase("98989")) {
            if (Password.equalsIgnoreCase("1231456") )
            {
                startActivity(new Intent(Admin.this, Admin_notification.class));
                Toast.makeText(Admin.this, "Redirected to Admin", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Admin.this, "You did n't have Enough Permissions", Toast.LENGTH_SHORT).show();
        }

    }

}
