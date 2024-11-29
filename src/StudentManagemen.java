import java.util.Scanner;

class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentStack manager = new StudentStack();

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add student");
            System.out.println("2. Update student score");
            System.out.println("3. Delete student");
            System.out.println("4. Display student list");
            System.out.println("5. Search student by ID");
            System.out.println("6. Sort students by score");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1: // Add student
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student score: ");
                    double score = scanner.nextDouble();
                    Student student = new Student(studentId, name, score);
                    manager.push(student);
                    System.out.println("Student added.");
                    break;

                case 2: // Update student score
                    System.out.print("Enter student ID to update: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter new score: ");
                    double newScore = scanner.nextDouble();
                    manager.updateStudent(studentId, newScore);
                    break;

                case 3: // Delete student
                    System.out.print("Enter student ID to delete: ");
                    studentId = scanner.nextLine();
                    manager.deleteStudent(studentId);
                    break;

                case 4: // Display all students
                    manager.displayAllStudents();
                    break;

                case 5: // Search student by ID
                    System.out.print("Enter student ID to search: ");
                    studentId = scanner.nextLine();
                    Student foundStudent = manager.searchStudent(studentId);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("No student found with ID: " + studentId);
                    }
                    break;

                case 6: // Sort students by score
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Call garbage collector to minimize noise in memory usage

                    long memoryBeforeSort = runtime.totalMemory() - runtime.freeMemory();
                    long startSort = System.nanoTime();

                    manager.sortStudentsByScore(); // Sorting method

                    long endSort = System.nanoTime();
                    long memoryAfterSort = runtime.totalMemory() - runtime.freeMemory();

                    System.out.println("Students sorted by score.");
                    System.out.println("Time taken: " + (endSort - startSort) + " nanoseconds");
                    System.out.println("Memory used: " + (memoryAfterSort - memoryBeforeSort) + " bytes");
                    break;

                case 7: // Exit
                    System.out.println("Program terminated.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
