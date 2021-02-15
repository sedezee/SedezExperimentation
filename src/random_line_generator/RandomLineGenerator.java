package random_line_generator; 

class RandomLineGenerator {
    private double xLowerBound; 
    private double xUpperBound; 
    private double yLowerBound; 
    private double yUpperBound; 
    private boolean quarterBound; 
    private double[] bounds = new double[4]; 

    /**
     * Create a random line generator 
     * @param xLowerBound the lower x coordinate bound 
     * @param xUpperBound the upper x coordinate bound 
     * @param yLowerBound the lower y coordinate bound 
     * @param yUpperBound the upper y coordinate bound 
     * @param quarterBound whether or not to round bounds to increments of .25
     */
    public RandomLineGenerator(double xLowerBound, double xUpperBound, double yLowerBound, double yUpperBound, boolean quarterBound) {
        this.xLowerBound = xLowerBound; 
        this.xUpperBound = xUpperBound; 
        this.yLowerBound = yLowerBound; 
        this.yUpperBound = yUpperBound; 

        this.quarterBound = quarterBound; 

        if(quarterBound) {
            bounds = new double[] {xLowerBound, xUpperBound, yLowerBound, yUpperBound};
            quarterBound(bounds); 
        }
    }

    private void setFromList() {
        xLowerBound = bounds[0]; 
        xUpperBound = bounds[1]; 
        yLowerBound = bounds[2]; 
        yUpperBound = bounds[3]; 
    }

    /**
     * Change lines to multiples of .25
     * @param bounds the bounds to change
     */
    private void quarterBound(double[] bounds) {
        for(int i = 0; i < bounds.length; i++) {
            if(bounds[i] % .25 != 0) {
                bounds[i] = Math.ceil(bounds[i] * 4) / 4; 
            }
        }   
        setFromList(); 
    }

    /**
     * Generate a line within the bounds
     * @return randomly generated line
     */
    public Line  generate() {
        return Line.random(xLowerBound, xUpperBound, yLowerBound, yUpperBound, quarterBound); 
    }
    
}