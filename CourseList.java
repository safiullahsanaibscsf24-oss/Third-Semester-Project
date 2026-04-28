package src;

public class CourseList {
    private CourseNode head, tail;

    public CourseList() {
        this.head = null;
        this.tail = null;
    }

    public void addCourse(Course course) {
        CourseNode newNode = new CourseNode(course);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    public Course findCourse(String code) {
        CourseNode current = head;
        while (current != null) {
            if (current.course.courseCode.equalsIgnoreCase(code)) {
                return current.course;
            }
            current = current.next;
        }
        return null;
    }

    public void displayCatalog() {
        CourseNode current = head;
        if (current == null) {
            System.out.println("Course catalog is empty.");
            return;
        }
        System.out.println("--- Course Catalog ---");
        while (current != null) {
            System.out.println(current.course);
            current = current.next;
        }
        System.out.println("--------------------");
    }

    public void removeTail() {
        if (tail == null) {
            // List is empty
            return;
        }
        if (head == tail) {
            // Only one node in the list
            head = null;
            tail = null;
            return;
        }
        // More than one node
        CourseNode newTail = tail.previous;
        newTail.next = null;
        tail = newTail;
    }
}
