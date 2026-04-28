package src;

public class CourseNode {
    Course course;
    CourseNode next;
    CourseNode previous;

    public CourseNode(Course course) {
        this.course = course;
        this.next = null;
        this.previous = null;
    }
}
