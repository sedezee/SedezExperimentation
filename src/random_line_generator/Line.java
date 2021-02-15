package random_line_generator; 

/**
 * Basic implementation of a mathematical line class
 */
class Line {
    private Coordinate coorZero; 
    private Coordinate coorOne; 

    /**
     * Create a line 
     * @param coorZero starting coordinate 
     * @param coorOne ending coordinate
     */
    public Line (Coordinate coorZero, Coordinate coorOne) {
        this.coorZero = coorZero; 
        this.coorOne = coorOne; 
    }

    /**
     * A copy constructor 
     * @param line the line to copy
     */
    public Line (Line line) {
        this.coorZero = line.getCoorZero(); 
        this.coorOne = line.getCoorOne(); 
    }

    public Coordinate getCoorZero() {
        return coorZero; 
    }

    public Coordinate getCoorOne() {
        return coorOne; 
    }

    /**
     * Add two lines. Does not modify the lines. 
     * @param line line to add
     * @return the sum of line with the current line
     */
    public Line add(Line line) {
        Coordinate coorBegin = coorZero.add(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.add(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    /**
     * Subtract two lines. Does not modify the lines. 
     * @param line the line to subtract
     * @return the difference of line with the current line
     */
    public Line subtract(Line line) {
        Coordinate coorBegin = coorZero.subtract(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.subtract(line.getCoorZero()); 
        return new Line(coorBegin, coorEnd); 
    }

    /**
     * Multiply this line by a scalar. Does not modify the original line. 
     * @param scale the scalar to multiply the line by 
     * @return the product of the line times the scalar 
     */
    public Line multiply(double scale) {
        Coordinate coorBegin = coorZero.multiply(scale); 
        Coordinate coorEnd = coorOne.multiply(scale);
        return new Line(coorBegin, coorEnd);  
    }

    /**
     * Multiply two lines. Does not modify the original lines. 
     * @param line the line to multiply
     * @return the product of line with the current line
     */
    public Line multiply(Line line) {
        Coordinate coorBegin = coorZero.multiply(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.multiply(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    /**
     * Divide two lines. Does not modify the original lines. 
     * @param dividend what to scale the line by 
     * @return the product of the current line and the dividend
     */
    public Line divide(double dividend) {
        Coordinate coorBegin = coorZero.divide(dividend); 
        Coordinate coorEnd = coorOne.divide(dividend); 
        return new Line(coorBegin, coorEnd); 
    }

    /**
     * Divide two lines. Does not modify the original lines. 
     * @param dividend the line to divide by 
     * @return the product of line with the current line
     */
    public Line divide(Line line) {
        Coordinate coorBegin = coorZero.divide(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.divide(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    /**
     * Generate a random line
     * @return a random line
     */
    public static Line random() {
        return new Line (Coordinate.random(), Coordinate.random()); 
    }

    /**
     * Generate a random line within the bounds 
     * @param lowerBoundX the smallest x can be 
     * @param upperBoundX the largest x can be 
     * @param lowerBoundY the smallest y can be 
     * @param upperBoundY the largest y can be
     * @param quarterBounding bound by .25 
     * @return a random line within parameters 
     */
    public static Line random(double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY, boolean quarterBounding) {
        return new Line(Coordinate.random(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY, quarterBounding), Coordinate.random(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY, quarterBounding)); 
    }

    public String toString() {
        return "Line: " + coorZero.toString() + " " + coorOne.toString(); 
    }
}