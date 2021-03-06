package com.kyeou.expensetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static {
        System.loadLibrary("expensetracker");
    }
    // This is for the Calendar
    private TextView datetext, errorMessage;

    // These are for input fields to add expense
    String description, amount;
    EditText descriptionInput;
    EditText amountInput;
    Button addButton;

    int month, day, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Calendar function
        datetext = findViewById(R.id.date_text);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // get data that user types and write it to a file
    public void gatherFieldTest(View view) throws IOException {

        errorMessage = findViewById(R.id.errorMessage);
        descriptionInput = findViewById(R.id.descriptionField);
        amountInput = findViewById(R.id.amountField);

        description = descriptionInput.getText().toString();
        amount = amountInput.getText().toString();


        if(description.equals("")){
            errorMessage.setText("Description Field Is Empty");
        }else if(amount.equals("0") || amount.equals("0.00")){
            errorMessage.setText("Amount Must Be Greater Than 0");
        }else if(datetext.getText().toString().equals("MONTH/DAY/YEAR")){
            errorMessage.setText("Please Select A Date");
        }else{
            String temp_parm = new WriteReadHandle().ReadHandle("transactions.json");
            if (temp_parm.equals("")) {new WriteReadHandle().WriteHandle("transactions.json", "[]");}



            new WriteReadHandle().WriteHandle("transactions.json", addTrans(description, day, month, year, Float.valueOf(amount).floatValue(),
                    new WriteReadHandle().ReadHandle("transactions.json")));
            //recordDebits();
            new WriteReadHandle().WriteHandle("user.json", getUSERSJSON());
            new WriteReadHandle().WriteHandle("transactions.json", getTRANSJSON());

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onDateSet(DatePicker datePicker, int yearSelected, int monthSelected, int dayOfMonthSelected) {
        day = dayOfMonthSelected;
        year = yearSelected;
        month = monthSelected + 1;

        String date = month + "/" + day + "/" + year;

        datetext.setText(date);
    }



    public void exitPage(View view) throws IOException {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public native String addTrans(String name, int day, int month, int year, float amount, String JSON);

    //public native void recordDebits();

    public native String getUSERSJSON();

    public native String getTRANSJSON();

}