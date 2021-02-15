package sealedclasses;

import java.util.Arrays;
import java.util.List;

/**
 *  An abstract sealed class in Java. 
    Designed so that only the specified classes can be extended.
    Verifies whether a class can be extended via class name. 
 */

public abstract class SealedClass {
    protected List<String> classes; 

    /**
     * Super must be called for all classes that extend SealedClass. 
     * @param arr the classes that are allowed to be generated from this class
     * @throws SealedClassException.java if the class is not one of the 'allowed' classes 
     */
    @SuppressWarnings("unchecked")
    protected SealedClass(String[] arr) {
        boolean pass = false; 
        Class<SealedClass> clazz = (Class<SealedClass>) this.getClass();
        
        classes  = Arrays.asList(arr); 
        
        // verify that the class exists within the allowed classes list
        for (String clasz : getAllowedClasses()) {
            if (clasz.equalsIgnoreCase(clazz.getSimpleName())) {
                pass = true; 
            }
        } 
        
        // if the class does not exist, throw a runtime exception
        if (!pass) {
            throw new SealedClassException("Class did not match the accepted extensions."); 
        }
    }

    private List<String> getAllowedClasses() { 
        return classes; 
    }
}
