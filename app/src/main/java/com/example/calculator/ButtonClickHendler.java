package com.example.calculator;

import android.view.View;
import android.widget.Button;

public class ButtonClickHendler implements View.OnClickListener {
    Display display;
    Button button;
    boolean flagClick = false;

    ButtonClickHendler(Display display, Button button, boolean flag){
        this.display = display;
        this.button = button;
        flagClick = flag;
    }

//    ButtonClickHendler(Display display, Button button){
//        this.display = display;
//        this.button = button;
//    }
    
    @Override
    public void onClick(View v) {
        display.addDisplay(v, button, flagClick);
    }
}
