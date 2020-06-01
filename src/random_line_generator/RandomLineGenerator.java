package src.random_line_generator; 

class RandomLineGenerator {
    private double xLowerBound;
    private double xUpperBound; 
    private double yLowerBound; 
    private double yUpperBound; 
    private boolean quarterBound; 
    private double[] bounds = new double[4]; 

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

    private void quarterBound(double[] bounds) {
        for(int i = 0; i < bounds.length; i++) {
            if(bounds[i] % .25 != 0) {
                bounds[i] = Math.ceil(bounds[i] * 4) / 4; 
            }
        }   
        setFromList(); 
    }

    public Line  generate() {
        return Line.random(xLowerBound, xUpperBound, yLowerBound, yUpperBound, quarterBound); 
    }
    
}