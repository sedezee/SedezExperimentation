package sealed_classes;

public class Main {
    public static void main(String... args) {
        Rectangle rectangle = new Rectangle(); // This will run without problem 
        
        Polygon polygon = new Polygon(); // This will throw an error 
    }
}
