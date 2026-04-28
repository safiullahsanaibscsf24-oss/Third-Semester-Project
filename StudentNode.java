package src;

public class StudentNode {
    Student student;
    StudentNode left;
    StudentNode right;

    public StudentNode(Student student) {
        this.student = student;
        this.left = null;
        this.right = null;
    }
}
