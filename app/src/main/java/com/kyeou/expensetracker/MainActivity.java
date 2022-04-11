package com.kyeou.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.kyeou.expensetracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'expensetracker' library on application startup.
    static {
        System.loadLibrary("expensetracker");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

        TextView tv2 = binding.sampleText;
        tv.setText(stringFromJNI2());
    }

    /**
     * A native method that is implemented by the 'expensetracker' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String stringFromJNI2();
}