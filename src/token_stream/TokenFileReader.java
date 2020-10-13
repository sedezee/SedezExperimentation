package token_stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenFileReader implements Iterator<String> {
    static {
        System.load("C:\\Users\\sedezeeu\\Documents\\token_stream\\fileIn.dll"); 
    }

    private String path; 
    private int location = 0;
    private int kLength = -1;
    private char[] readFile;   
    private char[] delimiter = null;  

    public TokenFileReader(String path) throws FileNotFoundException {
        this(path == null ? null : new File(path)); 
    }

    public TokenFileReader(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            path = file.getAbsolutePath(); 
        } else {
            throw new FileNotFoundException(); 
        }

        readFile = read0(path); 
        kLength = readFile.length; 
    }

    private native char[] read0(String path) throws FileNotFoundException;
    
    public char[] read() throws FileNotFoundException {
        return read0(path); 
    }

    public boolean hasNext() {
        return kLength > location;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = new char[] {delimiter};  
    }

    public String next() {
        if (delimiter == null) {
            try {
                return Character.toString(readFile[location++]); 
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException(); 
            }
        } else {
            return matchDelim(delimiter); 
        }
        
    }

    private String matchDelim(char c) {
        for (int i = location; i < kLength; i++) {
            if (readFile[i] == c && i != location) {
                String res = new String(readFile, location, i); 
                location = i; 
                return res; 
            }
        }

        return new String(readFile, location, kLength); 
    }

    private String matchDelim(char[] chArr) {
        char c = chArr[0]; 
        boolean match = true; 
        for (int i = location; i < kLength; i++) {
            if (readFile[i] == c && i != location) {
                int j = 0; 
                for (j = 0; j < chArr.length; j++) {
                    if (readFile[i + j] != chArr[j]) {
                        match = false; 
                        break; 
                    }
                }

                if (match) {
                    String res = new String(readFile, i, i + j); 
                    location = i + j; 
                    return res; 
                } else {
                    match = true; 
                }
            }
        }

        return new String(readFile, location, kLength); 
    }

    public String nextLine() {
        return matchDelim('\n'); 
    }

    public String nextWord() {
        return matchDelim(' ');  
    }

}