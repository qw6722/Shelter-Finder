package com.example.saimada.shelterfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SingleShelterView extends AppCompatActivity {

    TextView name;
    TextView capacity;
    TextView restrictions;
    TextView address;
    TextView phone;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_shelter_view);
        Bundle extras = getIntent().getExtras();

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.nameField);
        capacity = findViewById(R.id.capacityField);
        restrictions = findViewById(R.id.restrictionField);
        address = findViewById(R.id.addressField);
        phone = findViewById(R.id.phoneField);

        name.setKeyListener(null);
        capacity.setKeyListener(null);
        restrictions.setKeyListener(null);
        address.setKeyListener(null);
        phone.setKeyListener(null);



        name.setText(extras.getString("shelter_name"));
        capacity.setText(extras.getString("shelter_capacity"));
        restrictions.setText(extras.getString("shelter_restriction"));
        address.setText(extras.getString("shelter_address"));
        phone.setText(extras.getString("shelter_phone"));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
