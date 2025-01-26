package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        // Define o formato numérico para US, garantindo ponto como separador decimal
        Locale.setDefault(Locale.US);

        // Utiliza try-with-resources para garantir o fechamento do Scanner automaticamente
        try (Scanner sc = new Scanner(System.in)) {

            // Cria uma lista para armazenar os funcionários
            List<Employee> list = new ArrayList<>();

            // Solicita ao usuário a quantidade de funcionários que serão registrados
            System.out.print("How many employees will be registered? ");
            int quantEmployee = sc.nextInt();

            // Loop para registrar os funcionários
            for (int i = 0; i < quantEmployee; i++) {
                System.out.println();
                System.out.printf("Employee #%d:%n", i + 1);

                // Lê o ID do funcionário
                System.out.print("Id: ");
                Integer id = sc.nextInt();

                // Verifica se o ID já está sendo utilizado
                while (hasId(list, id)) {
                    System.out.print("Id already taken! Try again: ");
                    id = sc.nextInt();
                }

                // Lê o nome do funcionário
                System.out.print("Name: ");
                sc.nextLine(); // Limpa o buffer
                String name = sc.nextLine();

                // Lê o salário do funcionário
                System.out.print("Salary: ");
                Double salary = sc.nextDouble();

                // Cria um novo objeto Employee com os dados fornecidos e adiciona à lista
                Employee emp = new Employee(id, name, salary);
                list.add(emp);
            }

            System.out.println();

            // Solicita o ID do funcionário que terá o salário aumentado
            System.out.print("Enter the employee id that will have salary increase: ");
            int idSalary = sc.nextInt();

            // Busca o funcionário pelo ID usando Stream API
            Employee emp = list.stream()
                    .filter(x -> x.getId() == idSalary)
                    .findFirst()
                    .orElse(null);

            // Verifica se o funcionário com o ID informado existe
            if (emp == null) {
                System.out.println("This id does not exist!");
            } else {
                // Lê a porcentagem de aumento do salário
                System.out.print("Enter the percentage: ");
                double percent = sc.nextDouble();

                // Aumenta o salário do funcionário chamando o método da classe Employee
                emp.increaseSalary(percent);
            }

            System.out.println();

            // Exibe a lista de funcionários cadastrados e seus dados
            System.out.println("List of employees: ");
            list.forEach(System.out::println); // Usando referência de método para imprimir
        }

    }

    // Método para verificar se um ID já está presente na lista
    public static boolean hasId(List<Employee> list, int id) {
        // Busca um funcionário com o ID fornecido usando Stream API
        Employee emp = list.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        return emp != null; // Retorna true se o funcionário foi encontrado
    }
}
