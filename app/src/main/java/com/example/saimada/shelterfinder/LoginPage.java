package com.example.saimada.shelterfinder;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.util.Log;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SearchView;
import android.widget.TextView;

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

public class LoginPage extends AppCompatActivity {

    Toolbar toolbar = null;
    RecyclerView recyclerView = null;
    List<Shelter> list = new ArrayList<>();
    List<Shelter> filteredList = new ArrayList<>();

    private DatabaseReference ref;
    RecyclerView.Adapter adapter;
    SwipeController swipeController;
    //Used for binding and getting information
    private Spinner genderSpinner;
    private Spinner ageSpinner;
    private Button filter;
    private Button clear;
    private SearchView search;

    //Keeping track of Spinner changes
    private String _gender = "NA";
    private String _age = "Anyone";
    private String _search = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        genderSpinner = (Spinner) findViewById(R.id.Gender);
        ageSpinner = (Spinner) findViewById((R.id.Age));

        filter = (Button) findViewById(R.id.Filter);
        clear = (Button) findViewById(R.id.Clear);
        search = (SearchView) findViewById(R.id.searchCriteria);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _gender = genderSpinner.getSelectedItem().toString();
                _age = ageSpinner.getSelectedItem().toString();
                _search  = search.getQuery().toString();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAddress().toLowerCase().contains(_search.toLowerCase())) {
                        filteredList.add(list.get(i));
                    } else if (list.get(i).getRestrictions().toLowerCase().contains(_gender.toLowerCase())) {
                        filteredList.add(list.get(i));
                    } else if (_age.equals("Children") && list.get(i).getRestrictions().toLowerCase().contains("children")) {
                        filteredList.add(list.get(i));
                    } else if (_age.equals("FamilyAndNewborn") && list.get(i).getRestrictions().toLowerCase().contains("newborn")) {
                        filteredList.add(list.get(i));
                    } else if (_age.equals("YoungAdult") && list.get(i).getRestrictions().toLowerCase().contains("young adult")) {
                        filteredList.add(list.get(i));
                    } else if (_age.equals("Anyone") || _gender.equals("Anyone")) {
                        filteredList.add(list.get(i));
                    }
                }
                Log.e("size ", (String.valueOf(filteredList.size())));
                adapter = new ShelterRecyclerAdapter(filteredList,LoginPage.this);
                recyclerView.setAdapter(adapter);
                Log.e("CHECKING!!!!!!!!!!!!",_gender + " " + _age + " " + _search);


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _gender = "NA";
                _age = "Anyone";
                _search = "";
                genderSpinner.setSelection(0);
                ageSpinner.setSelection(0);
                search.setQuery("", false);
                search.clearFocus();
                Log.e("CHECKING!!!!!!!!! Clear",_gender + " " + _age + " " + _search);
                adapter = new ShelterRecyclerAdapter(list,LoginPage.this);
                recyclerView.setAdapter(adapter);

            }
        });

        ArrayAdapter<String> adapterGenderSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,User.possibleGender);
        adapterGenderSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapterGenderSpinner);

        ArrayAdapter<String> adapterAgeSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,User.possibleAges);
        adapterAgeSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(adapterAgeSpinner);



        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(LoginPage.this));

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        swipeController= new SwipeController(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {
                Intent intent = new Intent(LoginPage.this, SingleShelterView.class);
                intent.putExtra("shelter_name",list.get(position).getShelterName());
                intent.putExtra("shelter_capacity",list.get(position).getCapacity());
                intent.putExtra("shelter_restriction",list.get(position).getRestrictions());
                intent.putExtra("shelter_address",list.get(position).getAddress());
                intent.putExtra("shelter_phone",list.get(position).getPhoneNumber());
                startActivity(intent);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        ref = FirebaseDatabase.getInstance().getReference().child("Data");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Shelter>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Shelter value = dataSnapshot1.getValue(Shelter.class);
                    list.add(value);

                }
                adapter = new ShelterRecyclerAdapter(list,LoginPage.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




    }


    private void sendToStart() {
        Intent startIntent = new Intent(LoginPage.this, Opening.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            sendToStart();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
