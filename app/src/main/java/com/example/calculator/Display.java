package com.example.calculator;

import android.widget.TextView;
/**
 * при наборе не видно что пишется за границей поля ввода
 * большие числа вылезают за поле
 * как удалить символ?
 * корень не работает
 * +/- не работает
 * при наборе не видно что пишется за границей поля ввода
 *
 * */
public class Display {
    final TextView textView;
    final Calculate calculate;
    boolean needNewTextView = false;
    int numberOpenBracket = 0;
    int numberCloseBracket = 0;
    boolean isLastSymbolNumber = true;
    boolean isMoreDot = true;
    String afterPushResultDisplay = "";

    Display(TextView textView){
        this.textView = textView;
        calculate = new Calculate();
    }

    public void addDisplayNumber(CharSequence buttonNumberText){
        if (!(textView.getText().charAt(textView.length()-1) == ')')){
            if (textView.getText().toString().equals("0") || needNewTextView){
                textView.clearComposingText();
                textView.setText("");
            }

            textView.append(buttonNumberText.toString());
            needNewTextView = false;
            isLastSymbolNumber = true;
        }

    }

    public void addDisplayInversionNumber(CharSequence operator){

    }

    public void addDisplayOpenBracket(CharSequence operation){
        if (!isLastSymbolNumber){
            numberOpenBracket++;
            textView.append(operation.toString());
            needNewTextView = false;

            isLastSymbolNumber = false;
            //|||||||||||||||||||||||||||||||||||||||
            isMoreDot = true;
        }
    }

    public void addDisplayDot(CharSequence operator){
        if (isMoreDot){
            if (isLastSymbolNumber){
                textView.append(operator.toString());
                isLastSymbolNumber = false;
                isMoreDot = false;
            }
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
        //||||||||||||||||||||||||||||||||||||||||||
        isMoreDot = true;
    }
    public void addDisplayOperation(CharSequence operation){
        if (needNewTextView){
            clearDisplayAfterOperation();
        }
        //если последний символ число
        if (isLastSymbolNumber) {
            //добавим операцию
            textView.append(operation.toString());
            needNewTextView = false;
            isLastSymbolNumber = false;
        } else {
            //проверить предыдущий символ на открывающий символ
            if (textView.getText().charAt(textView.length()-1) == '('){
                //добавить 0 между ними
                textView.append("0");
                textView.append(operation);
                return;
            }
            //вместо предыдущей операции поставим текущую
            String s = String.valueOf(textView.getText().subSequence(0, textView.length() - 1)) +
                    operation;
            textView.setText(s);
        }
        isMoreDot = true;
    }

    public void addDisplayResult(){
        try {
            //проверить что является последним символом число или действие?
            if (isLastSymbolNumber){
                //подсчет открывающих и закрывающих скобок
                if (numberOpenBracket > numberCloseBracket){
                    textView.setText(addCloseBracket(numberOpenBracket - numberCloseBracket));
                }
                //результат расчета
                afterPushResultDisplay = calculate.getResult(textView.getText().toString());

                //очистка дисплея и вывод результата
                textView.clearComposingText();
                textView.setText(afterPushResultDisplay);
                textView.clearComposingText();

            }
        }catch (Exception exception){
            textView.clearComposingText();
            textView.setText(R.string.Error);
            textView.clearComposingText();

        }finally {
            //начальные настройки
            needNewTextView = true;
            isLastSymbolNumber = true;
            numberOpenBracket = 0;
            numberCloseBracket = 0;
            isMoreDot = false;
        }

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

    public void clearDisplayAfterOperation(){
        clearDisplay();
        textView.setText(afterPushResultDisplay);
        isMoreDot = false;
    }

    public void backspace(){
        if (textView.length() > 1){
            String s = textView.getText().subSequence(0, textView.length()-1).toString();
            textView.setText(s);
        }else {
            clearDisplay();
        }

    }

    //очистка дисплея ввода
    public void clearDisplay(){
        textView.clearComposingText();
        textView.setText(R.string.firstDisplayNumber);
        isLastSymbolNumber = true;
        numberOpenBracket = 0;
        numberCloseBracket = 0;
        isMoreDot = true;

    }
}
