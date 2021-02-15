package tokenstream;

import java.io.FileNotFoundException;

/**
 * Thanks to Baeldung's JNI guide (https://www.baeldung.com/jni) and Jakob
 * Wiesmore, RIT (for the aid with the C++, mingw, and dll files.)
 */
public class Main {
    public static void main(String... args) throws FileNotFoundException {
        TokenFileReader tis = new TokenFileReader("Demo.txt"); 
        tis.wordTokens().forEach(System.out::print);    
    } 
}