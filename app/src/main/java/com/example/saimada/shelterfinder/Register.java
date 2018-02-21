package com.example.saimada.shelterfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText ed1,ed2, ed3;
    Button b1;
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

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);
        ed3 = (EditText)findViewById(R.id.editText3);
        userType = (Switch)findViewById(R.id.switch1);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (ed3.getText().toString().compareTo(ed2.getText().toString()) == 0) && !(ed1.getText().toString() == "")) {
                    createAccount(ed1.getText().toString(),ed2.getText().toString());
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    User userAdding = new User(ed1.getText().toString(), ed2.getText().toString(), userType.isChecked());
                    dbRef.child("users").child(userID).setValue(user);


                }else {
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
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void createAccount(String s, String s1) {
        mAuth.createUserWithEmailAndPassword(s,s1);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

}
