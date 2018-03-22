package com.example.saimada.shelterfinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText ed1,ed2, ed3, ed4;
    Button b1;
    String adminPassword = "@dMin";
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;

    Toolbar toolbar;
    Switch userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        b1 = findViewById(R.id.button);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);
        ed4 = findViewById(R.id.editText4);
        ed4.setEnabled(false);
        userType = findViewById(R.id.switch1);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
        userType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed4.setEnabled(userType.isChecked());
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseUser user;
                    if( (ed3.getText().toString().compareTo(ed2.getText().toString()) == 0) && !(ed1.getText().toString() == "")) {
                    mAuth.createUserWithEmailAndPassword(ed1.getText().toString(),ed2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser(); //You Firebase user
                                String userID = user.getUid();
                                if (userType.isChecked()) {
                                    if (ed4.getText().toString().equals(adminPassword)) {
                                        User userAdding = new User(ed1.getText().toString(), ed2.getText().toString(), true);
                                        dbRef.child("users").child(userID).setValue(userAdding);
                                    }
                                    else {
                                        ed4.setError("Admin Password Not Equal");
                                    }
                                }
                                else {
                                    User userAdding = new User(ed1.getText().toString(), ed2.getText().toString(), false);
                                    dbRef.child("users").child(userID).setValue(userAdding);

                                }
                            }
                        }
                    });
                    }
                    else {
                    ed3.setError("Passwords Don't Match");
                    //refresh editText fields
                }
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mAuth.signOut();
        }
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void createAccount(String s, String s1) {
        Log.e(s,s1);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

}
