package com.example.aff02.expansemanager.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aff02.expansemanager.Adapter.RecycleAdapter;
import com.example.aff02.expansemanager.Database.DatabaseHelper;
import com.example.aff02.expansemanager.Model.DataModel;
import com.example.aff02.expansemanager.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    private ImageView imgDelete;
    private DatabaseHelper databaseHelper;
    private RecyclerView recycler;
    private RecycleAdapter recycleAdapter;
    List<DataModel> dataModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        getSupportActionBar().setTitle("Expense Manager");

        initViews();

        dataModelsList = databaseHelper.getData();
        recycleAdapter = new RecycleAdapter(this,dataModelsList,false);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);

        if(dataModelsList.size() > 0){
            recycler.setVisibility(View.VISIBLE);
            RecycleAdapter mAdapter = new RecycleAdapter(this, dataModelsList,false);
            recycler.setAdapter(mAdapter);

        }else {
            recycler.setVisibility(View.GONE);
            Toast.makeText(this, "There is no data in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        recycler.setAdapter(recycleAdapter);
    }

    private void initViews() {

        databaseHelper = new DatabaseHelper(RecycleActivity.this);
        dataModelsList = new ArrayList<DataModel>();
        recycler = (RecyclerView) findViewById(R.id.recyclerview);
        imgDelete = (ImageView)findViewById(R.id.form_delete);
        recycler = (RecyclerView)findViewById(R.id.recyclerview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.addData:
                Toast.makeText(this,"Add Data",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RecycleActivity.this,FillForm.class);
                startActivity(intent);
                return (true);
            case R.id.deleteData:
                recycleAdapter = new RecycleAdapter(this,dataModelsList,true);
                recycler.setAdapter(recycleAdapter);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}
