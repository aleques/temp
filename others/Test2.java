package skyscanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

class Employee {

    String name;
    Employee manager;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}

public class Test2 {

    public static void main(String[] args) {

        OutputCommonManager(6, new Scanner("Hilary\nJames\nSarah Fred\nSarah Paul\nFred Hilary\nFred Jenny\nJenny James"));
        OutputCommonManager(4, new Scanner("Simon\nClaudiu\nSarah Claudiu\nSarah Paul\nClaudiu Simon"));
    }

    static void OutputCommonManager(int count, Scanner in) {

        if (count == 0) {
            return;
        }

        Map<Employee, Employee> employeeMap = new HashMap<>();

        Employee employeeA = new Employee(in.nextLine());
        Employee employeeB = new Employee(in.nextLine());

        employeeMap.put(employeeA, employeeA);
        employeeMap.put(employeeB, employeeB);
        count = count-2;

        String[] twoEmployee;
        Employee employeeManager;
        Employee employee;
        Employee employeeAux;

        for (int i = 0; i <= count; i++) {

            twoEmployee = in.nextLine().split(" ");

            employeeManager = new Employee(twoEmployee[0]);
            employeeManager.manager = employeeMap.containsKey(employeeManager) ? employeeMap.get(employeeManager).manager : null;
            employeeMap.put(employeeManager, employeeManager);

            employee = new Employee(twoEmployee[1]);
            employeeAux = employeeMap.get(employee);
            employee = employeeAux != null ? employeeAux : employee;
            employee.manager = employeeManager;

            employeeMap.put(employee, employee);
        }

        int countA = 0;
        employeeAux = employeeA;

        while (employeeAux.manager != null) {
            employeeAux = employeeAux.manager;
            countA++;
        }

        int countB = 0;
        employeeAux = employeeB;

        while (employeeAux.manager != null) {
            employeeAux = employeeAux.manager;
            countB++;
        }


        if (countA <= countB) {

            if ( (employeeA.manager != null && employeeA.manager.manager != null) || (employeeB.manager == null || employeeB.manager.manager == null) ) {
                employee = employeeA;
            } else {
                employee = employeeB;
            }
        } else {

            if ( (employeeB.manager != null && employeeB.manager.manager != null) || (employeeA.manager == null || employeeA.manager.manager == null) ) {
                employee = employeeB;
            } else {
                employee = employeeA;
            }
        }

        System.out.println(employee.manager.name);
    }
}

