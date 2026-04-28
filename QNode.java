package src;

public class QNode {
    Student student;
    QNode next;

    public QNode(Student student) {
        this.student = student;
        this.next = null;
    }
}
