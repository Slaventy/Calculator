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
        Button button_result = findViewById(R.id.button_result);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_bracketOpen = findViewById(R.id.button_bracketOpen);
        Button button_bracketClose = findViewById(R.id.button_bracketClose);
        Button button_dot = findViewById(R.id.button_dot);
        Button button_inversion = findViewById(R.id.button_inversion);

        button_0.setOnClickListener(v -> display.addDisplayNumber(button_0.getText()));
        button_1.setOnClickListener(v -> display.addDisplayNumber(button_1.getText()));
        button_2.setOnClickListener(v -> display.addDisplayNumber(button_2.getText()));
        button_3.setOnClickListener(v -> display.addDisplayNumber(button_3.getText()));
        button_4.setOnClickListener(v -> display.addDisplayNumber(button_4.getText()));
        button_5.setOnClickListener(v -> display.addDisplayNumber(button_5.getText()));
        button_6.setOnClickListener(v -> display.addDisplayNumber(button_6.getText()));
        button_7.setOnClickListener(v -> display.addDisplayNumber(button_7.getText()));
        button_8.setOnClickListener(v -> display.addDisplayNumber(button_8.getText()));
        button_9.setOnClickListener(v -> display.addDisplayNumber(button_9.getText()));

        button_plus.setOnClickListener(v -> display.addDisplayOperation(button_plus.getText()));
        button_minus.setOnClickListener(v -> display.addDisplayOperation(button_minus.getText()));

        button_multiply.setOnClickListener(v -> display.addDisplayOperation(button_multiply.getText()));
        button_divide.setOnClickListener(v -> display.addDisplayOperation(button_divide.getText()));

        button_dot.setOnClickListener(v -> display.addDisplayDot(button_dot.getText()));
        button_inversion.setOnClickListener(v -> display.addDisplayInversionNumber(button_inversion.getText()));

        button_bracketOpen.setOnClickListener(v -> display.addDisplayOpenBracket(button_bracketOpen.getText()));
        button_bracketClose.setOnClickListener(v -> display.addDisplayCloseBracket(button_bracketClose.getText()));

        button_result.setOnClickListener(v -> display.addDisplayResult());

        button_AC.setOnClickListener(v -> display.clearDisplay());
    }
}