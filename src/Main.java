

public class Main {

    public static void main(String[] args) {
        final int rows = 11;
        final int cols = rows;
        int[][] multiTable = new int[rows][cols];
        for (int r = 1; r < 11; r++) {
            for (int c = 1; c < 11; c++) {
                multiTable[r][c] = r * c;
                
                if (multiTable[r][c] <= 9) {
                    System.out.print(multiTable[r][c] + "   "); 
                } else {
                    System.out.print(multiTable[r][c] + "  ");
                }
                
            }
            System.out.println();
        }
    }
}