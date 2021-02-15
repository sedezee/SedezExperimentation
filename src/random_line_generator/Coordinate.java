package random_line_generator;

import java.util.Random;

/**
 * Basic mathematic implementation of a coordinate. 
 */
class Coordinate {
    private double x;
    private double y; 

    public Coordinate(double x, double y) {
        this.x = x; 
        this.y = y; 
    }

    public double getX() {
        return x; 
    }

    public double getY() {
        return y; 
    }

    public void setX(double x) {
        this.x = x; 
    }

    public void setY(double y) {
        this.y = y; 
    }

    /**
     * Adds two coordinates without modifying the originals. 
     * @param coordinate coordinate to add to this object
     * @return sum of the two coordinates
     */
    public Coordinate add(Coordinate coordinate) {
        double x = this.x + coordinate.getX(); ; 
        double y = this.y + coordinate.getY(); 
        return new Coordinate(x, y); 
    }
    
    /**
     * Subtracts two coordinates without modifying the originals. 
     * @param coordinate coordinate to subtract from this object
     * @return difference of the two coordinates
     */
    public Coordinate subtract(Coordinate coordinate) {
        double x = this.x - coordinate.getX(); 
        double y = this.y - coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    /**
     * Multiplies the current coordinate by a scalar without modifying it 
     * @param scale the scalar to multiply by 
     * @return the current coordinate multiplied by the scalar
     */
    public Coordinate multiply(double scale) {
        double x = this.x * scale; 
        double y = this.y * scale; 
        return new Coordinate(x, y); 
    }

    /**
     * Multiplies the coordinate against the current without modifying either. 
     * @param coordinate the coordinate to multiply by 
     * @return the product of the two coordinates
     */
    public Coordinate multiply(Coordinate coordinate) {
        double x = this.x * coordinate.getX(); 
        double y = this.y * coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    /**
     * Divies the current coordinate by the dividend without modifying the original. 
     * @param dividend the number to divide by 
     * @return the current coordinate divided by the dividend
     */
    public Coordinate divide(double dividend) {
        double x = this.x / dividend; 
        double y = this.y / dividend; 
        return new Coordinate(x, y); 
    }

    /**
     * Divides the two coordinates without modifying the originals
     * @param coordinate the coordinate to divide by 
     * @return the product of the coordinates
     */
    public Coordinate divide (Coordinate coordinate) {
        double x = this.x / coordinate.getX(); 
        double y = this.y / coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    /**
     * Genearate a random coordinate 
     * @return a random coordinate
     */
    public static Coordinate random() {
        Random r = new Random(); 
        return new Coordinate(100*r.nextDouble(), 100*r.nextDouble()); 
    }

    /**
     * Generates a random coordinate within the bounds
     * @param lowerBoundX the smallest possible x
     * @param upperBoundX the largest possible x
     * @param lowerBoundY the smallest possible y 
     * @param upperBoundY the largest possible y 
     * @param quarterBounding truncate at .25
     * @return a random coordinate within the bounds
     */
    public static Coordinate random (double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY, boolean quarterBounding) {
        Random r = new Random(); 
        double randomX = lowerBoundX + (upperBoundX - lowerBoundX) * r.nextDouble(); 
        double randomY = lowerBoundY + (upperBoundY - lowerBoundY) * r.nextDouble(); 
        if(quarterBounding) {
            randomX = Math.ceil(randomX * 4) /4; 
            randomY = Math.ceil(randomY * 4) /4; 
        }
        return new Coordinate(randomX, randomY); 
    }

    public String toString() {
        return "(" + x + ", " + y + ")"; 
    }
}