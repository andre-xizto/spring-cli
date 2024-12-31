package dev.buskopan.manager.dependency_manager;

import dev.buskopan.dependencies.IDependency;
import dev.buskopan.dependencies.impl.SpringDataJPA;
import dev.buskopan.dependencies.impl.SpringStarterWeb;

import java.util.*;

public class DependencyManager {

    private final List<IDependency> dependencyList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private int count;

    public DependencyManager() {
        dependencyList.add(new SpringStarterWeb());
        dependencyList.add(new SpringDataJPA());
    }

    public List<IDependency> selectDependencies() {
        List<IDependency> dependencies = new ArrayList<>();
        displayDependencyList(dependencies);
        while(true) {
            System.out.println("> type a number of a dependency to add it or type 0 to exit of this menu");
            String value = sc.next();

            if (value.equals("0")) break;

            int option = Integer.parseInt(value);
            int arrayOption = option - 1;

            if (dependencyList.size() < option) {
                System.out.println("Invalid option");
                displayDependencyList(dependencies);
                continue;
            }

            IDependency selectedDependency = dependencyList.get(arrayOption);

            if (selectedDependency == null) {
                System.out.println("Invalid option");
                displayDependencyList(dependencies);
                continue;
            }

            if (dependencies.contains(selectedDependency)) {
                dependencies.remove(selectedDependency);
            } else {
                dependencies.add(selectedDependency);
            }

            displayDependencyList(dependencies);
        }

        return dependencies;
    }

    private void displayDependencyList(List<IDependency> dependencies) {
        int count = 1;
        System.out.println("""
                > SELECT WHAT DEPENDENCIES YOU WILL USE
                
                """);
        System.out.println("0 - terminate");
        for (IDependency dependency : dependencyList) {
            String icon;
            icon = dependencies.contains(dependency) ? "[X] " : "[ ] ";
            System.out.println(count++ + " - "+ icon + dependency.getDisplay());
        }
    }

}
