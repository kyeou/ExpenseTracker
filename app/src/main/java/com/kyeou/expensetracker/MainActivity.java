package com.kyeou.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
//import com.kyeou.expensetracker.databinding.ActivityMainBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.kyeou.expensetracker.MESSAGE";

    // Used to load the 'expensetracker' library on application startup.

        static {System.loadLibrary("expensetracker");}


    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Button btn = (Button)findViewById(R.id.addExpense);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.addexpense);
            }
        }); */
    }




    public void addBExpense(View view) throws IOException {
            //to write to files, i have to gather the text that needs to be writting and return it from a CPP functoin through JNI
       String message = "TEXT NOT CHANGED";
        File path = getFilesDir();
        File file = new File(path, "transactionsJSON.json");
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(message.getBytes());
            message = addExpense();
        } finally {
            stream.close();
        }
        Intent intent = new Intent(this, AddExpense.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();

        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    /**
     * A native method that is implemented by the 'expensetracker' native library,
     * which is packaged with this application.
     */
    public native String addExpense();
    //public native String stringFromJNI2();
}