
/**
 * ReflectionTest Class:
 *      should implement main() method.
 * 
 *   Print out all methods declared in class Person (notice, declared methods only), including the 
 *      number of input parameters and all input parameter types.
 * 
 *   Print out all methods available to class Address (notice, all methods), including the number 
 *      of input parameters and all input parameter types. 
 * 
 *   Print out all fields declared in class Address (notice, declared fields only), including the 
 *      access modifier and the type of the field.
 * 
 *   Instantiate 3 new instances of class Person. This should also involve instantiating the Address 
 *      class using reflection.
 * 
 *   Use Java reflection to invoke setFirstName(), setLastName() and setAge() in one or more 
 *      instance(s) of Person class.      
 * 
 * @author Thuy Tran
 * May 14, 2022
 */

import java.lang.reflect.*;

public class ReflectionTest {

    public static void main(String[] args) {
        Method[] personClass = Person.class.getDeclaredMethods();
        System.out.println("Person Class Methods :");
        for (Method method : personClass) {
            System.out.printf("%-15s : Total Parameters- %2d", method.getName(), method.getParameterCount());
            Class[] parameters = method.getParameterTypes();
            if (parameters.length > 0) {
                System.out.printf(" : Parameter Types-");
                for (Class parameter : parameters) {
                    System.out.print(" " + parameter.getSimpleName());
                }
            }
            System.out.println();
        }

        Method[] addressClass = Address.class.getDeclaredMethods();
        System.out.println("\nAddress Class Methods :");
        for (Method method : addressClass) {
            System.out.printf("%-15s : Total Parameters- %2d", method.getName(), method.getParameterCount());
            Class[] parameters = method.getParameterTypes();
            if (parameters.length > 0) {
                System.out.printf(" : Parameter Types-");
                for (Class parameter : parameters) {
                    System.out.print(" " + parameter.getSimpleName());
                }
            }
            System.out.println();
        }

        Field[] fields = Address.class.getDeclaredFields();
        System.out.println("\nAddress Class Fields : ");
        for (Field field : fields) {
            System.out.printf("%-10s %-8s   %s\n", field.getName(), field.getAnnotatedType().getType().getTypeName(),
                    Modifier.toString(field.getModifiers()));
        }

        try {
            Class person = Class.forName("Person");
            Class address = Class.forName("Address");

            System.out.println(address.toString());
            Constructor<Person> person1 = Person.class.getConstructor();
            Person p1 = person1.newInstance();
            Constructor<Person> person2 = Person.class.getConstructor(String.class, String.class, Address.class);
            Person p2 = person2.newInstance("Lisa", "Jenie", address);
            System.out.println(p2.toString());
            Constructor<Person> person3 = Person.class.getConstructor(String.class, String.class, String.class,
                    Address.class);
            Person p3 = person3.newInstance("Lisa", "Jenie", "100401", address);
            Method setFirstName = person.getDeclaredMethod("setFirstName", String.class);
            Method setLastName = person.getDeclaredMethod("setLastName", String.class);
            Method setAge = person.getDeclaredMethod("setAge", int.class);
            setFirstName.invoke(p1, "Thuy");
            setLastName.invoke(p1, "Tran");
            setAge.invoke(p1, 41);
            Method getFirstName = person.getDeclaredMethod("getFirstName");
            Method getAge = person.getDeclaredMethod("getAge");
            System.out.println("\n" + getFirstName.invoke(p1));
            System.out.println(getAge.invoke(p1));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println(e);
        }
    }

}