package org.example;

import org.example.in.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Вход в программу
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            controller.start(bufferedReader);
        }
        catch (IOException e){
            e.printStackTrace();
        }




    }
}