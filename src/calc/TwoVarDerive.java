package calc;

import java.util.ArrayList;
import java.util.List;

public class TwoVarDerive {
    /*
    This is an incomplete version of a derivative calculator. 
    TODO: redo later w/ an abstract syntax tree
    */ 

    private String firstVar; 
    private String secondVar; 
    private String prime; 
    ArrayList<String> vars;
    ArrayList<String> operands; 
    private static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))|((?<=%2$s)|(?=%2$s))";

    /**
     * Derives a two variable equation 
     * @param firstVar 
     *      the first variable to derive by. Typically x or t. 
     * @param secondVar
     *      the second variable to derive by. Typically y.
     * @param equation
     *      the equation to be derived. Should only contain variables in accordance with first and second var. 
     */
    public TwoVarDerive(String firstVar, String secondVar, String equation) {
        this.firstVar = firstVar; 
        this.secondVar = secondVar; 
        this.prime = String.format("d%s/d%s", secondVar, firstVar); 
        equation = equation.replaceAll("\\s", ""); 

        vars = new ArrayList<String>(); 
        operands = new ArrayList<String>(); 

        int lastIndice = 0; 
        for (int i = 0; i < equation.length(); i++) {
            if(equation.charAt(i) == '+' || equation.charAt(i) == '-') {
                vars.add(equation.substring(lastIndice, i)); 
                operands.add(Character.toString(equation.charAt(i))); 
                lastIndice = i + 1; 
            }
        }
        vars.add(equation.substring(lastIndice, equation.length())); 
    }

    public String[] getVariables() {
        return new String[] {firstVar, secondVar}; 
    }
    
    public List<String> getEquation() {
        ArrayList<String> equation = new ArrayList<>(); 
        for (int i = 0; i < vars.size(); i++) {
            equation.add(vars.get(i)); 
            equation.add(operands.get(i)); 
        }
        return equation; 
    }

    /**
     * Derive the full equation
     * @return the equation in string format
     */
    public String deriveFull() {
        String res  = ""; 
        for(int i = 0; i < vars.size(); i++) {
            res += "(" + derive(vars.get(i)) + ")";
            if (i < vars.size() - 1)
                res += operands.get(i);  
        }
        return res; 
    }
    
    /**
     * Dervice the provided equation 
     * @param equation the equation to derive 
     * @return the derived equation 
     */
    public String derive(String equation) {
        String res = ""; 
        if (equation.contains("/"))
            res = quotientRule(equation);
        else if (equation.contains(firstVar) && equation.contains(secondVar))
            res = chainRule(equation);  
        else
            res = powerRule(equation); 

        return res; 
    }

    /**
     * Perform the power rule 
     * @param equation the equation to perform the rule on 
     * @return the result of the power rule
     */
    private String powerRule(String equation) {
        String[] vars = equation.split(String.format(WITH_DELIMITER, firstVar, secondVar)); 
        int constant = 1; 
        String res = ""; 
        ArrayList<String> resArr = new ArrayList<String>(); 
        boolean caught = false; 
        int vPower = 0; 
        
        for (int i = 0; i < vars.length; i++) {
            try {
                constant *= Integer.parseInt(vars[i]); 
            } catch(NumberFormatException e) {
                caught = true; 
                if (!(vars[i].equals(firstVar) || vars[i].equals(secondVar))){
                   
                    StringBuilder varSB = new StringBuilder(vars[i]); 
                    varSB.deleteCharAt(varSB.indexOf("^")); 

                    vPower = Integer.parseInt(varSB.toString()); 
                    constant *= vPower;
                    
                    String variable =  vars[i-1]; 
                    if (vPower != 2)
                        variable = variable + "^" + (vPower-1); 
                    
                    if (vPower > 1)
                        resArr.add(variable);
                        
                } else {
                    if (((i+1) >= vars.length || vars[i+1] != "^") && vars[i].equals(secondVar)) {
                        resArr.add(prime); 
                    }
                }
            } 
        }

        if (!caught) 
            constant = 0; 
            
        res += constant;

        for (String r : resArr) {
            res += r; 
            if (r.equals(prime) && vPower >= 2)
                res += "*"; 
        }
        
        return res; 
    }

    /**
     * Perform the quotient rule 
     * @param equation the equation to perform the quotient rule on 
     * @return the result of the quotient rule
     */
    private String quotientRule(String equation) {
        String res;  
        String[] vars = equation.split("/"); 
        String[] resArr = new String[vars.length]; 

        for (int i = 0; i < vars.length; i++) {
            resArr[i] = derive(vars[i]); 
        }

        //f'g - g'f / g^2

        //f' = resArr[0]
        //g' = resArr[1]
        //f = vars[0]
        //g = vars[1]
        
        res = String.format("((%s * %s) - (%s * %s)) / (%s)^2",
            resArr[0], vars[1], resArr[1], vars[0], vars[1]); 
        
        return res; 
    }

    /**
     * Performs the chain rule
     * @param equation the equation to perform the chain rule on 
     * @return the result of the chain rule 
     */
    private String chainRule(String equation) {
        String[] vars = equation.split(String.format(WITH_DELIMITER, firstVar, secondVar)); 
        String[] resArr = new String[2]; 
        String[] equations = new String[2];

        int lastVar = 0; 
        for (int i = 0; i < vars.length; i++) {
            if ((vars[i].equals(firstVar) || vars[i].equals(secondVar)))
                lastVar = i; 
        }

        String eq = ""; 
        for (int i = 0; i < vars.length; i++) {
            if (i < lastVar) {
                eq += vars[i]; 
            } else if (i == lastVar) {
                equations[0] = eq; 
                eq = vars[i]; 
            } else {
                eq += vars[i]; 
            }
        }

        equations[1] = eq; 

        for(int i = 0; i < 2; i++) {
            resArr[i] = derive(equations[i]); 
        }

        return String.format("(%s * %s) + (%s * %s)", 
            resArr[0], equations[1], resArr[1], equations[0]); 
        }
}