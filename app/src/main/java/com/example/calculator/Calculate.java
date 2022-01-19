package com.example.calculator;

import java.util.Stack;

/**
 *  производит вычисление выражения переданного в строке getResult(String)
 * */
public class Calculate {

    public String getResult(String expression){
        if (expression.isEmpty()){
            return "0";
        }else {
            return String.valueOf(calc(revPolNot(expression)));
        }
    }
    //вычислитель на основе стека для строки выражения в ОПЗ
    double calc(String str){
        StringBuilder stringBuilder = new StringBuilder();
        double calc = 0;
        Stack<Double> stack = new Stack<>();
        int index = 0;
        while (index < str.length()){
            switch (str.charAt(index)){
                case '+': {
                    calc = stack.pop() + stack.pop();
                    stack.push(calc);
                    break;
                }
                case '*': {
                    calc = stack.pop() * stack.pop();
                    stack.push(calc);
                    break;
                }
                case '-': {
                    double b = stack.pop();
                    double a = stack.pop();
                    calc = a - b;
                    stack.push(calc);
                    break;
                }
                case '/': {
                    double b = stack.pop();
                    double a = stack.pop();
                    calc = a / b;
                    stack.push(calc);
                    break;
                }
                case ' ': {
                    if (stringBuilder.length() > 0){
                        stack.push(Double.valueOf(stringBuilder.toString()));
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                    break;
                }
                default: {
                    stringBuilder.append(str.charAt(index));
                    break;
                }
            }

            index++;
        }
        return calc;
    }
    //преобразователь строки математического выражения инфиксной форме в
    //математическое выражение постфиксной записи или ОПЗ
    String revPolNot(String parsedString){

        char currentCharOfTheParseString;           //текущий символ разбираемой строки
        int indexCurrentCharOfTheParseString = 0;   //индекс текущего символа в разбираемой строке

        Stack<Character> stack = new Stack<>();             //стек для операций
        StringBuilder stringBuilder = new StringBuilder();  //создаваемая строка вывода

        while (indexCurrentCharOfTheParseString < parsedString.length()){
            //считали символ строки
            currentCharOfTheParseString = parsedString.charAt(indexCurrentCharOfTheParseString);
            //если это операнд пишем в строку
            //если это оператор пишем в стек
            switch (currentCharOfTheParseString){
                case '(':{
                    stringBuilder.append(revPolNot(parsedString.substring(indexCurrentCharOfTheParseString+1)));
                    indexCurrentCharOfTheParseString = parsedString.length();
                    break;
                }
                case ')':{
                    if (!stack.isEmpty()){
                        stringBuilder.append(" ");
                        return stringBuilder.append(readStackInString(stack)).toString();
                    }
                    break;
                }
                case '*':
                case '/': {
                    stringBuilder.append(" ");
                    if (!stack.isEmpty()){
                        if (stack.peek() == ('*') || stack.peek() == ('/')){
                            stringBuilder.append(stack.pop());
                            stringBuilder.append(" ");
                        }
                    }
                    stack.push(currentCharOfTheParseString);
                    break;
                }
                case '+':
                case '-': {
                    stringBuilder.append(" ");
                    if (!stack.isEmpty()){
//                        readStackInString(stringBuilder, stack);
                        stringBuilder.append(readStackInString(stack));
                    }
                    stack.push(currentCharOfTheParseString);
                    break;
                }
                default:{
                    //если буква добавляем в строку
                    stringBuilder.append(currentCharOfTheParseString);
                    break;
                }
            }
            indexCurrentCharOfTheParseString++;
        }
        //опустошаем стек
        stringBuilder.append(" ");
        stringBuilder.append(readStackInString(stack));

        return stringBuilder.toString();
    }

    //поднятие стека, вывод в виде строки
    String readStackInString(Stack<Character> stack){

        StringBuilder s = new StringBuilder();

        if (stack.empty()) return s.toString();

        while (stack.size()!=0){
            s.append(stack.pop());
            s.append(" ");
        }

        return s.toString();
    }
}
