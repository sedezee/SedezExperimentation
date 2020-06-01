package src.user_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;


public class UIRegister {
    /*
    Used to quickly create a UI. Usage can be seen in RLG_UI. 
    */ 

    private List<UIObject> uiObjList; 
    private Scanner scanner; 

    public UIRegister() {
        uiObjList = new ArrayList<UIObject>(); 
        scanner = new Scanner(System.in); 
    }

    /**
     * @param func
     *      The function to run. Must take a scanner as the input. 
     * @param name
     *      The name of the function to run. This is what it'll be called by in the menu. 
     * @param description
     *      The description of the function that will be run. This will show up in help. 
     */
    public void register(Consumer<Scanner> func, String name, String description) {
        uiObjList.add(new UIObject(name, description, func)); 
    }

    public void register (Consumer<Scanner> func, String name) {
        uiObjList.add(new UIObject(name, "", func)); 
    }

    private UIObject getMethod(String name) {
        name = name.toUpperCase(); 
        for (UIObject uiMethod : uiObjList) {
            if (uiMethod.getName().equals(name)) {
                return uiMethod; 
            }
        }
        return null; 
    }

    private boolean runMethod(String name) {
        try {
            this.getMethod(name).getFunc().accept(scanner); 
            return true; 
        } catch (NullPointerException e) {
            return false; 
        }
    }

    private String[] getDescriptions() {
        String[] desc = new String[uiObjList.size()]; 
        int i = 0; 
        for(UIObject uiMethod : uiObjList) {
            desc[i] = uiMethod.toString(); 
            i++; 
        }
        return desc; 
    }

    private void help(String optionsName) {
        while (true) {
            System.out.println("What would you like help with? " + optionsName); 
            System.out.println("To return to the main page, type MENU."); 

            String choice = scanner.nextLine(); 
            choice = choice.toUpperCase(); 

            if (choice.equals("MENU")) {
                break; 
            } else if (choice.equals("ALL")) {
                for (String expl : this.getDescriptions()) {
                    System.out.println(expl); 
                }
            }

            try {
                System.out.println(this.getMethod(choice).toString()); 
            } catch(NullPointerException e) {
                System.out.println("Sorry, that command doesn't exist."); 
            }
        }
    }

    // launches the user interface. 
    public void run() {
        String options = "Your options are: ";
        for(UIObject uiMethod : uiObjList) {
            options += uiMethod.getName(); 
            options += ", ";
        }
        options = options.substring(0, options.length() -2) + "."; 
        System.out.println(options); 
        System.out.println("To see the options or get an explanation on their purpose, type HELP."); 
        System.out.println("To exit, type \"EXIT\". Note that EXITing the program will CANCEL the entire program."); 
        
        while (true) {
            System.out.println("What would you like to do?"); 
            String methodName = scanner.nextLine(); 
            if(methodName.toUpperCase().equals("EXIT")) {
                 scanner.close(); 
                 break; 
            } else if (methodName.toUpperCase().equals("HELP")) {
                this.help(options); 
            }else if(!this.runMethod(methodName)) {
                System.out.println("Sorry, " + methodName + " is not a valid choice."); 
            }

            
        }
    }

    private class UIObject {
        private String name; 
        private String description; 
        private Consumer<Scanner> function; 

        public UIObject(String name, String description, Consumer<Scanner> function) {
            this.name = name.toUpperCase(); 
            this.description = description; 
            this.function = function; 
        }

        public String getName() {
            return name; 
        }

        public String getDescription() {
            return description; 
        }

        public Consumer<Scanner> getFunc() {
            return function; 
        }

        public String[] getInfo() {
            return new String[] {name, description}; 
        }

        public String toString() {
            return name + ": " + description; 
        }
    }
}