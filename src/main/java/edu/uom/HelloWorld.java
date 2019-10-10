package edu.uom;

public class HelloWorld {
    public String getMessage(){
        return "Hello World!!";
    }

    public String getMessage(String name){
        if(name == null || name.length() == 0){
            return getMessage();
        } else {
            return "Hello " + name + "!";
        }
    }
}
