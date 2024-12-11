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

        // Khôi phục ngăn xếp ban đầu
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            System.out.println("Student updated successfully.");
        }
    }

    // Xóa một sinh viên theo ID
    public void deleteStudent(String studentId) {
        Stack<Student> tempStack = new Stack<>();

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (!student.getStudentId().equals(studentId)) {
                tempStack.push(student);
            }
        }

        // Khôi phục ngăn xếp ban đầu mà không có học sinh bị xóa
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        System.out.println("Student with ID " + studentId + " deleted successfully.");
    }

    // Tìm kiếm sinh viên theo ID
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

        // Khôi phục ngăn xếp ban đầu
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return foundStudent;
    }

    // Hiển thị tất cả sinh viên
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

        // Khôi phục ngăn xếp ban đầu
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }
    }

    // Sắp xếp học sinh theo điểm bằng Bubble Sort
    public void sortStudentsByScoreBubble() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        // Chuyển Stack thành Mảng để sắp xếp
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

        // Xóa ngăn xếp và thêm lại các phần tử đã sắp xếp
        students.clear();
        for (Student student : studentArray) {
            students.push(student);
        }

        System.out.println("Students sorted by score using Bubble Sort.");
    }

    // Sắp xếp học sinh theo điểm bằng Merge Sort
    public void sortStudentsByScoreMerge() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        // Chuyển Stack thành Mảng để sắp xếp
        Student[] studentArray = students.toArray(new Student[0]);

        // Thuật toán sắp xếp hợp nhất
        mergeSort(studentArray, 0, studentArray.length - 1);

        // Xóa ngăn xếp và thêm lại các phần tử đã sắp xếp
        students.clear();
        for (Student student : studentArray) {
            students.push(student);
        }

        System.out.println("Students sorted by score using Merge Sort.");
    }

    private void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private void merge(Student[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i].getScore() <= rightArray[j].getScore()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
