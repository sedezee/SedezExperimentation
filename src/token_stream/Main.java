package token_stream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.CharBuffer; 

/**
 * Thanks to Baeldung's JNI guide (https://www.baeldung.com/jni)
 * and Jakob Wiesmore, RIT (for the aid with the C++, mingw, and dll files.)
 */
public class Main {
    public static void main(String... args) throws FileNotFoundException {
        TokenFileReader tis = new TokenFileReader("C:\\Users\\sedezeeu\\Documents\\token_stream\\test.txt"); 
    }
}