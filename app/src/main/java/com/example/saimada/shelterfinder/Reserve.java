package com.example.saimada.shelterfinder;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Represents a Reservation Page
 * @author arnabdey
 * @since 3/11/18
 */
public class Reserve extends AppCompatActivity{

    private TextView nameOfShelter;
    private EditText numOfBeds;
    private TextView shelterCapacity;
    private Button reserve;
    private Button cancel;
    private Toolbar toolbar;
    private DatabaseReference ref;
    List<Shelter> list = new ArrayList<>();
    private String _numOfPeople;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        nameOfShelter = findViewById(R.id.NameOfShelter);
        numOfBeds = findViewById(R.id.NumberOfBeds);
        reserve =  findViewById(R.id.reserve);
        shelterCapacity = findViewById(R.id.shelterCapacity);
        cancel = findViewById(R.id.cancel);

        toolbar = findViewById(R.id.toolbarReserve);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("shelter_name");
        String cap = extras.getString("shelter_capacity");

        nameOfShelter.setKeyListener(null);
        nameOfShelter.setText(name);
        shelterCapacity.setKeyListener(null);
        shelterCapacity.setText("Capacity: "+cap);

        _numOfPeople = "";

        /*ref = FirebaseDatabase.getInstance().getReference().child("Data");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Shelter>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Shelter value = dataSnapshot1.getValue(Shelter.class);
                    list.add(value);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _numOfPeople =  numOfBeds.getText().toString();
                int _numIntOfPeople =  Integer.parseInt(_numOfPeople);
                //get the int value of the shelter capacity
                if (_numIntOfPeople < 0 || _numIntOfPeople > getShelterCapacity()) {
                    Toast.makeText(Reserve.this,
                            "Number invalid. It must be positive and less than the shelter capacity.",
                            Toast.LENGTH_SHORT).show();
                    _numIntOfPeople = 0;
                } else {
                    String cap = "Blah";
                    setShelterCapacity(_numIntOfPeople, cap);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cap = "Blah";
                int _numIntOfPeople = Integer.parseInt(numOfBeds.getText().toString());
                addShelterCapacity(_numIntOfPeople, cap);
            }
        });
    }

    //create a method that gets the data from Firebase and returns the shelter's reservation int
    public int getShelterCapacity() {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String cap = extras.getString("shelter_capacity");
        return Integer.parseInt(cap);
    }

    public void setShelterCapacity(int reservations, String cap) {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String capacity = extras.getString("shelter_capacity");
        final int reserve = Integer.parseInt(capacity) - reservations;

        ref = FirebaseDatabase.getInstance().getReference().child("Data");
        ref.child(findParent()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Capacity").setValue("" + reserve);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        shelterCapacity.setText("Capacity: "+reserve);
    }

    public void addShelterCapacity(int reservations, String cap) {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String capacity = extras.getString("shelter_capacity");
        System.out.println(capacity);
        final int reserve = Integer.parseInt(capacity);

        ref = FirebaseDatabase.getInstance().getReference().child("Data");
        ref.child(findParent()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Capacity").setValue("" + reserve);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        shelterCapacity.setText("Capacity: " + reserve);
    }

    public String findParent() {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String cap = extras.getString("shelter_capacity");
        return extras.getString("shelter_key");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
