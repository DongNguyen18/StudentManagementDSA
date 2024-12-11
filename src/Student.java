// Student class
class Student {
    private String studentId;
    private String name;
    private double score;
    private String rank;

    public Student(String studentId, String name, double score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        this.rank = calculateRank(); // Automatically calculate rank
    }

    // Method to calculate student rank (Hàm tính xếp hạng sinh viên)
    private String calculateRank() {
        if (score < 5.0) {
            return "Fail";
        } else if (score < 6.5) {
            return "Average";
        } else if (score < 7.5) {
            return "Good";
        } else if (score < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    // Getters and Setters (Phương thức lấy và đặt giá trị)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.rank = calculateRank(); // Update rank when score changes
    }

    public String getRank() {
        return rank;
    }

    // Method to display student information (Hiển thị thông tin sinh viên)
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", rank='" + rank + '\'' +
                '}';
    }
}
