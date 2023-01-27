package staticImport;
// static import - import all static members (both variables and functions using static import)
// We can call the method directly without using class name
// We should write utilities in a static way

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static staticImport.Test.*;

public class Executor {
    public static void main(String[] args) {
        add(10, 20);
        printSomething();
        System.out.println(salary);

        //method chaining -> simultaneous calls
        String[] arr = {"Apple", "Orange", "Guava", "Watermelon"};
        System.out.println(Arrays.asList(arr).stream().map(e -> e + " fruit").collect(Collectors.toList()));
    }
}
