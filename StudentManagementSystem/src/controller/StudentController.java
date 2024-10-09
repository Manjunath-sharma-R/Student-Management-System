package controller;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private StudentDAO studentDAO;
    private Scanner scanner;

    public StudentController() {
        this.studentDAO = new StudentDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = getInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getInput();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        Student student = new Student(0, name, age, grade, course);
        studentDAO.addStudent(student);
    }

    private void viewStudents() {
        List<Student> students = studentDAO.viewStudents();
        System.out.println("\nStudents List:");
        for (Student student : students) {
            System.out.printf("ID: %d, Name: %s, Age: %d, Grade: %s, Course: %s%n",
                student.getId(), student.getName(), student.getAge(), student.getGrade(), student.getCourse());
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = getInput();
        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.print("Enter new name (current: " + student.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Enter new age (current: " + student.getAge() + "): ");
        int age = getInput();
        System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
        String grade = scanner.nextLine();
        System.out.print("Enter new course (current: " + student.getCourse() + "): ");
        String course = scanner.nextLine();

        student.setName(name);
        student.setAge(age);
        student.setGrade(grade);
        student.setCourse(course);
        studentDAO.updateStudent(student);
    }

    private void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = getInput();
        studentDAO.deleteStudent(id);
    }

    private int getInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine()); // Read input as a string and convert to int
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }
    }
}
