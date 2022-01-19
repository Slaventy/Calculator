package com.example.calculator;

import java.util.Stack;

/**
 *  производит вычисление выражения переданного в строке
 * */
public class Calculate {


    public String getResult(String expression){
        if (expression.isEmpty()){
            return "0";
        }else {
            return String.valueOf(calc(revPolNot(expression)));
        }
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

        StringBuilder stringBuilder = new StringBuilder();
        char op ;
        int index = 0;
        while (index < str.length()){
            //считали символ строки
            op = str.charAt(index);
            //если это операнд пишем в строку
            //если это оператор пишем в стек
            switch (op){
                case '(':{
                    stringBuilder.append(revPolNot(str.substring(index+1)));
                    index = str.length();
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
                    stack.push(op);
                    break;
                }
                case '+':
                case '-': {
                    stringBuilder.append(" ");
                    if (!stack.isEmpty()){
//                        readStackInString(stringBuilder, stack);
                        stringBuilder.append(readStackInString(stack));
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
        stringBuilder.append(readStackInString(stack));

        return stringBuilder.toString();
    }

    String readStackInString(Stack<Character> stack){
        StringBuilder s = new StringBuilder("");
        if (stack.empty()) return s.toString();

        while (stack.size()!=0){
            s.append(stack.pop());

            s.append(" ");
        }
        return s.toString();
    }


}
