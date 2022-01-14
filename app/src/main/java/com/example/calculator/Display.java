package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display {
    TextView textView;

    Display(TextView textView){
        this.textView = textView;
    }

    public void addDisplay(View v, Button button, boolean flag){
        CharSequence text = button.getText();
        if (text.equals("=")){
            Calculate calculate = new Calculate();
            Double result = calculate.getResult(textView.getText().toString());
            textView.clearComposingText();
            textView.setText(String.valueOf(result));
            textView.clearComposingText();

        }else if (text.equals("AC")){
            textView.clearComposingText();
            textView.setText(String.valueOf(""));
            textView.clearComposingText();
        } else {
            if (flag){
                textView.clearComposingText();
                textView.setText(String.valueOf(""));
                textView.clearComposingText();
            }
            textView.append(button.getText());

        }
    }

}
