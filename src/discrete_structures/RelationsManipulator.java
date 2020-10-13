package discrete_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RelationsManipulator<K, V> {
    ArrayList<HashMap<K,V>> relations; 

    public RelationsManipulator(ArrayList<HashMap<K,V>> relations) {
        this.relations =  relations; 
    }

    public RelationsManipulator() {
        this.relations = new ArrayList<>(); 
    }

    public void addRelation(HashMap<K,V> relation) {
        relations.add(relation); 
    }

    public void removeRelation(HashMap<K,V> relation) {
        relations.remove(relation); 
    }

    public void removeRelation(int index) {
        relations.remove(index); 
    }

    public HashMap<K, V> getOfRelation (HashMap<K, V> fx, HashMap<K,V> gx) {
        HashMap<K, V> result = new HashMap<>(); 

        for (Map.Entry<K, V> entry : gx.entrySet()) {
            List<K> entryValues = (List<K>) entry.getValue(); 

            for (K entryKey : entryValues) {
                // TODO 
            }
        }

        return result; 
    }


    public HashMap<K, V> getTransitiveClosure(HashMap<K,V> relation) {
        HashMap<K, V> closure = relation; 
        HashSet<K> set = new HashSet<>(); 
        for (Map.Entry<K, V> entry : closure.entrySet()) {
            List entryList = (List) entry.getValue(); 
            if (!entry.getKey().getClass().equals(entryList.get(0).getClass())) {
                throw new IllegalArgumentException(
                        "Key and value must be of same type to create a transitive closure.");
            }

            set.add(entry.getKey()); 
        }

        HashMap<K, V> previousRelation = relation; 

        for (int i = 0; i < set.size(); i++) {
            HashMap<K, V> ofRelation = getOfRelation(previousRelation, relation); 

            Set<K> ofKKeys = ofRelation.keySet(); 
            Set<K> closureKeys = closure.keySet(); 

            for (K ofVal : ofKKeys) {
                if (closureKeys.contains(ofVal)) {
                    List<K> addValues = new ArrayList<>(); 
                    List<K> closureValues = (List<K>) closure.get(ofVal); 
                    List<K> ofValues = (List<K>) ofRelation.get(ofVal); 
                    boolean contains = false; 
                    for (K ofValue : ofValues) {
                        for (K closureValue : closureValues) {
                            if (ofValue.equals(closureValue)) {
                                contains = true; 
                            }
                        }
                        if (!contains) {
                            addValues.add(ofValue); 
                        }
                    }
                    
                    addValues.addAll(ofValues); 
                    closure.put(ofVal, (V) addValues);
                } else {
                    closure.put(ofVal, ofRelation.get(ofVal)); 
                }
            }

            previousRelation = ofRelation; 
        }
        return closure; 
    }
    
}
