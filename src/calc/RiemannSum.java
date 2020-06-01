package src.calc; 

public class RiemannSum {
    /* RIEMAN MIDPOINT SUM
    subintervals need to be found first. 
    i.e. riemann midpoint sum from 0-6, with three subintervals 
    will use the points (1, 4), (3, 0), (5, 4)
    with a table structure like 
    {{1, 3, 5}, {4, 0, 4}}

    EVERY OTHER SUM
    punch in the numbers. 
    (0, 4), (4, 3), (5, 4) goes in as such: 
    {{0, 4, 5}, {4, 3, 4}}
    */
    
    double[][] table; 
    public RiemannSum(double[][] table) {
        this.table = table; 
    }

    public double rightRiemannSum() {
        double total = 0; 
        for(int i = 1; i < table[0].length; i++) {
            double width = table[0][i] - table[0][i-1]; 
            double height = table[1][i]; 
            total += (width * height);  
        }
        return total; 
    }

    public double leftRiemannSum() {
        double total = 0; 
        String resStr = ""; 
        for(int i = 1; i < table[0].length; i++) {
            double width = table[0][i] - table[0][i-1];  
            double height = table[1][i-1];
            resStr += " (" + width + " * " + height + ") +"; 
            total += (width * height); 
        }
        System.out.println(resStr.substring(0, resStr.length() - 2));
        return total; 
    }

    public double trapezoidalRiemannSum() {
        double total = 0; 
        for(int i = 1; i < table[0].length; i++) {
            double width = table[0][i] - table[0][i-1]; 
            double average = table[1][i] + table[1][i-1]; 
            average /= 2; 
            total += (width * average); 
        }
        return total; 
    }

    public double midpointRiemannSum() {
        double total = 0; 
        double width = table[0][1] - table[0][0];
        for(int i = 0; i < table[0].length; i++) {
            double height = table[1][i];
            total+= (width * height); 
        }
        return total; 
    }

}