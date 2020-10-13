package vector;

import java.util.logging.Level;
import java.util.logging.Logger; 
import java.util.ArrayList;

/**
 * An n dimensional, mathematical vector. 
 */

class Vector{
    private double[] vector; 
    private final Logger logger = Logger.getGlobal(); 
    /**
     * Create an instance of a mathematical vector
     * @param args the coordinates of the vector.
     */
    public Vector(double... args) {
        vector = args; 
    }

    /**
     * Get the size of the vector
     * @return the number of parameters in the vector
     */
    public int size() {
        return vector.length; 
    }

    /**
     * Get the coordinate at the given index
     * @param index the index you want the coordinate from. Indexes from 0. 
     * @return the coordinate. 
     */
    public double get(int index) {
        if(index >= this.size())
            return 0; 
        return vector[index]; 
    }

    /**
     * Get the coordinate based on a character value. 
     * @param coordinate The coordinate you want from the vector. Must be x, y, z, or w. 
     * @return the coordinate. 
     */
    public double get(char coordinate) {
        switch(coordinate) {
            case 'x': 
                return vector[0]; 
            case 'y': 
                return vector[1]; 
            case 'z': 
                return vector[2]; 
            case 'w': 
                return vector[3]; 
            default: 
                logger.log(Level.WARNING, "That is not a supported character. Your coordinate was not set."); 
                return 0; 
        }
    }

    /**
     * Change the selected coordinate. 
     * @param index the index of the coordinate you want to change. 
     * @param newVal the value you want to change the coordinate to.
     * @return true if the operation was successful, false if not.  
     */
    public boolean set(int index, double newVal) {
        if(index < size()) {
            vector[index] = newVal; 
            return true; 
        }
        return false; 
    }

    /**
     * Change the select coordinate. 
     * @param coordinate the name of the coordinate you want to change. Can be x, y, z, or w. 
     * @param newVal the value you want to change the coordinate to. 
     * @return true if the operation was successful, false if not. 
     */
    public boolean set(char coordinate, double newVal) {
        switch(coordinate) {
            case 'x': 
                vector[0] = newVal; 
                break; 
            case 'y': 
                vector[1] = newVal; 
                break; 
            case 'z': 
                vector[2] = newVal; 
                break; 
            case 'w': 
                vector[3] = newVal; 
                break;
            default:
                logger.log(Level.WARNING, "That is not a supported character. Your coordinate was not set."); 
                return false;                            
        }
        return true; 
    }

    /**
     * Get the size of the larger vector.  
     * @param vec The vector you want to compare to
     * @return the size of the larger vector. 
     */
    private int extend(Vector vec) {
        return Math.max(vec.size(), size()); 
    }

    /**
     * Get the size of the smaller vector. 
     * @param vec The vector you want to compare to. 
     * @return the size of the smaller vector. 
     */
    private int contract(Vector vec) {
        return Math.min(vec.size(), size()); 
    }

    /**
     * Add two vectors together
     * @param vec the vector you want to add to the current vector
     * @return the reslutant vector. 
     */
    public Vector sum(Vector vec) {
        double[] res = new double[extend(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) + vec.get(i); 
        }
        return new Vector(res); 
    }

    /**
     * Subtract a vector from the current vector. 
     * @param vec the vector you want to subtract. 
     * @return the reslutant vector. 
     */
    public Vector subtract(Vector vec) {
        double[] res = new double[extend(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) - vec.get(i); 
        } 
        return new Vector(res); 
    }

    /**
     * Multiply a vectory by a number.  
     * @param multiplier the number you want to multiply by. 
     * @return the resultant vector
     */
    public Vector multiply(double multiplier) {
        double[] res = new double[size()]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) * multiplier; 
        }
        return new Vector(res); 
    }

    /**
     * Multiply two vectors together.
     * @param vec the vector you want to multiply by. 
     * @return the resultant vector. 
     */
    public Vector multiply(Vector vec) {
        double[] res = new double[contract(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) * vec.get(i); 
        }
        return new Vector(res); 
    }

    /**
     * Divide a vector by a number. 
     * @param dividend the number to divide by.
     * @return the relutant vector. 
     */
    public Vector divide(double dividend) {
        return multiply(1/dividend); 
    }

    /**
     * Divide a vector by another vector.
     * @param vec the vector to divide by. 
     * @return the reslutant vector. 
     */
    public Vector divide (Vector vec) {
        double[] res = new double[contract(vec)]; 
        for(int i = 0; i < res.length; i++) {
            if(vec.get(i) == 0)
                throw new ArithmeticException("Dividing by zero in vector."); 
            res[i] = get(i) / vec.get(i); 
        }
        return new Vector(res); 
    }

    /**
     * Use the dot product on two vectors. 
     * @param vec the vector to perform dot product with. 
     * @return the resultant vector. 
     */
    public double dot(Vector vec) {
        int res = 0; 
        for(int i = 0; i < contract(vec); i++) {
            res+= get(i) * vec.get(i); 
        }

        return res; 
    }

    /**
     * Transform a vector into an array. 
     * @return the array. 
     */
    public double[] toArray() {
        return vector.clone(); 
    }

    /**
     * Transform a vector into an array list. 
     * @return the resultant array list. 
     */
    public ArrayList<Double> toArrayList() {
        ArrayList<Double> res = new ArrayList<Double>(); 
        for(double i : vector) {
            res.add(i); 
        }
        return res; 
    }
    
    public String toString() {
        if(size() == 0)
            return "Vector()"; 
        
        StringBuilder out = new StringBuilder(); 
        out.append("Vector("); 
        for(double b : vector) {
            out.append(b); 
            out.append(", "); 
        }
        out.replace(out.length()-2, out.length()-1, ")");
        return out.toString(); 
    }
}