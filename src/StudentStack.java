import java.util.Stack;

public class StudentStack {
    private Stack<Student> students;

    public StudentStack() {
        this.students = new Stack<>();
    }

    // Add a new student
    public void push(Student student) {
        students.push(student);
    }

    // Update an existing student's score
    public void updateStudent(String studentId, double newScore) {
        Stack<Student> tempStack = new Stack<>();
        boolean found = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getStudentId().equals(studentId)) {
                student.setScore(newScore);
                tempStack.push(student);
                found = true;
            } else {
                tempStack.push(student);
            }
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            System.out.println("Student updated successfully.");
        }
    }

    // Delete a student by ID
    public void deleteStudent(String studentId) {
        Stack<Student> tempStack = new Stack<>();

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (!student.getStudentId().equals(studentId)) {
                tempStack.push(student);
            }
        }

        // Restore the original stack without the deleted student
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        System.out.println("Student with ID " + studentId + " deleted successfully.");
    }

    // Search for a student by ID
    public Student searchStudent(String studentId) {
        Stack<Student> tempStack = new Stack<>();
        Student foundStudent = null;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getStudentId().equals(studentId)) {
                foundStudent = student;
            }
            tempStack.push(student);
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return foundStudent;
    }

    // Display all students
    public void displayAllStudents() {
        Stack<Student> tempStack = new Stack<>();

        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        while (!students.isEmpty()) {
            Student student = students.pop();
            System.out.println(student);
            tempStack.push(student);
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }
    }

    // Sort students by score using Bubble Sort
    public void sortStudentsByScore() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        // Convert Stack to Array for sorting
        Student[] studentArray = students.toArray(new Student[0]);
        int n = studentArray.length;

        // Bubble Sort Algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentArray[j].getScore() > studentArray[j + 1].getScore()) {
                    // Swap students
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                }
            }
        }

        // Clear the stack and re-add sorted elements
        students.clear();
        for (Student student : studentArray) {
            students.push(student);
        }

        System.out.println("Students sorted by score.");
    }
}
