package com.example.moviesapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.signUp_button)
    Button btn;
    @BindView(R.id.email_editText)
    EditText mailEditText;
    @BindView(R.id.password_editText)
    EditText passwordEditText;
    @BindView(R.id.userName_editText)
    EditText usernameEditText;
    @BindView(R.id.haveAccount_textView)
    TextView haveAccount;
    String password, email,user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void goToMainActivity(){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    private void registerUser(){
        user_name = usernameEditText.getText().toString();
        email = mailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "you must fill all Data please..", Toast.LENGTH_SHORT).show();
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    goToMainActivity();
                }else {
                    Toast.makeText(RegisterActivity.this, "some error occurred, please try again later..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}