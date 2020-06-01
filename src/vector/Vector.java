package src.vector;

import java.util.ArrayList;

/**
 * An n dimensional, mathematical vector. 
 */

class Vector{
    private double[] vector; 
    public Vector(double... args) {
        vector = args; 
    }

    public int size() {
        return vector.length; 
    }

    public double get(int index) {
        if(index >= this.size())
            return 0; 
        return vector[index]; 
    }

    public double get(char coordinate) {
        if(coordinate == 'x' && this.size() >= 1)
            return vector[0]; 
        else if (coordinate == 'y' && this.size() >= 2)
            return vector[1]; 

        switch(coordinate) {
            case 'x': 
                return vector[0]; 
            case 'y': 
                return vector[1]; 
            case 'z': 
                return vector[2]; 
            default: 
                return 0; 
        }
    }

    public void set(int index, int newVal) {
        if(index < size()) 
            vector[index] = newVal; 
    }

    public void set(char coordinate, int newVal) {
        if(size() < 3){
            System.out.println("Sorry, setting via x, y, z is only valid for vectors that have three or more coordinates."); 
            return; 
        }

        switch(coordinate) {
            case 'x': 
                vector[0] = newVal; 
            case 'y': 
                vector[1] = newVal; 
            case 'z': 
                vector[2] = newVal; 
        }
    }

    private int extend(Vector vec) {
        return Math.max(vec.size(), size()); 
    }

    private int contract(Vector vec) {
        return Math.min(vec.size(), size()); 
    }

    public Vector sum(Vector vec) {
        double[] res = new double[extend(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) + vec.get(i); 
        }
        return new Vector(res); 
    }

    public Vector subtract(Vector vec) {
        double[] res = new double[extend(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) - vec.get(i); 
        } 
        return new Vector(res); 
    }

    public Vector multiply(double multiplier) {
        double[] res = new double[size()]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) * multiplier; 
        }
        return new Vector(res); 
    }

    public Vector multiply(Vector vec) {
        double[] res = new double[contract(vec)]; 
        for(int i = 0; i < res.length; i++) {
            res[i] = get(i) * vec.get(i); 
        }
        return new Vector(res); 
    }

    public Vector divide(double dividend) {
        return multiply(1/dividend); 
    }

    public Vector divide (Vector vec) {
        double[] res = new double[contract(vec)]; 
        for(int i = 0; i < res.length; i++) {
            if(vec.get(i) == 0)
                throw new ArithmeticException("Dividing by zero in vector."); 
            res[i] = get(i) / vec.get(i); 
        }
        return new Vector(res); 
    }

    public double dot(Vector vec) {
        int res = 0; 
        for(int i = 0; i < contract(vec); i++) {
            res+= get(i) * vec.get(i); 
        }

        return res; 
    }

    public double[] toArray() {
        return vector.clone(); 
    }

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