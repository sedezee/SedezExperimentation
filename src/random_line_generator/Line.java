package random_line_generator; 

class Line {
    private Coordinate coorZero; 
    private Coordinate coorOne; 

    public Line (Coordinate coorZero, Coordinate coorOne) {
        this.coorZero = coorZero; 
        this.coorOne = coorOne; 
    }

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

    public Line add(Line line) {
        Coordinate coorBegin = coorZero.add(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.add(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    public Line subtract(Line line) {
        Coordinate coorBegin = coorZero.subtract(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.subtract(line.getCoorZero()); 
        return new Line(coorBegin, coorEnd); 
    }

    public Line multiply(double scale) {
        Coordinate coorBegin = coorZero.multiply(scale); 
        Coordinate coorEnd = coorOne.multiply(scale);
        return new Line(coorBegin, coorEnd);  
    }

    public Line multiply(Line line) {
        Coordinate coorBegin = coorZero.multiply(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.multiply(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    public Line divide(double dividend) {
        Coordinate coorBegin = coorZero.divide(dividend); 
        Coordinate coorEnd = coorOne.divide(dividend); 
        return new Line(coorBegin, coorEnd); 
    }

    public Line divide(Line line) {
        Coordinate coorBegin = coorZero.divide(line.getCoorZero()); 
        Coordinate coorEnd = coorOne.divide(line.getCoorOne()); 
        return new Line(coorBegin, coorEnd); 
    }

    public static Line random() {
        return new Line (Coordinate.random(), Coordinate.random()); 
    }

    public static Line random(double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY, boolean quarterBounding) {
        return new Line(Coordinate.random(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY, quarterBounding), Coordinate.random(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY, quarterBounding)); 
    }

    public static Line random(boolean quarterBounding, double... bounds) {
        return new Line(Coordinate.random(bounds[0], bounds[1], bounds[2], bounds[3], quarterBounding), Coordinate.random(bounds[0], bounds[1], bounds[2], bounds[3], quarterBounding)); 
    }

    public String toString() {
        return "Line: " + coorZero.toString() + " " + coorOne.toString(); 
    }
}