package com.example.aff02.expansemanager.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.aff02.expansemanager.Database.DatabaseHelper;
import com.example.aff02.expansemanager.Model.DataModel;
import com.example.aff02.expansemanager.R;

public class UpdateActivity extends AppCompatActivity {

    EditText updateName,updateDesc,updateRupees,updateDate,updatePayment;
    String name,payment,desc,date;
    int rupees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Update Data");

        initViews();

        name = getIntent().getExtras().getString("Name");
        updateName.setText(name);

        rupees = getIntent().getExtras().getInt("Rupees");
        updateRupees.setText(""+rupees);

        payment = getIntent().getExtras().getString("Payment");
        updatePayment.setText(payment);

        desc = getIntent().getExtras().getString("Desc");
        updateDesc.setText(desc);

        date = getIntent().getExtras().getString("Date");
        updateDate.setText(date);
    }



    private void initViews() {

        updateDate = (EditText)findViewById(R.id.updateDate);
        updateName =  (EditText)findViewById(R.id.updateName);
        updateDesc = (EditText)findViewById(R.id.updateDesc);
        updatePayment = (EditText)findViewById(R.id.updatePayment);
        updateRupees = (EditText)findViewById(R.id.updateRupees);
    }
}
