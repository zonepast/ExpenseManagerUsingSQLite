package com.example.aff02.expansemanager.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aff02.expansemanager.Adapter.RecycleAdapter;
import com.example.aff02.expansemanager.Database.DatabaseHelper;
import com.example.aff02.expansemanager.Model.DataModel;
import com.example.aff02.expansemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class FillForm extends AppCompatActivity {

    private EditText edtName,edtRupees,edtdesc;
    String name,desc,rupees,payment,date;
    private TextInputLayout nameinputLayout,rupeesinputLayout,descinputlayout;
    private Spinner spinner;
    List<DataModel> dataModelsList = new ArrayList<DataModel>();
    DataModel model = new DataModel();
    List<String> list =  new ArrayList<>();
    ArrayAdapter<String> adapter;
    private TextView datepicker,txtpayment,txtsetdate,temptxt;
    private Button btnsave,btnshowlist;
    Calendar myCalendar = Calendar.getInstance();
    DatabaseHelper databaseHelper;
    RecycleAdapter recycleAdapter;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        getSupportActionBar().setTitle("Enter the Details");

        initViews();
        setListeners();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        list.add("Credit Card");
        list.add("Debit Card");
        list.add("Mobile Payment");
        list.add("Online Payment");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initViews() {

        datepicker = (TextView)findViewById(R.id.datepicker);
        txtsetdate = (TextView)findViewById(R.id.txtsetdate);

        btnsave = (Button)findViewById(R.id.btnsave);
        btnshowlist = (Button)findViewById(R.id.btnshowlist);

        edtName = (EditText)findViewById(R.id.edtName);
        nameinputLayout = (TextInputLayout)findViewById(R.id.input_layout_name);
        edtName.addTextChangedListener(new MyTextWatcher(edtName));

        rupeesinputLayout=(TextInputLayout)findViewById(R.id.txtipRupees);
        edtRupees = (EditText) findViewById(R.id.edtRupees);
        edtRupees.addTextChangedListener(new MyTextWatcher(edtRupees));

        descinputlayout =(TextInputLayout)findViewById(R.id.textipdesc);
        edtdesc=(EditText)findViewById(R.id.edtDesc);
        edtdesc.addTextChangedListener(new MyTextWatcher(edtdesc));

        spinner = (Spinner)findViewById(R.id.spinner);

        txtpayment = (TextView)findViewById(R.id.txtpayment);
        temptxt = (TextView)findViewById(R.id.temp);
    }

    private void setListeners() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = spinner.getSelectedItem().toString();
                DataModel dataModel = new DataModel();
                dataModel.setPayment(item);
                temptxt.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FillForm.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edtName.getText().toString();
                desc =edtdesc.getText().toString();
                rupees = edtRupees.getText().toString();
                 payment = temptxt.getText().toString();

                if (name.isEmpty() && desc.isEmpty() && rupees.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Fill Details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.e("Name",edtName.getText().toString());
                    Log.e("Rupees",edtRupees.getText().toString());
                    Log.e("Desc",edtdesc.getText().toString());
                    Log.e("Date",txtsetdate.getText().toString());
                    Log.e("Payment", temptxt.getText().toString());

                    databaseHelper = new DatabaseHelper(FillForm.this);

                    databaseHelper.insertData(name,rupees,desc, String.valueOf(date),payment);


                    edtName.setText("");
                    edtdesc.setText("");
                    edtRupees.setText("");
                    txtsetdate.setText("");
                    temptxt.setText("");
                    Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnshowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent(FillForm.this,RecycleActivity.class);
                startActivity(intent1);
            }
        });
    }


    private void updateLabel() {

        String myFormat = "MMM dd,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtsetdate.setText(sdf.format(myCalendar.getTime()));
    }

    static class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}


