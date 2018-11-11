package com.mats.alertavecinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.mats.alertavecinal.com.mats.alertavecinal.controllers.HomeActivity;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private  TextView email;
    private  TextView password;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPass);
        btnSignIn = findViewById(R.id.btnSignIn);
    }
    //Toast.makeText(context, text, duration);
    //toast.show();
    public void signIn(View view){
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar el email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("creado", "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(RegisterActivity.this,"Ese usuario se registro", Toast.LENGTH_SHORT).show();

                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){// si se presenta una colision.
                                Toast.makeText(RegisterActivity.this,"Ese usuario ya existe", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                            // If sign in fails, display a message to the user.
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void logIn(View view){
        final String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar el email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0,pos);
                            Toast.makeText(RegisterActivity.this,"Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), HomeActivity.class);
                            intent.putExtra(HomeActivity.user,user);
                            startActivity(intent);

                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){// si se presenta una colision.
                                Toast.makeText(RegisterActivity.this,"Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                            // If sign in fails, display a message to the user.
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
