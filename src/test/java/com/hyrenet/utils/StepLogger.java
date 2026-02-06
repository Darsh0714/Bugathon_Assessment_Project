package com.hyrenet.utils;
import org.testng.Reporter;
public class StepLogger {

    public static void step(String message){
        System.out.println(">>> STEP: " + message);
        Reporter.log(message, true);
    }
}
