package sealedclasses;

/**
 * Am invalid example implementation of the sealed class, Shape. 
 */

public class Polygon extends Shape {

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }
    
}
