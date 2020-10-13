package reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionTools {  
    /*
    A set of reflection tools. The non-recursive versions 
    are the sane forms. The recursive versions were done to 
    experiment with interfaces and recursion. 
    */ 

    interface rInterface {
        Object run(Class<?> clasz); 
    }

    public List<Class<?>> getParentClassesRecursive(Class<?> clasz) {
        List<Class<?>> parentClasses = new ArrayList<Class<?>>(); 
        //Anonymous interface implementation that makes a version of the rInt interface
        //to run a method within a method. 
        rInterface rInt = new rInterface() { public Class<?> run (Class<?> clasz) {
            if(clasz != Object.class) { 
                parentClasses.add(run(clasz.getSuperclass()));  
            }
            return clasz; 
        }};
        //The initial kicking off of the interface method 'run'
        rInt.run(clasz);
        return parentClasses; 
    }

    public List<Class<?>> getParentClasses(Class <?> clasz) {
        List<Class<?>> parentClasses = new ArrayList<Class<?>>(); 
        while (clasz != Object.class) {
            clasz = clasz.getSuperclass();
            parentClasses.add(clasz);  
        }
        return parentClasses; 
    }
    
    public List<Field> getAllFieldsRecursive(Class<?> clasz) {
        List<Field> fields = new ArrayList<Field>(); 
        //A similar anonymous interface implementation to above. Creates the interface and the method within it 
        rInterface rInt = new rInterface() { public Class<?> run (Class<?> clasz) {
            if(clasz != Object.class) {
                fields.addAll(Arrays.asList(clasz.getDeclaredFields()));
                run(clasz.getSuperclass());  
            }
            return clasz; 
        }};
        rInt.run(clasz); 
        return fields; 
    }

    public List<Field> getAllFields(Class<?> clasz) {
        List<Field> fields = new ArrayList<Field>(); 
        while (clasz != Object.class) {
            fields.addAll(Arrays.asList(clasz.getDeclaredFields())); 
            clasz = clasz.getSuperclass(); 
        }
        return fields; 
    }
}
