package reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
A set of reflection tools. The non-recursive versions 
are the sane forms. The recursive versions were done to 
experiment with interfaces and recursion. 
*/
public class ReflectionTools {   

    /**
     * Retrieve all parent classes
     * @param clasz the class to get the parents of
     * @return parents of clasz
     */
    public List<Class<?>> getParentClasses(Class <?> clasz) {
        List<Class<?>> parentClasses = new ArrayList<Class<?>>(); 
        while (clasz != Object.class) {
            clasz = clasz.getSuperclass();
            parentClasses.add(clasz);  
        }
        return parentClasses; 
    }

    /**
     * Get all fields of classes and their superclasses
     * @param clasz class to get the field of
     * @return all fields 
     */
    public List<Field> getAllFields(Class<?> clasz) {
        List<Field> fields = new ArrayList<Field>(); 
        while (clasz != Object.class) {
            fields.addAll(Arrays.asList(clasz.getDeclaredFields())); 
            clasz = clasz.getSuperclass(); 
        }
        return fields; 
    }
}
