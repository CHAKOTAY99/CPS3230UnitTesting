package edu.uom;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class StringCalculator {

    public int addSum(String input){
        // First remove empty spaces in the string
        input = input.replaceAll("\\n",",");
        input = input.replaceAll("\\s+", "");
        // Check if empty else if it does not contain a comma
        if(input == ""){
            return 0;
        } else if(input.matches("[0-9]+")) {
            int num1 = Integer.parseInt(input);
            return num1;
        }
        return inputIterator(input);
    }

    public int inputIterator(String input){
        int currentCount = 0, tempCount;
        String negativeString = "";
        String currentString = "";
        int loopCounter = input.length();
        char[] chars = input.toCharArray();
        for(int i = 0; i < loopCounter; i++){
            // Check if it is a ','
            if(isDigit(chars[i])){
                // it is a number
                if(i == loopCounter-1) {
                    currentString = currentString + chars[i];
                    tempCount = Integer.parseInt(currentString);
                    currentCount = currentCount + tempCount;
                } else {
                    currentString = currentString + chars[i];
                }
            } else {
                // Check if it is the - symbol
                if(chars[i] == '-'){
                    int tempLoop = i;
                    while(isDigit(chars[tempLoop]) || chars[tempLoop] == '-'){
                        negativeString = negativeString + chars[tempLoop];
                        tempLoop ++;
                    }
                    negativeString = negativeString + " ";
                }

                // It is a delimiter
                if(i == loopCounter-1){
                    tempCount = Integer.parseInt(currentString);
                    currentCount = currentCount + tempCount;
                } else {
                    if(!currentString.isEmpty()){
                        tempCount = Integer.parseInt(currentString);
                        currentCount = currentCount + tempCount;
                        currentString = "";
                    } else {
                        // do nothing
                    }
                }
            }
        }
        if(negativeString != ""){
            try {
                throw new IllegalAccessException("Negative not allowed: ["+negativeString+"]");
            } catch (IllegalAccessException e) {
                // do nothing
            }
        }
        return currentCount;
    }
}
