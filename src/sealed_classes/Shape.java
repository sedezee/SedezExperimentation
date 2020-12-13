package sealed_classes;

/**
 * Basic implementation of a shape class for demonstrating the 
 * SealedClass capability. 
 */

public abstract class Shape extends SealedClass {
    // the classes that can extend Shape
    static String[] allowedClasses = {"square", "circle", "rectangle", "triangle"}; 

    protected Shape() {
        super(allowedClasses); 
    }

    public abstract double getArea(); 

    public abstract double getPerimeter(); 
}
