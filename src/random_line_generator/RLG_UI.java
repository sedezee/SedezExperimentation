package random_line_generator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import user_interface.UIRegister; 

/**
 * An class largely used to show off uiRegister. 
 * Generates lines based on entered parameters. 
 */
public class RLG_UI {
    private UIRegister uiRegister; 
    private Scanner scanner; 
    private ArrayList<Line> lineList = new ArrayList<>();
    
    public RLG_UI() {
        uiRegister = new UIRegister(); 
        scanner = new Scanner(System.in); 
    }

    private void exitCheck(String exit) {
        if(exit.equalsIgnoreCase("EXIT")){
            scanner.close(); 
            throw new CancellationException(); 
        }
    }

    private double strToDouble(String str) {
        double internalCat = 0; 
        try {  
            internalCat = Double.parseDouble(str); 
        }  catch (Exception e) {
            if(str.length() == 1)
                internalCat = Character.getNumericValue(str.charAt(0)); 
            else 
                System.out.println("Please enter a number or a single character."); 
        } 
        return internalCat; 
    }

    private double[] bounds(Scanner scanner) {
        System.out.println("LOWER X BOUND:"); 
        String boundStr = scanner.nextLine(); 
        exitCheck(boundStr); 
        double lXBound = strToDouble(boundStr); 

        System.out.println("UPPER X BOUND:"); 
        boundStr = scanner.nextLine(); 
        exitCheck(boundStr); 
        double uXBound = strToDouble(boundStr); 
        
        System.out.println("LOWER Y BOUND:"); 
        boundStr = scanner.nextLine(); 
        exitCheck(boundStr); 
        double lYBound = strToDouble(boundStr); 

        System.out.println("UPPER Y BOUND:"); 
        boundStr = scanner.nextLine(); 
        exitCheck(boundStr); 
        double uYBound = strToDouble(boundStr); 
        
        return new double[] {lXBound, uXBound, lYBound, uYBound}; 
    }

    private void generateLines(Scanner scanner) {
        double[] boundArr = bounds(scanner); 
        System.out.println("How many lines would you like to GENERATE?"); 
        String generateStr = scanner.nextLine(); 
        exitCheck(generateStr); 
        int lineNum = (int)strToDouble(generateStr); 
        boolean save = false; 
        while (true){
            System.out.println("Would you like to SAVE your lines? YES or NO."); 
            generateStr = scanner.nextLine(); 
            exitCheck(generateStr); 
            generateStr = generateStr.toUpperCase(); 
            if(generateStr.equals("YES")) {
                save = true; 
                break; 
            } else if (generateStr.equals("NO")) {
                break;
            }
        }
        for(int i = 0; i < lineNum; i++) {
            Line l = Line.random(boundArr[0], boundArr[1], boundArr[2], boundArr[3], true);
            System.out.println(l.toString()); 
            if (save) 
                lineList.add(l); 
        }
    }

    private void viewLines(Scanner scanner) {
        for (Line l : lineList) {
            System.out.println(l.toString()); 
        }
    }

    private void createLine(Scanner scanner) {
        System.out.println("NOTE: This will automatically save lines to your list."); 
        System.out.println("Quarter bounding? YES or NO?"); 
        String qb = scanner.nextLine().toUpperCase(); 
        exitCheck(qb); 
        boolean quarterBounding = false; 
        if(qb.equals("YES"))
            quarterBounding = true; 
        while(true) {
            System.out.println("RANDOM or INPUT parameters?"); 
            String lineStr = scanner.nextLine().toUpperCase(); 
            exitCheck(lineStr); 
            if(lineStr.equals("RANDOM")) {
                double[] arrBounds = bounds(scanner); 
                Line l = Line.random(arrBounds[0], arrBounds[1], arrBounds[2], arrBounds[3], quarterBounding); 
                System.out.println(l); 
                lineList.add(l); 
                break;
            } else if (lineStr.equals("INPUT")) {
                System.out.println("X1:"); 
                double x1 = scanner.nextDouble(); 
                System.out.println("Y1:"); 
                double y1 = scanner.nextDouble(); 
                System.out.println("X2:"); 
                double x2 = scanner.nextDouble(); 
                System.out.println("Y2:"); 
                double y2 = scanner.nextDouble(); 
                Line l = new Line(new Coordinate(x1, y1), new Coordinate(x2, y2)); 
                System.out.println(l.toString()); 
                lineList.add(l); 
            }
        }
    }

    public void run() {
        uiRegister.register(this::generateLines, "Generate Lines", "Generates lines."); 
        uiRegister.register(this::viewLines, "View Lines", "Allows you to view all of your lines."); 
        uiRegister.register(this::createLine, "Create lines", "Creates a line.");
        uiRegister.run(); 
    }
}