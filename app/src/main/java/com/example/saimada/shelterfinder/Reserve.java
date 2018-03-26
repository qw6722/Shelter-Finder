package com.example.saimada.shelterfinder;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
 * @author Shishir
 * @since 3/11/18
 */
public class Reserve extends AppCompatActivity{

    private TextView nameOfShelter;
    private EditText numOfBeds;
    private TextView shelterCapacity;
    private Button reserve;
    private Button cancel;
    private Button checkOut;
    private Toolbar toolbar;
    private DatabaseReference ref;
    private String _numOfPeople;

    // Ya I know this code design sucks but Java 7 also sucks
    private static boolean ret;
    private int reservations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        nameOfShelter = findViewById(R.id.NameOfShelter);
        numOfBeds = findViewById(R.id.NumberOfBeds);
        reserve =  findViewById(R.id.reserve);
        shelterCapacity = findViewById(R.id.shelterCapacity);
        cancel = findViewById(R.id.cancel);
        checkOut = findViewById(R.id.checkOut);


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
        shelterCapacity.setText("Capacity: " + cap);

        _numOfPeople = "";

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _numOfPeople =  numOfBeds.getText().toString();
                int _numIntOfPeople;
                try {
                    _numIntOfPeople = Integer.parseInt(_numOfPeople);
                } catch (NumberFormatException e) {
                    _numIntOfPeople = 0;
                }
                //get the int value of the shelter capacity
                boolean notCheckedIn = notCheckedIn();
                if (_numIntOfPeople > 0 && notCheckedIn) {
                    if ((_numIntOfPeople < 0 || _numIntOfPeople > getShelterCapacity())) {
                        Toast.makeText(Reserve.this,
                                "Number invalid. It must be positive and less than the "
                                        + "shelter capacity.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String cap = "Blah";
                        setShelterCapacity(_numIntOfPeople, cap);
                    }
                } else if (!notCheckedIn) {
                    Toast t = Toast.makeText(getApplicationContext(), "Cannot check into more "
                            + "than one shelter. Check out of the previous one first",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cap = "Blah";
                int _numIntOfPeople;
                try {
                    _numIntOfPeople = Integer.parseInt(numOfBeds.getText().toString());
                } catch (NumberFormatException e) {
                    _numIntOfPeople = 0;
                }
                addShelterCapacity(_numIntOfPeople, cap);
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOut();
            }
        });
    }

    private void checkOut() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("checkedin")
                .setValue("false");

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String capacity = extras.getString("shelter_capacity");
        final int total = Integer.parseInt(capacity) + reservations;
        ref = FirebaseDatabase.getInstance().getReference().child("Data");
        ref.child(findParent()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot
                        .getRef()
                        .child("Capacity")
                        .setValue(String.valueOf(total));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        shelterCapacity.setText("Capacity: "+ total);
    }

    //create a method that gets the data from Firebase and returns the shelter's reservation int
    public int getShelterCapacity() {
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("shelter_name");
        String cap = extras.getString("shelter_capacity");
        return Integer.parseInt(cap);
    }

    public void setShelterCapacity(int reservations, String cap) {
        this.reservations = reservations;
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

    private boolean isCheckedIn() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        DatabaseReference user = reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.child("checkedin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                Reserve.ret = Boolean.parseBoolean(s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return ret;
    }

    private boolean notCheckedIn() {
        boolean ret = isCheckedIn();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        DatabaseReference user = reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.child("checkedin").setValue("true");
        return !ret;
    }
}
