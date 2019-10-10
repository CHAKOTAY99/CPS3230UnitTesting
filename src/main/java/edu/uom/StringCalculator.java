package edu.uom;

public class StringCalculator {

    public int addSum(String input){
        // First remove empty spaces in the string
        input = input.replaceAll("\\s+", "");
        // Check if empty else if it does not contain a comma
        if(input == ""){
            return 0;
        } else if(!input.contains(",")) {
            int num1 = Integer.parseInt(input);
            return num1;
        }
        return inputIterator(input);
    }

    public int inputIterator(String input){
        int currentCount = 0, tempCount;
        String currentString = "";
        int loopCounter = input.length();
        char[] chars = input.toCharArray();
        for(int i = 0; i < loopCounter; i++){
            if(chars[i] != ','){
                // it is a number
                if(i == loopCounter-1) {
                    currentString = currentString + chars[i];
                    tempCount = Integer.parseInt(currentString);
                    currentCount = currentCount + tempCount;
                } else {
                    currentString = currentString + chars[i];
                }
            } else {
                // It is a ','
                if(i == loopCounter-1){
                    tempCount = Integer.parseInt(currentString);
                    currentCount = currentCount + tempCount;
                } else {
                    if(currentString != ""){
                        tempCount = Integer.parseInt(currentString);
                        currentCount = currentCount + tempCount;
                        currentString = "";
                    } else {
                        // do nothing
                    }
                }
            }
        }
        return currentCount;
    }
}
