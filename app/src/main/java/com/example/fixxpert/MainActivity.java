package com.example.fixxpert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button buttonSignOut;
    TextView textView , textView1;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        buttonSignOut = findViewById(R.id.btnLogout);
        textView = findViewById(R.id.userDetails);
        textView1 = findViewById(R.id.main);
        user = auth.getCurrentUser();
        if (user==null){
            Intent intent = new Intent(MainActivity.this,Login_Activity.class);
            startActivity(intent);
            finish();
        } else {
            textView1.setText("Welcome");
            textView.setText(user.getEmail());
        }

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Logged Out Succesfully ",Toast.LENGTH_LONG).show();



                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(MainActivity.this,Login_Activity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}