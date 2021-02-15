package calc; 

public class RiemannSum {
    /**
     * Calculate a righthand Riemann sum 
     * @param table the table represented in the form of a 2D array
     * such as (1, 4), (3, 0), (5, 4) as {{0, 4, 5}, {4, 3, 4}}
     * @return the result of the sum 
     */
    public double rightRiemannSum(double[][] table) {
        double total = 0; 
        for(int i = 1; i < table[0].length; i++) {
            double width = table[0][i] - table[0][i-1]; 
            double height = table[1][i]; 
            total += (width * height);  
        }
        return total; 
    }

     /**
     * Calculate a lefthand Riemann sum 
     * @param table the table represented in the form of a 2D array
     * such as (1, 4), (3, 0), (5, 4) as {{0, 4, 5}, {4, 3, 4}}
     * @return the result of the sum 
     */
    public double leftRiemannSum(double[][] table) {
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

      /**
     * Calculate a trapezoidal Riemann sum 
     * @param table the table represented in the form of a 2D array
     * such as (1, 4), (3, 0), (5, 4) as {{0, 4, 5}, {4, 3, 4}}
     * @return the result of the sum 
     */
    public double trapezoidalRiemannSum(double[][] table) {
        double total = 0; 
        for(int i = 1; i < table[0].length; i++) {
            double width = table[0][i] - table[0][i-1]; 
            double average = table[1][i] + table[1][i-1]; 
            average /= 2; 
            total += (width * average); 
        }
        return total; 
    }
    
    /** 
     * Calculates a midpoint Riemann sum 
     * @param table subintervalas must be found first. 
     * Table should be represented in the form of a 2D array 
     * such as (1, 4), (3, 0), (5, 4) as {{1, 3, 5}, {4, 0, 4}}
     * @return the result of the sum 
    */
    public double midpointRiemannSum(double[][] table) {
        double total = 0; 
        double width = table[0][1] - table[0][0];
        for(int i = 0; i < table[0].length; i++) {
            double height = table[1][i];
            total+= (width * height); 
        }
        return total; 
    }

}