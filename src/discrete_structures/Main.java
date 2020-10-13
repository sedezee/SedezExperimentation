package discrete_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Main {

    public static void foo () {
        String hi = "hi"; 
    }

    public static void main(String... args) {
        RelationsManipulator rm = new RelationsManipulator<>();
        HashMap<String, List<String>> set = new HashMap<>();
        List<String> aSet = new ArrayList<>();
        aSet.add("A");
        aSet.add("B");
        set.put("A", aSet);
        List<String> aSet2 = new ArrayList<>(); 
        aSet2.add("D"); 
        set.put("B", aSet2); 
        List<String> aSet3 = new ArrayList<>(); 
        aSet3.add("C"); 
        set.put("C", aSet3); 
        List<String> aSet4 = new ArrayList<>(); 
        aSet4.add("A"); 
        set.put("D", aSet4); 
        

        HashMap<String, List<String>> finalSet = rm.getOfRelation(set, set);

        for (Entry<String, List<String>> entry : finalSet.entrySet()) {
            System.out.println("{" + entry.getKey() + ":"); 
            entry.getValue().stream().forEach(System.out::println); 
            System.out.println("}"); 
        }
    }
    
}
