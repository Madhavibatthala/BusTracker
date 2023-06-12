package com.example.bustracker1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button login_btn;
    EditText txt1,txt2;
    String user_name,pass;
    FirebaseAuth mauth;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn=(Button) findViewById(R.id.login);
        txt1=(EditText)findViewById(R.id.edt_usr);
        txt2=(EditText)findViewById(R.id.edt_pass);
        progressDialog=new ProgressDialog(LoginActivity.this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading..");
                progressDialog.setTitle("Checking Credentials");
                progressDialog.setCancelable(false);
                progressDialog.show();
                user_name=txt1.getText().toString();
                pass=txt2.getText().toString();
                mauth=FirebaseAuth.getInstance();
                if(user_name.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    mauth.signInWithEmailAndPassword(user_name,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            sharedPreferences=getSharedPreferences("SHARED_PREFERENCES",MODE_PRIVATE);
                            SharedPreferences.Editor edit=sharedPreferences.edit();
                            String userId=mauth.getUid();
                            edit.putString("UID",userId);

                            String[] sub = user_name.split("@");
                            edit.putString("name",sub[0]);

                            edit.apply();
//                            Toast.makeText(LoginActivity.this, "Shared preferences stored and they are"+sharedPreferences.getString("UID","not found"), Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                            finish();
                            startActivity(new Intent(LoginActivity.this,AfterLogin.class));
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                    });
                }
            }
        });
    }
//    private void updateisLoggedIn(){
//        databaseReference.child("isLoggedIn").setValue("yes");
//        Toast.makeText(this, "isLoggeinvalue is cahnged", Toast.LENGTH_SHORT).show();
//        isLoggedIn=false;
//    }

}
