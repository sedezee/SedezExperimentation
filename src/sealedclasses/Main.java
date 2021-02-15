package sealedclasses;

import java.util.ArrayList;

public class Main {
    public static String[] solution(String s) {
        String arr[] = new String[(int)Math.ceil((double)s.length()/2)]; 
        for (int i = 0; i < s.length(); i+=2) { 
            arr[i/2] = s.substring(i, i + 1); 
        }
        
        if (s.length() % 2 != 0) { 
            arr[arr.length -1] = s.substring(s.length() - 1) + "_"; 
            
        }
        
        return arr; 
    }
 
}
