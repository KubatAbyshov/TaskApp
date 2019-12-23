package com.geektech.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {


    private EditText editNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private EditText codeNumber;
    private Button btn_signIn;
    private String verificationId;
    private FirebaseAuth auth;
    private LinearLayout linearLayout, linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        auth = FirebaseAuth.getInstance();
        linearLayout = findViewById(R.id.viewPhone);
        editNumber = findViewById(R.id.editNumber);
        codeNumber = findViewById(R.id.codeNumber);
        linear = findViewById(R.id.viewCodePage);
        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });


        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                Log.e("TAG", "onVerificationCompleted");


            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Log.e("TAG", "onVerificationFailed: " + e.getMessage());

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        };


    }

    private void verifySignInCode() {
        String code = codeNumber.getText().toString();
        if (TextUtils.isEmpty(code)) {
            return;
        }
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signIn(credential);
    }


    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance()
                .signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                            finish();
                        }  else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }

                        }
                    }
                });
    }


    public void onClick(View view) {
        String number = editNumber.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this,
                callbacks);
        linearLayout.setVisibility(View.GONE);
        linear.setVisibility(View.VISIBLE);

    }
}
