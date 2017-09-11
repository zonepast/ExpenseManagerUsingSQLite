package com.example.aff02.expansemanager.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aff02.expansemanager.Database.DatabaseHelper;
import com.example.aff02.expansemanager.Model.DataModel;
import com.example.aff02.expansemanager.R;

public class ViewActivity extends AppCompatActivity {

    TextView viewName,viewPayment,viewRupees,viewDate,viewDesc;
    DatabaseHelper databaseHelper;
    String name,payment,desc,date;
    int rupees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().setTitle("View & Update Data");

        initViews();
        databaseHelper = new DatabaseHelper(this);

        name = getIntent().getExtras().getString("Name");
        viewName.setText(name);

        rupees = getIntent().getExtras().getInt("Rupees");
        viewRupees.setText(""+rupees);

        payment = getIntent().getExtras().getString("Payment");
        viewPayment.setText(payment);

        desc = getIntent().getExtras().getString("Desc");
        viewDesc.setText(desc);

        date = getIntent().getExtras().getString("Date");
        viewDate.setText(date);


    }

    private void initViews() {

        viewDate = (TextView)findViewById(R.id.viewDate);
        viewDesc = (TextView)findViewById(R.id.viewDesc);
        viewName = (TextView)findViewById(R.id.viewName);
        viewPayment = (TextView)findViewById(R.id.viewPayment);
        viewRupees = (TextView)findViewById(R.id.viewRupees);
    }
}
