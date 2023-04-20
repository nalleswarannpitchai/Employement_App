package com.example.fixxpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {
//   initializing the objects name to the class
    EditText editTextName , editTextEmail  , editTextDoB , editTextMobileNo , editTextPassword , editTextCnfPassword;
    Button btnRegister;
    TextView textViewSignIn;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

   /*  -> Needs to be understood
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    */

//    assigning the id to the reference variable by findViewById

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        editTextName = findViewById(R.id.et_enter_name);
        editTextEmail = findViewById(R.id.et_enter_email);
        editTextDoB = findViewById(R.id.et_enter_dob);
        editTextMobileNo = findViewById(R.id.et_enter_mobile);
        editTextPassword = findViewById(R.id.et_enter_password);
        editTextCnfPassword = findViewById(R.id.et_enter_repassword);
        textViewSignIn = findViewById(R.id.tvsignIn);

        btnRegister = findViewById(R.id.regbutton);

        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();



//        onClick listner on text view
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent sliding from registration activity to login activity
                Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });



//        onClick Listner on button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

//                assigning the variables to take the values in the string
                String email , password , name , doB , mobileNumber , cnfPassword ;

                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                name = String.valueOf(editTextName.getText());
                doB = String.valueOf(editTextDoB.getText());
                mobileNumber = String.valueOf(editTextMobileNo);
                cnfPassword = String.valueOf(editTextCnfPassword.getText());

//                if else statement to pass all the cases so that the details of the user can be sent for authentication

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(Register_Activity.this,"Please enter the full name ",Toast.LENGTH_LONG).show();
                    editTextName.setError("Full Name Required ");
                    editTextName.requestFocus();
                    progressBar.setVisibility(View.GONE);

                }else if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register_Activity.this,"Email is required ",Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Email Required ");
                    editTextEmail.requestFocus();
                    progressBar.setVisibility(View.GONE);

                } else if (TextUtils.isEmpty(doB)){
                    Toast.makeText(Register_Activity.this,"Dob is required ",Toast.LENGTH_LONG).show();
                    editTextDoB.setError("DoB Required ");
                    editTextDoB.requestFocus();
                    progressBar.setVisibility(View.GONE);

                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register_Activity.this,"Password is required ",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password Required ");
                    editTextPassword.requestFocus();
                    progressBar.setVisibility(View.GONE);

                }else if (TextUtils.isEmpty(mobileNumber)){
                    Toast.makeText(Register_Activity.this,"Mobile Number is required ",Toast.LENGTH_LONG).show();
                    editTextMobileNo.setError("Cant be empty ");
                    editTextMobileNo.requestFocus();
                    progressBar.setVisibility(View.GONE);

                }else if (TextUtils.isEmpty(cnfPassword)){
                    Toast.makeText(Register_Activity.this,"Please re-enter the password ",Toast.LENGTH_LONG).show();
                    editTextCnfPassword.setError("Confirm Password ");
                    editTextCnfPassword.requestFocus();
                    progressBar.setVisibility(View.GONE);

                } else {
//                            using the object of firebase
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Register_Activity.this,"Registration is sucessfull",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register_Activity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                                        startActivity(intent);

                                    }
                                }


                            });


                }

            }
        });

    }
}