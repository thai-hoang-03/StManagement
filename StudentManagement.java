package ManageStudents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWELCOME TO STUDENT MANAGEMENT");
            System.out.println("1. Create");
            System.out.println("2. Find and Sort");
            System.out.println("3. Update/Delete");
            System.out.println("4. Report");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSortStudents();
                    break;
                case 3:
                    updateOrDeleteStudent();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createStudent() {
        System.out.println("Creating a new student:");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Course Name (Java, .Net, C/C++): ");
        String course = scanner.nextLine();

        Student student = new Student(id, name, semester, course);
        students.add(student);

        if (students.size() >= 10) {
            System.out.print("Do you want to continue (Y/N)? ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("Y")) {
                return;
            }
        }
    }

    private static void findAndSortStudents() {
        System.out.print("Enter a part of student name to search: ");
        String searchName = scanner.nextLine();

        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchName.toLowerCase())) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("No students found with the given search criteria.");
        } else {
            Collections.sort(foundStudents, (s1, s2) -> s1.getName().compareTo(s2.getName()));
            System.out.println("Found students (sorted by name):");
            for (Student student : foundStudents) {
                System.out.println(student);
            }
        }
    }

    private static void updateOrDeleteStudent() {
        System.out.print("Enter Student ID to find: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student foundStudent = null;
        for (Student student : students) {
            if (student.getId() == id) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent == null) {
            System.out.println("Student not found with the given ID.");
            return;
        }

        System.out.print("Do you want to update (U) or delete (D) this student? ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("U")) {
            System.out.print("Enter new Student Name: ");
            String newName = scanner.nextLine();
            foundStudent = new Student(foundStudent.getId(), newName, foundStudent.getSemester(), foundStudent.getCourse());
            System.out.println("Student information updated successfully.");
        } else if (choice.equalsIgnoreCase("D")) {
            students.remove(foundStudent);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void generateReport() {
        System.out.println("Student Report:");

        ArrayList<String> uniqueNames = new ArrayList<>();
        for (Student student : students) {
            String fullName = student.getName() + " | " + student.getCourse();
            if (!uniqueNames.contains(fullName)) {
                uniqueNames.add(fullName);
            }
        }

        for (String fullName : uniqueNames) {
            String[] parts = fullName.split(" \\| ");
            String name = parts[0];
            String course = parts[1];
            int count = 0;

            for (Student student : students) {
                if (student.getName().equals(name) && student.getCourse().equals(course)) {
                    count++;
                }
            }

            System.out.println(name + " | " + course + " | " + count);
        }
    }
    
}
