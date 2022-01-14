package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Calculator with simple functions
 * by Viacheslav 01/12/21 Ver.001
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewPole = findViewById(R.id.textPole);
        Display display = new Display(textViewPole);


        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);
        Button button_0 = findViewById(R.id.button_0);
        Button button_AC = findViewById(R.id.button_AC);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_result = findViewById(R.id.button_result);
        button_1.setOnClickListener(new ButtonClickHendler(display, button_1, false));
        button_2.setOnClickListener(new ButtonClickHendler(display, button_2, false));
        button_3.setOnClickListener(new ButtonClickHendler(display, button_3, false));
        button_4.setOnClickListener(new ButtonClickHendler(display, button_4, false));
        button_5.setOnClickListener(new ButtonClickHendler(display, button_5, false));
        button_6.setOnClickListener(new ButtonClickHendler(display, button_6, false));
        button_7.setOnClickListener(new ButtonClickHendler(display, button_7, false));
        button_8.setOnClickListener(new ButtonClickHendler(display, button_8, false));
        button_9.setOnClickListener(new ButtonClickHendler(display, button_9, false));
        button_0.setOnClickListener(new ButtonClickHendler(display, button_0, false));
        button_plus.setOnClickListener(new ButtonClickHendler(display, button_plus, true));
        button_result.setOnClickListener(new ButtonClickHendler(display, button_result, true));
        button_AC.setOnClickListener(new ButtonClickHendler(display, button_AC, false));
        button_minus.setOnClickListener(new ButtonClickHendler(display, button_minus, true));
        button_multiply.setOnClickListener(new ButtonClickHendler(display, button_multiply, true));
        button_divide.setOnClickListener(new ButtonClickHendler(display, button_divide, true));
    }
}