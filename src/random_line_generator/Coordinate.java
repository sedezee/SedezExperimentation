package src.random_line_generator;

import java.util.Random;

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

    public Coordinate add(Coordinate coordinate) {
        double x = this.x + coordinate.getX(); ; 
        double y = this.y + coordinate.getY(); 
        return new Coordinate(x, y); 
    }
    
    public Coordinate subtract(Coordinate coordinate) {
        double x = this.x - coordinate.getX(); 
        double y = this.y - coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    public Coordinate multiply(double scale) {
        double x = this.x * scale; 
        double y = this.y * scale; 
        return new Coordinate(x, y); 
    }

    public Coordinate multiply(Coordinate coordinate) {
        double x = this.x * coordinate.getX(); 
        double y = this.y * coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    public Coordinate divide(double dividend) {
        double x = this.x / dividend; 
        double y = this.y / dividend; 
        return new Coordinate(x, y); 
    }

    public Coordinate divide (Coordinate coordinate) {
        double x = this.x / coordinate.getX(); 
        double y = this.y / coordinate.getY(); 
        return new Coordinate(x, y); 
    }

    public static Coordinate random() {
        Random r = new Random(); 
        return new Coordinate(100*r.nextDouble(), 100*r.nextDouble()); 
    }

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