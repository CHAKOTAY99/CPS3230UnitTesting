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

    public String getTimedMessage(TimeProvider timeProvider){
        int segment = timeProvider.getTimeSegment();

        switch(segment) {
            case TimeProvider.Morning : return "Hello World!! Good Morning!";
            case TimeProvider.Afternoon : return "Hello World!! Good Afternoon!";
            case TimeProvider.Evening : return "Hello World!! Good Evening!";
            default : return "Hello World!!";
        }
    }
}
