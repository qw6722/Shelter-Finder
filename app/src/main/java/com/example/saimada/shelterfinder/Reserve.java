package com.example.saimada.shelterfinder;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by arnabdey on 3/11/18.
 */

public class Reserve  extends AppCompatActivity{

    private TextView nameOfShelter;
    private EditText numOfBeds;
    private Button reserve;
    private Toolbar toolbar;

    private String _numOfPeople;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        nameOfShelter = (TextView) findViewById(R.id.NameOfShelter);
        numOfBeds = findViewById(R.id.NumberOfBeds);
        reserve =  findViewById(R.id.reserve);

        toolbar = (Toolbar) findViewById(R.id.toolbarReserve);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("shelter_name");

        nameOfShelter.setKeyListener(null);
        nameOfShelter.setText(name);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _numOfPeople =  numOfBeds.getText().toString();
                int _numIntOfPeople =  Integer.parseInt(_numOfPeople);
                if (_numIntOfPeople < 0 || _numIntOfPeople > 50) { //get the int value of the shelter capacity
                    Toast.makeText(Reserve.this, "Number invalid. It must be positive and less than the shelter capacity.",
                            Toast.LENGTH_SHORT).show();
                    _numIntOfPeople = 0;
                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
