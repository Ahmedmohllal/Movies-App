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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.Login)
    Button button;
    @BindView(R.id.editTextTextPassword)
    EditText editText;
    @BindView(R.id.editTextTextEmailAddress)
    EditText mailEditText;
    @BindView(R.id.needaccount_view)
    TextView needaccount;
    String password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        needaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginAccount();
            }
        });


    }

    private void LoginAccount(){
        email = mailEditText.getText().toString();
        password = editText.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Email and password are require..", Toast.LENGTH_SHORT).show();
        } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            gotToMainActivity();
                        }else {
                            Toast.makeText(LoginActivity.this, "please sign up first..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }

    }

    private void gotToMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}