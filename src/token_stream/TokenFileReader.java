package token_stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TokenFileReader implements Iterator<String> {
    static {
        System.load(System.getProperty("user.dir") + "\\fileIn.dll"); 
    }

    private String path; //path to the file. 
    private int location = 0; //location in the character array. 
    private int kLength = -1; //length of the character array. 
    private char[] readFile;  //the contents of the file. 
    private char[] delimiter = null;  //the delimeter to split by

    /**
     * Creates a new TokenFileReader, given the path of the file to read from. 
     * @param path the path to the file to read. 
     * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or cannot be opened. 
     */
    public TokenFileReader(String path) throws FileNotFoundException {
        this(path == null ? null : new File(path)); 
    }

    /**
     * Creates a new TokenFileReader, given the file to read from. 
     * @param file the file to read from.  
     * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or cannot be opened. 
     */
    public TokenFileReader(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            path = file.getAbsolutePath(); 
        } else {
            throw new FileNotFoundException(); 
        }

        readFile = read0(path); 
        kLength = readFile.length; 
    }

    /**
     * Native, low level reading of the file. Interfaces with C++. 
     * @param path the path of the file to read 
     * @return the read in file 
     * @throws FileNotFoundException if the file cannot be found, or read
     */
    private native char[] read0(String path) throws FileNotFoundException;
    
    /**
     * Read the file into a char array. File must be reread if it is changed. 
     * @return the contents of the file. 
     * @throws FileNotFoundException if the file cannot be found, or read. 
     */
    public char[] read() throws FileNotFoundException {
        return read0(path); 
    }

    /**
     * Return whether or not the file has more to be read. 
     * @return true if there is more to be read, false if there is not. 
     */
    public boolean hasNext() {
        return kLength > location;
    }

    /**
     * Reset the location of the file to zero. Will not reread the file. 
     */
    public void reset() {
        location = 0; 
    }

    /**
     * Set the delimiter of the file to a single char. 
     * @param delimiter the desired delimiter. 
     */
    public void setDelimiter(char delimiter) {
        this.delimiter = new char[] {delimiter};  
    }

    /**
     * Set the delimiter of the file to a string. 
     * @param delimiter the desired String for the delimeter. 
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter.toCharArray(); 
    }

    /**
     * Move onto the next token. Uses the delimiter to determine what the next token will be. 
     * @return the token. 
     */
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

    /**
     * Return the string matching the inputted delimeter. 
     * @param c the delimeter.
     * @return the next token. 
     */
    private String matchDelim(char c) { 
        return matchDelim(new char[] {c}); 
    }

    /** 
     * Return the string matching the inputted delimeter. 
     * @param chArr the array of delimiter characters. 
     * @return the next token. 
     */
    private String matchDelim(char[] chArr) {
        char c = chArr[0]; 
        boolean match = true; 
        for (int i = location; i < kLength; i++) {
            if (readFile[i] == c && i != location) {
                match = true; 
                int j = 0; 
                for (j = 0; j < chArr.length; j++) {
                    if (readFile[i + j] != chArr[j]) {
                        match = false; 
                        break; 
                    }
                }

                if (match) {  
                    String res = new String(readFile, location, i - location); 
                    location = i; 
                    return res; 
                } 
            }
        }

        int temp = location; 
        location = kLength; 
        return new String(readFile, temp, kLength - 1 - temp); 
    }

    /**
     * Get the next line in the filereader. 
     * @return the next line
     */
    public String nextLine() {
        return matchDelim('\n'); 
    }

    /**
     * Get the next wprd in the filereader. 
     * @return the next word. 
     */
    public String nextWord() {
        int i; 
        for (i = location; i < kLength; i++) {
            if ((readFile[i] == ' ' || readFile[i] == '\n') && (i != location && i != location - 1)) {
                String res = new String(readFile, location, i - location); 
                location = i;
                return res; 
            }
            
        }
        
        int temp = location; 
        location = kLength; 
        return new String(readFile, temp, kLength - temp - 1); 
    }

    /**
     * Get a stream from the set delimiter. 
     * @return a Stream<String> of the char[] array. 
     */
    public Stream<String> tokens() {
        List<String> tokenIn = new ArrayList<>(); 

        while (hasNext()) {
            tokenIn.add(next()); 
        }

        TokenSpliterator spliterator = new TokenSpliterator(tokenIn); 
        return StreamSupport.stream(spliterator, true);
    }

    /**
     * Get a stream of every line. 
     * @return a Stream<String> of the char[] array's lines. 
     */
    public Stream<String> lineTokens() {
        List<String> tokenIn = new ArrayList<>(); 

        while (hasNext()) {
            tokenIn.add(nextLine()); 
        }

        TokenSpliterator spliterator = new TokenSpliterator(tokenIn); 
        return StreamSupport.stream(spliterator, true);
    }

    /**
     * Get a stream of every word. 
     * @return a Stream<String> of the char[] array's words. 
     */
    public Stream<String> wordTokens() {
        List<String> tokenIn = new ArrayList<>(); 
        while (hasNext()) {
            tokenIn.add(nextWord()); 
        }
        TokenSpliterator spliterator = new TokenSpliterator(tokenIn); 
        return StreamSupport.stream(spliterator, true); 
    }

    private class TokenSpliterator implements Spliterator<String> {
        private final List<String> list;
        private int location = 0;  

        public TokenSpliterator(List<String> list) {
            this.list = list; 
        }

        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            action.accept(list.get(location++)); 
            return location < list.size(); 
        }

        @Override
        public Spliterator<String> trySplit() {
            //unimplemented because there is no valid usecase in this application. 
            return null; 
        }

        @Override
        public long estimateSize() {
            return (long)list.size() - location; 
        }

        @Override
        public int characteristics() {
            return CONCURRENT; 
        }


    }

}