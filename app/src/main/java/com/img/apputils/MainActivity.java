package com.img.apputils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rohit.apputils.AppUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppUtils.customToast(MainActivity.this,"Test");
    }
}