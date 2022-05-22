package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputOutput {

    public static void print(String message){
        System.out.println(message);
    }

    public static String getInput (){
        try {  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
