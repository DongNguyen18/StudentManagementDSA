import java.util.Random;
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
            System.out.println("6. Sort students by score :");
            System.out.println("7. Generate random students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //Xóa ký tự xuống dòng

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

                    // Mảng lưu kết quả thời gian thực thi
                    long[] executionTimes  = new long[2];

                    // Tính thời gian Bubble Sort
                    long bubbleStart = System.nanoTime();
                    manager.sortStudentsByScoreBubble();
                    long bubbleEnd = System.nanoTime();
                    executionTimes [0] = bubbleEnd - bubbleStart;

                    // Tính thời gian Merge Sort
                    long mergeStart = System.nanoTime();
                    manager.sortStudentsByScoreMerge();
                    long mergeEnd = System.nanoTime();
                    executionTimes [1] = mergeEnd - mergeStart;

                    // Hiển thị thời gian từ mảng
                    System.out.println("Bubble Sort Time: " + executionTimes [0] + " nanoseconds");
                    System.out.println("Merge Sort Time: " + executionTimes [1] + " nanoseconds");

                    break;


                case 7: // Generate random students
                    System.out.print("Enter number of students to generate: ");
                    int count = scanner.nextInt();
                    generateRandomStudents(manager, count);
                    break;

                case 8: // Exit
                    System.out.println("Program terminated.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void generateRandomStudents(StudentStack manager, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String studentId = "S" + (i + 1); // Sinh mã sinh viên
            String name = "Student" + (i + 1); // Sinh tên sinh viên
            double score = Math.round(random.nextDouble() * 10 * 10.0) / 10.0;
            // Sinh điểm ngẫu nhiên từ 0 đến 10, làm tròn đến 1 chữ số thập phân
            Student student = new Student(studentId, name, score);
            manager.push(student); // Đẩy sinh viên vào stack
        }
        System.out.println(count + " random students generated.");
    }

}

