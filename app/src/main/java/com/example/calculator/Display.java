package com.example.calculator;

import android.widget.TextView;

public class Display {
    TextView textView;
    Calculate calculate;
    String result = "";
    boolean needNewTextView = false;
    int numberOpenBracket = 0;
    int numberCloseBracket = 0;
    boolean isLastSymbolNumber = true;

    Display(TextView textView){
        this.textView = textView;
        calculate = new Calculate();
    }

    public void addDisplayNumber(CharSequence buttonNumberText){
        if (textView.getText().toString().equals("0") || needNewTextView){
            textView.clearComposingText();
            textView.setText("");
        }
        textView.append(buttonNumberText.toString());
        needNewTextView = false;
        isLastSymbolNumber = true;
    }

    public void addDisplayOpenBracket(CharSequence operation){
        if (!isLastSymbolNumber){
            numberOpenBracket++;
            textView.append(operation.toString());
            needNewTextView = false;
        }
    }

    public void addDisplayCloseBracket(CharSequence operation){
        //открытые скобки не больше закрытых
        if (numberOpenBracket > numberCloseBracket){
            //проверить предыдущий символ на открывающий символ
            if (textView.getText().charAt(textView.length()-1) == '('){
                //добавить 0 между ними
                textView.append("0");
            }
            numberCloseBracket++;
            textView.append(operation.toString());
            needNewTextView = false;
            isLastSymbolNumber = true;
        }

    }
    public void addDisplayOperation(CharSequence operation){
        //если последний символ число
        if (isLastSymbolNumber) {
            //добавим операцию
            textView.append(operation.toString());
            needNewTextView = false;
            isLastSymbolNumber = false;
        } else {
            //вместо предыдущей операции поставим текущую
            StringBuilder s = new StringBuilder();
            s.append(textView.getText().subSequence(0, textView.length()-1));
            s.append(operation);
            textView.setText(s.toString());
        }
    }

    public void addDisplayResult(){
        //подсчет открывающих и закрывающих скобок
            if (numberOpenBracket > numberCloseBracket){
              textView.setText(addCloseBracket(numberOpenBracket - numberCloseBracket));
            }
        //проверить что является последним символом число или действие?
        if (!isLastSymbolNumber){
            textView.append("0");
        }
        result = calculate.getResult(textView.getText().toString());

        textView.clearComposingText();
        textView.setText(result);
        textView.clearComposingText();
        needNewTextView = true;
        isLastSymbolNumber = true;
        numberOpenBracket = 0;
        numberCloseBracket = 0;


    }

    //добавление недостающих закрывающих скобок
    String addCloseBracket(int addBracket){
        StringBuilder s = new StringBuilder(textView.getText());
        while (addBracket > 0){
            s.append(")");
            addBracket--;
        }
        return s.toString();
    }

    //очистка дисплея ввода
    public void clearDisplay(){
        textView.clearComposingText();
        textView.setText(R.string.firstDisplayNumber);
        isLastSymbolNumber = true;
        numberOpenBracket = 0;
        numberCloseBracket = 0;
    }
}
