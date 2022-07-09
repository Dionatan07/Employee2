package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner key = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        int n = key.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.println("Employee #" + (i + 1));

            System.out.print("Id: ");
            int id = key.nextInt();

            while (hasId(list, id)){
                System.out.println("Id already taken. Try again!");
                id = key.nextInt();
            }

            key.nextLine();
            System.out.print("Name: ");
            String name = key.nextLine();

            System.out.print("Salary: ");
            double salary = key.nextDouble();

            Employee employee = new Employee(id, name, salary);

            list.add(employee);

        }

        System.out.println();
        System.out.print("Enter the employee id that will have salary increase: ");
        int id = key.nextInt();

        Employee employee = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (employee == null) {
            System.out.println("This id does not exist!");
        } else {
            System.out.print("Enter the percentage: ");
            double percentage = key.nextDouble();
            employee.increaseSalary(percentage);
        }


        System.out.println();
        System.out.println("List of employees:");
        for (Employee e : list) {
            System.out.println(e.getId() + ", " + e.getName() + ", " + String.format("%.2f", e.getSalary()));
        }

    }

    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}
