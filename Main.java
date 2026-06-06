import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); 
    // Default standard scanner initialization
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== STUDENT GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Calculate Average Marks");
            System.out.println("5. Exit");
            System.out.println("Enter your choice (1-5): ");
            
            while (!input.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number between 1 and 5: ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    calculateAverage();
                    break;
                case 5:
                    System.out.println("Exiting system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option from 1 to 5.");
            }
        } while (choice != 5);
    }

    // 1. Add a new student record
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();
        
        // Check for duplicate ID
        for (Student s : studentList) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                System.out.println("Error: A student with this ID already exists.");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter Student Marks (0 - 100): ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid marks! Enter a numeric value: ");
            input.next();
        }
        double marks = input.nextDouble();
        input.nextLine(); 

        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be between 0 and 100.");
            return;
        }

        Student newStudent = new Student(id, name, marks);
        studentList.add(newStudent);
        System.out.println("Student record added successfully!");
    }

    // 2. Display all student records
    private static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n-------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-6s |\n", "Student ID", "Student Name", "Marks");
        System.out.println("-------------------------------------------------");
        for (Student student : studentList) {
            student.displayStudentDetails();
        }
        System.out.println("-------------------------------------------------");
    }

    // 3. Search for a student by ID
    private static void searchStudentById() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available to search.");
            return;
        }

        System.out.print("Enter Student ID to search: ");
        String searchId = input.nextLine().trim();
        boolean found = false;

        for (Student student : studentList) {
            if (student.getStudentId().equalsIgnoreCase(searchId)) {
                System.out.println("\nStudent Record Found:");
                System.out.println("-------------------------------------------------");
                System.out.printf("| %-12s | %-20s | %-6s |\n", "Student ID", "Student Name", "Marks");
                System.out.println("-------------------------------------------------");
                student.displayStudentDetails();
                System.out.println("-------------------------------------------------");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID '" + searchId + "' not found.");
        }
    }

    // 4. Calculate and display average marks
    private static void calculateAverage() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available to calculate average.");
            return;
        }

        double totalMarks = 0;
        for (Student student : studentList) {
            totalMarks += student.getMarks();
        }

        double average = totalMarks / studentList.size();
        System.out.println("\n=================================");
        System.out.printf("Total Students : %d\n", studentList.size());
        System.out.printf("Average Marks  : %.2f\n", average);
        System.out.println("=================================");
    }
}