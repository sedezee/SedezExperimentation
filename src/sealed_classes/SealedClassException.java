package sealed_classes; 

/**
 * Specialized SealedClassException designed for use with SealedClass.java.
 */
public class SealedClassException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SealedClassException(String message) {
        super(message); 
    }
}