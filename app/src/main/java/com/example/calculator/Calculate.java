package com.example.calculator;

import java.util.Stack;

/**
 *  производит вычисление выражения переданного в строке
 * */
public class Calculate {


    public Double getResult(String expression){
        return calc(revPolNot(expression));
    }

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
    String revPolNot(String str){
        Stack<Character> stack = new Stack<>();
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        char op ;

        while (index < str.length()){
            //считали символ строки
            op = str.charAt(index);
            //если это операнд пишем в строку
            //если это оператор пишем в стек
            switch (op){
                case '*':
                case '/': {
                    stringBuilder.append(" ");
                    if (!stack.isEmpty()){
                        if (stack.peek() == ('*') || stack.peek() == ('/')){
                            stringBuilder.append(stack.pop());
                            stringBuilder.append(" ");
                        }
                    }
                    stack.push(op);
                    break;
                }
                case '+':
                case '-': {
                    stringBuilder.append(" ");
                    if (!stack.isEmpty()){
                        readStackInString(stringBuilder, stack);
                    }
                    stack.push(op);
                    break;
                }
                default:{
                    //если буква добавляем в строку
                    stringBuilder.append(op);
                    break;
                }

            }
            index++;
        }
        //опустошаем стек
        stringBuilder.append(" ");
        readStackInString(stringBuilder, stack);

        return stringBuilder.toString();
    }
    void readStackInString(StringBuilder stringBuilder, Stack<Character> stack){
        if (stack.empty()) return;
        while (stack.size()!=0){
            stringBuilder.append(stack.pop());
            stringBuilder.append(" ");
        }
    }


}
